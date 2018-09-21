@ECHO OFF
@REM File: deployment\iibdeploy.bat
@REM Version: 0.1.14
@REM Date: 25-Nov 2014
@REM Author: Alex Russell, Navin Khanna
@REM Comment: switched around MQSI_RESTART functionality..
@REM Comment: [0.1.14] added JMSAdmin support and reconditioned logic to support /mqsc|/jmsc

@REM For example: %1=>IBND1 %2=>core-batch-initiator %3=>DV [%4=>2] [%5=>/mqsc|/jmsc]
@REM For example: %1=>IBND1 %2=>core-log-handler-library-java

CALL ..\resources\%USERNAME%-env.bat

SET "_NODE_=%1"
SET "_PROJ_=%2"
CALL ..\resources\iibdeploy-%_NODE_%-env.bat
CALL ..\resources\%_HOSTNAME%-env.bat

SET "_ENVN="
IF NOT "x%3" == "x" (
 SET "_ENVN=%3"
)
SET "_ENVN_=%_ENVN%"
IF NOT "x%_ENVN%" == "x" (
 SET "_ENVN_=-%_ENVN%"
)
SET "_INST="
IF NOT "x%4" == "x" (
 SET "_INST=%4"
 IF "%_INST%" == "0" (
  SET "_INST="
 )
 IF "%_INST%" == "1" (
  SET "_INST="
 )
)
SET "_INST_=%_INST%"
IF NOT "x%_INST%" == "x" (
 SET "_INST_=-%_INST%"
)
SET "_ONLY_MQSC=0"
IF "x%4" == "x/mqsc" (
 SET "_ONLY_MQSC=1"
) ELSE (
 IF "x%5" == "x/mqsc" (
  SET "_ONLY_MQSC=1"
 )
)
SET "_ONLY_JMSC=0"
IF "x%4" == "x/jmsc" (
 SET "_ONLY_JMSC=1"
) ELSE (
 IF "x%5" == "x/jmsc" (
  SET "_ONLY_JMSC=1"
 )
)
IF "xlocalhost" == "x%_HOSTNAME%" (
 CALL ..\resources\iibdeploy-localhost-env.bat
 SET "REMOVE=REMOVE-localhost.bat"
) ELSE (
 CALL ..\resources\iibdeploy-env.bat
 SET "REMOVE=REMOVE.bat"
)

MKDIR ..\cygmin64\home 1>nul 2>nul
MKDIR ..\cygmin64\home\%USERNAME% 1>nul 2>nul


IF %_ONLY_JMSC% EQU 0 (
 IF EXIST "..\source\%2\mqsc.txt" (
  @REM MQSeries Script Commands
  CALL :MQSC
 )
)
IF %_ONLY_MQSC% EQU 0 (
 IF EXIST "..\source\%2\jmsc.txt" (
  @REM JMSAdmin Script Commands
  CALL :JMSC
 )
)
IF %_ONLY_MQSC% EQU 0 (
 IF %_ONLY_JMSC% EQU 0 (
  IF EXIST "..\source\%_PROJ_%\sql.txt" (
   @REM SQL*Plus Commands
   CALL :PLSQL
  )
  IF EXIST "..\source\%_PROJ_%\dropins.txt" (
   @REM Eclipse Toolkit dropins - For example: C:\Program Files (x86)\IBM\IntegrationToolkit90\dropins
   FOR %%i IN ("%_ECLIPSE_DROPINS%") DO (
    IF EXIST %%~si\NUL (
     CALL :ECLIPSE
    )
   )
  )
  IF NOT "x0" == "%_MQSI_RESTART%" (
   CALL MQSI_RESTART.bat %_NODE_% %1 %2 %_ENVN% %_INST% /stop
  )
  @REM Integration Bus JPlugin (On Windows As Administrator)
  IF EXIST "..\source\%_PROJ_%\jplugin.txt" (
   CALL :JPLUGIN
  )
  @REM Integration Bus shared-classes
  IF EXIST "..\source\%_PROJ_%\shared-classes.txt" (
   CALL :SHARED_CLASSES
  )
  IF "x1" == "%_MQSI_RESTART%" (
   CALL MQSI_RESTART.bat %_NODE_% %_PROJ_% %_ENVN% %_INST% /start
  )
  @REM Integration Bus BAR
  IF EXIST "..\source\%_PROJ_%\bar.txt" (
   CALL :BAR
  )
 )
)


GOTO :FINISH


:MQSC
ECHO ^>^>^> MQSC ^<^<^<
ECHO %_MKDIR_MQSC%
CALL %_MKDIR_MQSC%
ECHO %_COPY_MQSC% %_SOURCE_MQSC%runmqsc%_ENVN_%%_INST_%.mqsc %_TARGET_MQSC%
CALL %_COPY_MQSC% %_SOURCE_MQSC%runmqsc%_ENVN_%%_INST_%.mqsc %_TARGET_MQSC%
ECHO %_CHMOD_MQSC%
CALL %_CHMOD_MQSC%
ECHO %_DEPLOY_MQSC%
CALL %_DEPLOY_MQSC%
GOTO :EOF

:JMSC
ECHO ^>^>^> JMSC ^<^<^<
ECHO %_MKDIR_JMSC%
CALL %_MKDIR_JMSC%
ECHO %_COPY_JMSC% %_SOURCE_JMSC%jmsadmin%_ENVN_%%_INST_%.jmsc %_TARGET_JMSC%
CALL %_COPY_JMSC% %_SOURCE_JMSC%jmsadmin%_ENVN_%%_INST_%.jmsc %_TARGET_JMSC%
ECHO %_CHMOD_JMSC%
CALL %_CHMOD_JMSC%
ECHO %_DEPLOY_JMSC%
CALL %_DEPLOY_JMSC%
GOTO :EOF

:PLSQL
ECHO ^>^>^> PL/SQL ^<^<^<
ECHO %_MKDIR_MQSC%
CALL %_MKDIR_PLSQL%
ECHO %_COPY_PLSQL% %_SOURCE_PLSQL%sqlplus%_ENVN_%%_INST_%.s* %_TARGET_PLSQL%
CALL %_COPY_PLSQL% %_SOURCE_PLSQL%sqlplus%_ENVN_%%_INST_%.s* %_TARGET_PLSQL%
ECHO %_CHMOD_PLSQL%
CALL %_CHMOD_PLSQL%
ECHO %_DEPLOY_PLSQL%
CALL %_DEPLOY_PLSQL%
GOTO :EOF

:ECLIPSE
ECHO ^>^>^> DROPINS ^<^<^<
ECHO ^>^>^> Manually Stop Eclipse ^<^<^<
pause
ECHO %_REMOVE_JAR% "%_ECLIPSE_DROPINS%%_SEP%%_PROJ_%*.jar"
CALL %_REMOVE_JAR% "%_ECLIPSE_DROPINS%%_SEP%%_PROJ_%*.jar"
ECHO %_COPY_JAR% %_SOURCE_JAR%%_PROJ_%%_VERSION_%.jar "%_ECLIPSE_DROPINS%"
CALL %_COPY_JAR% %_SOURCE_JAR%%_PROJ_%%_VERSION_%.jar "%_ECLIPSE_DROPINS%"
@REM For now, _ECLIPSE_DROPINS is final resting place, so no need for step below (and _DEPLOY_DROPINS is not defined)
@REM ECHO %_SHELL_BAR% %_DEPLOY_DROPINS%
@REM CALL %_SHELL_BAR% %_DEPLOY_DROPINS%
ECHO ^>^>^> Manually Start Eclipse ^<^<^<
GOTO :EOF

:SHARED_CLASSES
ECHO ^>^>^> SHARED-CLASSES ^<^<^<
IF EXIST "%_DESTIN_JAR%%_PROJ_%%_VERSION_%.jar" (
 ECHO _MKDIR_CLASSES: %_MKDIR_CLASSES%
 CALL %_MKDIR_CLASSES%
 ECHO _REMOVE_CLASSES: %_REMOVE_CLASSES%
 CALL %_REMOVE_CLASSES%
 ECHO _COPY_JAR: %_COPY_JAR% "%_DESTIN_JAR%%_PROJ_%%_VERSION_%.jar" %_TARGET_CLASSES%
 CALL %_COPY_JAR% "%_DESTIN_JAR%%_PROJ_%%_VERSION_%.jar" %_TARGET_CLASSES%
)
IF EXIST "%_SOURCE_JAR%lib" (
 PUSHD "%_SOURCE_JAR%lib"
 FOR %%j IN ("*.jar") DO (
  POPD
  ECHO ^>^>^> SHARED-CLASSES:%%j ^<^<^<
  CALL %REMOVE% "%_PATH_CLASSES%%%j"
  ECHO _COPY_JAR: %_COPY_JAR% "%_DESTIN_JAR%lib%_SEP%%%j" %_TARGET_CLASSES%
  CALL %_COPY_JAR% "%_DESTIN_JAR%lib%_SEP%%%j" %_TARGET_CLASSES%
  PUSHD "%_SOURCE_JAR%lib"
 )
 POPD
)
@REM FOR %%i IN ("%_DEPLOY_CLASSES%") DO (
@REM  IF EXIST %%~si\NUL (
ECHO _CHMOD_CLASSES: %_CHMOD_CLASSES%
CALL %_CHMOD_CLASSES%
ECHO _SHELL_JAR: %_DEPLOY_CLASSES%
CALL %_DEPLOY_CLASSES%
@REM  )
@REM )
GOTO :EOF

:JPLUGIN
ECHO ^>^>^> JPLUGIN ^<^<^<
ECHO _MKDIR_JPLUGIN: %_MKDIR_JPLUGIN%
CALL %_MKDIR_JPLUGIN%
ECHO _REMOVE_JPLUGIN: %_REMOVE_JPLUGIN%
CALL %_REMOVE_JPLUGIN%
ECHO _COPY_JAR: %_COPY_JAR% "%_DESTIN_JAR%%_PROJ_%%_VERSION_%.jar" %_TARGET_JPLUGIN%
CALL %_COPY_JAR% "%_DESTIN_JAR%%_PROJ_%%_VERSION_%.jar" %_TARGET_JPLUGIN%
@REM IF EXIST "%_SOURCE_JAR%lib" (
@REM  PUSHD "%_SOURCE_JAR%lib"
@REM  FOR %%j IN ("*.jar") DO (
@REM   POPD
@REM   ECHO ^>^>^> JPLUGIN:%%j ^<^<^<
@REM   CALL %REMOVE% "%_PATH_JPLUGIN%%%j"
@REM   ECHO _COPY_JAR: %_COPY_JAR% "%_DESTIN_JAR%lib%_SEP%%%j" %_TARGET_JPLUGIN%
@REM   CALL %_COPY_JAR% "%_DESTIN_JAR%lib%_SEP%%%j" %_TARGET_JPLUGIN%
@REM   PUSHD "%_SOURCE_JAR%lib"
@REM  )
@REM  POPD
@REM )
@REM FOR %%i IN ("%_DEPLOY_JPLUGIN%") DO (
@REM  IF EXIST %%~si (
  ECHO _CHMOD_JPLUGIN: %_CHMOD_JPLUGIN%
  CALL %_CHMOD_JPLUGIN%
  ECHO _SHELL_JAR: %_DEPLOY_JPLUGIN%
  CALL %_DEPLOY_JPLUGIN%
@REM  )
@REM )
GOTO :EOF

:BAR
ECHO ^>^>^> BAR ^<^<^<
ECHO %_MKDIR_BAR%
CALL %_MKDIR_BAR%
ECHO %_COPY_BAR% %_SOURCE_BAR%%_PROJ_%%_ENVN_%%_INST_%.bar %_TARGET_BAR%
CALL %_COPY_BAR% %_SOURCE_BAR%%_PROJ_%%_ENVN_%%_INST_%.bar %_TARGET_BAR%
ECHO %_CHMOD_BAR%
CALL %_CHMOD_BAR%
ECHO %_DEPLOY_BAR%
CALL %_DEPLOY_BAR%
GOTO :EOF


:FINISH