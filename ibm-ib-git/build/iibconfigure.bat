@ECHO OFF
@REM File: build\iibconfigure.bat
@REM Version: 0.2.9
@REM Date: 14-Nov 2014
@REM Author: Alex Russell, Navin Khanna
@REM Comment: added support for /mqsc flag..

SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION
SET _NO_DRY_RUN=1

SET _EC=0
SET RC=0
GOTO :MAIN

:USAGE
ECHO iibconfigure ^<project^> ^<collection^> [^<environment^>] [^<instance^>] [/force|/mqsc|/jmsc]
ECHO For example: iibconfigure * local
ECHO For example: iibconfigure * caldc DV
ECHO For example: iibconfigure * IBNQ1 QA
ECHO For example: iibconfigure doc-transfer-* caldc PP /force
ECHO For example: iibconfigure doc-transfer-pmm-publisher caldc PP /force
ECHO For example: iibconfigure doc-transfer-*-wms-subscriber caldc PP /force
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

CALL "C:\eai\ibm-ib\resources\%USERNAME%-env.bat"

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

CALL "..\resources\%_2%%_ENVN_%%_INST_%%_ENV_%.bat"
IF %ERRORLEVEL% NEQ 0 (
 SET "_message=collection '%_2%' settings do not exist: ..\resources\%_2%%_ENVN_%%_INST_%%_ENV_%.bat"
 CALL :ERROR
 SET RC=2
 GOTO :FINISH
)

FOR %%i IN ("..\%_2%-build") DO (
 IF EXIST %%~si\NUL (
  FOR /F "tokens=1* delims=:" %%a IN ('type "..\resources\%_2%%_ENVN_%%_INST_%.dat"^|FINDSTR /V "^@REM"^|FINDSTR /V "^#"') DO (
   ECHO %%a|perl -e "$a=<>;$a=~s/(.*\S)\s*/$1/;$_1=$ENV{_1};$_1=~s/\*/\.\*/g;if($a=~m/^$_1$/){exit 0}else{exit 1}"
   IF !ERRORLEVEL! EQU 0 (
    CALL :CONFIGURE %%a
   )
  )
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

:CONFIGURE
SET "_a=%1"
SET "_message=project '%_a%' for collection '%_2%' processing.."
SET _b=1
CALL :STATUS
CALL CONFIGURE.bat %_2% %_a%
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
 SET "_message=project '%_1%' for collection '%_2%' was configured [PASS]"
 CALL :STATUS
) ELSE (
 SET EC=1
 SET "_message=project '%_1%' for collection '%_2%' was not configured [FAIL]"
 CALL :STATUS
)
:FINISH2
IF NOT "x1" == "x%_DONT_EXIT%" (
 EXIT /B !EC!
)
