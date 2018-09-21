@ECHO OFF
@REM File: build\iibclean.bat
@REM Version: 0.2.5
@REM Date: 10-Nov 2014
@REM Author: Alex Russell and Navin Khanna
@REM Comment: Also clean build\<project> and deployment\<project> folders for good measure
@REM Comment: Split CLEAN_HANLDER into ERASE_HANDLER and RMDIR_HANDLER

SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION
SET _NO_DRY_RUN=1

SET _EC=0
SET RC=0
GOTO :MAIN

:USAGE
ECHO iibclean ^<project^> ^<collection^> ^<environment^> [^<instance^>]
ECHO For example: iibclean * caldc PP
ECHO For example: iibclean doc-transfer-* caldc PP
ECHO For example: iibclean doc-transfer-pmm-publisher caldc PP
ECHO For example: iibclean doc-transfer-*-wms-subscriber caldc PP
SET RC=1
GOTO :FINISH

:MAIN
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
SET "_ENVN=%_3%"
SET "_ENVN_=%_ENVN%"
SET "_ENV_=-env"
IF NOT "x%_ENVN_%" == "x" (
 SET "_ENVN_=-%_ENVN_%"
)

SET "_4=%4"
SET "_INST=%_4%"
SET "_INST_=%_INST%"
IF NOT "x%_INST_%" == "x" (
 SET "_INST_=-%_INST%"
)

CALL "..\resources\%_2%%_ENVN_%%_INST_%%_ENV_%.bat" 1>nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
 SET "_message=collection '%_2%' settings do not exist: ..\resources\%_2%%_ENVN_%%_INST_%%_ENV_%.bat"
 CALL :ERROR
 SET RC=2
 GOTO :FINISH
)

FOR %%i IN ("..\%_2%-build") DO (
 IF EXIST %%~si\NUL (
  FOR /F "tokens=1* delims=:" %%a IN ('type "..\resources\%_2%%_ENVN_%%_INST_%.dat"^|FINDSTR /V "^@REM"^|FINDSTR /V "^#"') DO (
   ECHO %%a|perl -e "$a=<>;$a=~s/(.*\S)\s*/$1/;$_1=$ENV{_1};$_1=~s/\*/\.\*/g;if($a=~m/$_1/){exit 0}else{exit 1}"
   IF !ERRORLEVEL! EQU 0 (
    CALL :CLEAN %%a
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

:ERASE_HANDLER
ERASE /S /Q "%1" 1>nul 2>nul
GOTO :EOF

:RMDIR_HANDLER
RMDIR /S /Q "%1" 1>nul 2>nul
GOTO :EOF

:CLEAN
SET "_a=%1"
SET "_message=project '%_a%' for collection '%_2%' processing.."
SET _b=1
CALL :STATUS
IF EXIST "..\build\%_a%" (
 CALL :RMDIR_HANDLER "..\build\%_a%"
 SET _b=!ERRORLEVEL!
)
IF EXIST "..\%_2%-build\%_a%" (
 CALL :ERASE_HANDLER "..\%_2%-build\%_a%"
 SET _b=!ERRORLEVEL!
)
IF EXIST "..\%_2%-build\%_a%.b" (
 CALL :ERASE_HANDLER "..\%_2%-build\%_a%.b"
 SET _b=!ERRORLEVEL!
)
IF EXIST "..\%_2%%_ENVN_%%_INST_%-configure\%_a%.c" (
 CALL :ERASE_HANDLER "..\%_2%%_ENVN_%%_INST_%-configure\%_a%.c"
 SET _b=!ERRORLEVEL!
)
IF EXIST "..\%_2%%_ENVN_%%_INST_%-deploy\%_a%" (
 CALL :ERASE_HANDLER "..\%_2%%_ENVN_%%_INST_%-deploy\%_a%"
 SET _b=!ERRORLEVEL!
)
IF EXIST "..\%_2%%_ENVN_%%_INST_%-deploy\%_a%.d" (
 CALL :ERASE_HANDLER "..\%_2%%_ENVN_%%_INST_%-deploy\%_a%.d"
 SET _b=!ERRORLEVEL!
)
IF EXIST "..\deployment\%_a%" (
 CALL :RMDIR_HANDLER "..\deployment\%_a%"
 SET _b=!ERRORLEVEL!
)
IF EXIST "..\source\%_a%\bar.txt" (
 FOR /F "tokens=1* delims=:" %%b IN ('type "..\source\%_a%\bar.txt"^|FINDSTR /V "^@REM"^|FINDSTR /V "^#"') DO (
  IF EXIST "..\%_2%-build\%%b" (
   CALL :ERASE_HANDLER "..\%_2%-build\%%b"
   SET _b=!ERRORLEVEL!
  )
 )
)
IF !_b! EQU 0 (
 SET "_message=project '%_a%' for collection '%_2%' was processed"
) ELSE (
 SET "_message=project '%_a%' for collection '%_2%' was not processed"
)
CALL :STATUS
GOTO :EOF

:FINISH
IF "%_EC%" == "%RC%" (
 SET EC=0
 SET "_message=project '%_1%' for collection '%_2%' cleaned [PASS]"
 CALL :STATUS
) ELSE (
 SET EC=1
 SET "_message=project '%_1%' for collection '%_2%' not cleaned [FAIL]"
 CALL :STATUS
)
EXIT /B %EC%