@ECHO OFF
@REM File: build\iibbuild.bat
@REM Version: 0.2.9
@REM Date: 17-Apr 2015
@REM Author: Alex Russell, Navin Khanna
@REM Comment: [0.2.9] Added support for any-object-sys in collection .dat file

SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION
SET _NO_DRY_RUN=1

SET _EC=0
SET RC=0
GOTO :MAIN

:USAGE
ECHO iibbuild ^<project^> ^<collection^> [^<environment^>] [^<instance^>] [/force]
ECHO For example: iibbuild * local
ECHO For example: iibbuild * caldc DV
ECHO For example: iibbuild * IBNQ1 QA
ECHO For example: iibbuild doc-transfer-* caldc PP /force
ECHO For example: iibbuild doc-transfer-pmm-publisher caldc PP /force
ECHO For example: iibbuild doc-transfer-*-wms-subscriber caldc PP /force
SET RC=1
GOTO :FINISH2

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

MKDIR "..\%_2%-build" 1>nul 2>nul
FOR /F "tokens=1* delims=:" %%a IN ('type "..\resources\%_2%%_ENVN_%%_INST_%.dat"^|FINDSTR /V "^@REM"^|FINDSTR /V "^#"') DO (
 @REM ECHO %%a|perl -e "$a=<>;$a=~s/(.*\S)\s*/$1/;$_1=$ENV{_1};$_1=~s/\*/\.\*/g;if($a=~m/^$_1$/){exit 0}else{exit 1}"
 ECHO %%a|perl -e "$a=<>;$a=~s/(\S*)\s.*/$1/;$_1=$ENV{_1};$_1=~s/\*/\.\*/g;if($a=~m/^$_1$/){exit 0}else{exit 1}"
 IF !ERRORLEVEL! EQU 0 (
  CALL :BUILD %%a "%%b"
 )
)

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
SET "_message=project '%_a%' for collection '%_2%' processing.."
SET _b=1
CALL :STATUS
CALL BUILD.bat %_2% %_a% %2
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
 SET "_message=project '%_1%' for collection '%_2%' was built [PASS]"
 CALL :STATUS
) ELSE (
 SET EC=1
 SET "_message=project '%_1%' for collection '%_2%' was not built [FAIL]"
 CALL :STATUS
)
:FINISH2
IF NOT "x1" == "x%_DONT_EXIT%" (
 EXIT /B !EC!
)