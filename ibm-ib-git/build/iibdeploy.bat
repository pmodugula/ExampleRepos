@ECHO OFF
@REM File: build\iibdeploy.bat
@REM Version: 0.2.11
@REM Date: 26-Nov 2014
@REM Author: Alex Russell, Navin Khanna
@REM Comment: Also clean deployment\<project> folder for good measure (change is actually in DEPLOY.bat)
@REM Comment: [0.2.11] Added /mqsc support

SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION
SET _NO_DRY_RUN=1

SET _EC=0
SET RC=0
GOTO :MAIN

:USAGE
ECHO iibdeploy ^<project^> ^<collection^> [^<environment^>] [^<instance^>] [/force|/mqsc|/jmsc]
ECHO For example: iibdeploy * local
ECHO For example: iibdeploy * caldc DV
ECHO For example: iibdeploy * IBNQ1 QA
ECHO For example: iibdeploy doc-transfer-* caldc PP /force
ECHO For example: iibdeploy doc-transfer-pmm-publisher caldc PP /force
ECHO For example: iibdeploy doc-transfer-*-wms-subscriber caldc PP /force
SET RC=1
GOTO :FINISH

:MAIN
IF "x" == "x%MQSI_FILEPATH%" (
 ECHO Error: this command must be run in an Integration Bus Console!
 GOTO :FINISH2
)
SET "_1=%1"
IF "x/?" == "%_1%x" (
 GOTO :USAGE
)
IF "x/h" == "%_1%x" (
 GOTO :USAGE
)
IF "x" == "x%_1%" (
 GOTO :USAGE
)

SET "_2=%2"
IF "x" == "x%_2%" (
 GOTO :USAGE
)

SET "_3=%3"
SET "_4=%4"
SET "_5=%5"
SET _FORCE=0
SET _ONLY_MQSC=0
SET _ONLY_JMSC=0
IF NOT "x" == "x%_3%" (
 IF "%_3%" == "/force" (
  SET _FORCE=1
 ) ELSE (
  IF "%_3%" == "/mqsc" (
   SET _ONLY_MQSC=1
  ) ELSE (
   IF "%_3%" == "/jmsc" (
    SET _ONLY_JMSC=1
   )
  )
 )
 IF NOT "x" == "x%_4%" (
  IF "%_4%" == "/force" (
   SET _FORCE=1
  ) ELSE (
   IF "%_4%" == "/mqsc" (
    SET _ONLY_MQSC=1
   ) ELSE (
    IF "%_4%" == "/jmsc" (
     SET _ONLY_JMSC=1
    )
   )
  )
  IF NOT "x" == "x%_5%" (
   IF "%_5%" == "/force" (
    SET _FORCE=1
   ) ELSE (
    IF "%_5%" == "/mqsc" (
     SET _ONLY_MQSC=1
    ) ELSE (
     IF "%_5%" == "/jmsc" (
      SET _ONLY_JMSC=1
     )
    )
   )
  )
 )
)
SET "_ENV_=-env"
SET "_ENVN="
SET "_ENVN_="
IF NOT "x/force" == "x%_3%" (
 SET "_ENVN=!_3!"
 SET "_ENVN_=!_ENVN!"
 IF NOT "x!_ENVN_!" == "x" (
  SET "_ENVN_=-!_ENVN_!"
 )
)
SET "_INST="
SET "_INST_="
IF NOT "x/force" == "x%_4%" (
 SET "_INST=!_4!"
 SET "_INST_=!_INST!"
 IF NOT "x!_INST_!" == "x" (
  SET "_INST_=-!_INST!"
 )
)
IF "xlocalhost" == "x%_HOSTNAME%" (
 SET "SHELL_BRKADM=SHELL-localhost.bat"
) ELSE (
 SET "SHELL_BRKADM=SHELL_BRKADM.bat"
)

CALL "..\resources\%_2%%_ENVN_%%_INST_%%_ENV_%.bat"
IF %ERRORLEVEL% NEQ 0 (
 SET "_message=collection '%_2%' settings do not exist: ..\resources\%_2%%_ENVN_%%_INST_%%_ENV_%.bat"
 CALL :ERROR
 SET RC=2
 GOTO :FINISH
)

CALL MQSI_RESTART.bat %_NODE% %1 %2 %_ENVN% %_INST% /stop
SET "_MQSI_RESTART=0"
FOR %%i IN ("..\%_2%-build") DO (
 IF EXIST %%~si\NUL (
  FOR /F "tokens=1* delims=:" %%a IN ('type "..\resources\%_2%%_ENVN_%%_INST_%.dat"^|FINDSTR /V "^@REM"^|FINDSTR /V "^#"') DO (
   ECHO %%a|perl -e "$a=<>;$a=~s/(.*\S)\s*/$1/;$_1=$ENV{_1};$_1=~s/\*/\.\*/g;if($a=~m/^$_1$/){exit 0}else{exit 1}"
   IF !ERRORLEVEL! EQU 0 (
    CALL :DEPLOY %%a
   )
  )
 )
)
CALL MQSI_RESTART.bat %_NODE% %1 %2 %_ENVN% %_INST% /start

GOTO :FINISH

:STATUS
ECHO Status: !_message!
GOTO :EOF

:INFO
ECHO Info: !_message!
GOTO :EOF

:ERROR
ECHO Error: !_message!
GOTO :EOF

:DEPLOY
SET "_a=%1"
SET "_message=project '%_a%' for collection '%_2%' processing.."
SET _b=1
CALL :STATUS
CALL DEPLOY.bat %_2% %_a%
IF "x" == "x!EC!" SET EC=0
IF !EC! EQU 0 (
 SET "_message=project '%_a%' for collection '%_2%' was processed"
) ELSE (
 SET "_message=project '%_a%' for collection '%_2%' was not processed"
)
CALL :STATUS
GOTO :EOF

:FINISH
IF "%_EC%" == "%RC%" (
 SET EC=0
 SET "_message=project '%_1%' for collection '%_2%' was deployed [PASS]"
 CALL :STATUS
) ELSE (
 SET EC=1
 SET "_message=project '%_1%' for collection '%_2%' was not deployed [FAIL]"
 CALL :STATUS
)
:FINISH2
IF NOT "x1" == "x%_DONT_EXIT%" (
 EXIT /B !EC!
)