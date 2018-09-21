@REM File: MQSI_RESTART.bat
@REM Date: 30-Oct 2014
@REM Version: 0.1.0
@REM Author: Alex Russell

@REM For example: CALL MQSI_RESTART.bat <node> * <collection> <environment> <instance> /stop
@REM For example: CALL MQSI_RESTART.bat <node> * <collection> <environment> <instance> /start

SET "_NODE=%1"
SET "_PROJ=%2"
SET "_COLL=%3"
IF NOT "x%4" == "x/stop" (
 IF NOT "x%4" == "x/start" (
  SET "_ENVN=%4"
 ) ELSE (
  SET "_MODE=%4"
 )
) ELSE (
 SET "_MODE=%4"
)
IF NOT "x%5" == "x/stop" (
 IF NOT "x%5" == "x/start" (
  SET "_INST=%5"
 ) ELSE (
  SET "_MODE=%5"
 )
) ELSE (
 SET "_MODE=%5"
)
IF NOT "x" == "x%6" (
 SET "_MODE=%6"
)

SET "_FLAG=0"
IF "x1" == "x%_MQSI_RESTART%" (
 SET "_FLAG=1"
) ELSE (
 FOR %%i IN ("..\%_COLL%-build") DO (
  IF EXIST "%%~si\NUL" (
   FOR /F "tokens=1* delims=:" %%a IN ('type "..\resources\%_COLL%%_ENVN_%%_INST_%.dat"^|FINDSTR /V "^@REM"^|FINDSTR /V "^#"') DO (
    ECHO %%a|perl -e "$a=<>;$a=~s/(.*\S)\s*/$1/;$_a=$ENV{_PROJ};$_a=~s/\*/\.\*/g;if($a=~m/$_a/){exit 0}else{exit 1}"
    IF !ERRORLEVEL! EQU 0 (
     @REM Eclipse Toolkit dropins - For example: C:\Program Files (x86)\IBM\IntegrationToolkit90\dropins
     FOR %%i IN ("%_ECLIPSE_DROPINS%") DO (
      IF EXIST "%%~si\NUL" (
       FOR %%j IN ("..\source\%%a\dropins.txt") DO (
        IF EXIST "%%~sj" (
         SET "_FLAG=1"
         GOTO :ENDFORS
        )
       )
      )
     )
     @REM Integration Bus shared-classes
     FOR %%i IN ("..\source\%%a\shared-classes.txt") DO (
      IF EXIST "%%~si" (
       SET "_FLAG=1"
       GOTO :ENDFORS
      )
     )
     @REM Integration Bus JPlugin (On Windows As Administrator)
     FOR %%i IN ("..\source\%%a\jplugin.txt") DO (
      IF EXIST "%%~si" (
       SET "_FLAG=1"
       GOTO :ENDFORS
      )
     )
    )
   )
  )
 )
)
:ENDFORS

IF "x%_MODE%" == "x/stop" (
 IF !_FLAG! EQU 1 (
  ECHO ^>^>^> Automatically Stop Broker^[%_NODE%^] ^<^<^<
  pause
  %SHELL_BRKADM% "mqsistop %_NODE%"
  SET "_MQSI_RESTART_=1"
 )
)

IF "x%_MODE%" == "x/start" (
 IF !_FLAG! EQU 1 (
  ECHO ^>^>^> Automatically Start Broker^[%_NODE%^] ^<^<^<
  %SHELL_BRKADM% "mqsistart %_NODE%"
 )
)