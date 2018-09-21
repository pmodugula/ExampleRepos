@ECHO OFF
@REM File: build\iibbuildconfiguredeploy.bat
@REM Version: 0.2.7
@REM Date: 14-Nov 2014
@REM Author: Alex Russell, Navin Khanna

SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION
SET _NO_DRY_RUN=1

SET _EC=0
SET RC=0
GOTO :MAIN

:USAGE
ECHO iibbuildconfiguredeploy ^<project^> ^<collection^> [^<environment^>] [^<instance^>] [/force]
ECHO For example: iibbuildconfiguredeploy * local
ECHO For example: iibbuildconfiguredeploy * caldc PP
ECHO For example: iibbuildconfiguredeploy doc-transfer-* caldc PP
ECHO For example: iibbuildconfiguredeploy doc-transfer-pmm-publisher caldc PP
ECHO For example: iibbuildconfiguredeploy doc-transfer-*-wms-subscriber caldc PP
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
IF NOT "x" == "x%_3%" (
 IF "%_3%" == "/force" (
  SET _FORCE=1
 )
 IF NOT "x" == "x%_4%" (
  IF "%_4%" == "/force" (
   SET _FORCE=1
  )
  IF NOT "x" == "x%_5%" (
   IF "%_5%" == "/force" (
    SET _FORCE=1
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

CALL "..\resources\%_2%%_ENVN_%%_INST_%%_ENV_%.bat"
IF %ERRORLEVEL% NEQ 0 (
 SET "_message=collection '%_2%' settings do not exist: ..\resources\%_2%%_ENVN_%%_INST_%%_ENV_%.bat"
 CALL :ERROR
 SET RC=2
 GOTO :FINISH
)
SET EC=0

MKDIR "..\%_1%-build" 1>nul 2>nul
FOR /F "tokens=1* delims=:" %%a IN ('type "..\resources\%_2%%_ENVN_%%_INST_%.dat"^|FINDSTR /V "^@REM"^|FINDSTR /V "^#"') DO (
 ECHO %%a|perl -e "$a=<>;$a=~s/(.*\S)\s*/$1/;$_1=$ENV{_1};$_1=~s/\*/\.\*/g;if($a=~m/$_1/){exit 0}else{exit 1}"
 IF !ERRORLEVEL! EQU 0 (
  SET "_DONT_EXIT=1"
  CALL :BUILD %%a
  IF !EC! EQU 0 (
   SET "_DONT_EXIT=1"
   CALL :CONFIGURE %%a
  )
  IF !EC! EQU 0 (
   SET "_DONT_EXIT=1"
   CALL :DEPLOY %%a
  )
 )
)
SET "_DONT_EXIT=0"

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

:BUILD
SET "_a=%1"
SET "_message=project '%_a%' for collection '%_2%' building.."
SET _b=1
CALL :STATUS
CALL BUILD.bat %_2% %_a%
IF "x" == "x!EC!" SET EC=0
IF !EC! EQU 0 (
 SET "_message=project '%_a%' for collection '%_2%' was built"
) ELSE (
 SET "_message=project '%_a%' for collection '%_2%' was not built"
)
CALL :STATUS
GOTO :EOF

:CONFIGURE
SET "_a=%1"
SET "_message=project '%_a%' for collection '%_2%' configuring.."
SET _b=1
CALL :STATUS
CALL CONFIGURE.bat %_2% %_a%
IF !EC! EQU 0 (
 SET "_message=project '%_a%' for collection '%_2%' was configured"
) ELSE (
 SET "_message=project '%_a%' for collection '%_2%' was not configured"
)
CALL :STATUS
GOTO :EOF

:DEPLOY
SET "_a=%1"
SET "_message=project '%_a%' for collection '%_2%' deploying.."
SET _b=1
CALL :STATUS
CALL DEPLOY.bat %_2% %_a%
IF !EC! EQU 0 (
 SET "_message=project '%_a%' for collection '%_2%' was deployed"
) ELSE (
 SET "_message=project '%_a%' for collection '%_2%' was not deployed"
)
CALL :STATUS
GOTO :EOF

:FINISH
IF "%_EC%" == "%RC%" (
 SET EC=0
 SET "_message=project '%_1%' for collection '%_2%' was completed [PASS]"
 CALL :STATUS
) ELSE (
 SET EC=1
 SET "_message=project '%_1%' for collection '%_2%' was not completed [FAIL]"
 CALL :STATUS
)
:FINISH2
IF NOT "x1" == "x%_DONT_EXIT%" (
 EXIT /B !EC!
)