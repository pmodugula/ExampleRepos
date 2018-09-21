@ECHO OFF
@REM File: build\iibapplybaroverride.bat
@REM Version: 0.1.5
@REM Date: 28-Nov 2014
@REM Author: Alex Russell
@REM Comment: now using notion of 'working' directory as initial BAR now is same as Production BAR (never was until first attempt to deploy to Production)

SETLOCAL ENABLEEXTENSIONS

@REM For example: %1 => core-batch-initiator
@REM SET "_WORKPATH=..\source"
@REM For example: %2 => DV
@REM For example: %3 => 2

SET "_1=%1"
SET "_ENVN="
SET "_INST="
IF NOT "x%2" == "x" (
 SET "_ENVN=%2"
 IF NOT "x%3" == "x" (
  SET "_INST=%3"
 )
)

IF NOT "x%_ENVN%" == "x" (
 SET "_ENVN=-%_ENVN%"
)

IF "!_INST!" == "0" (
 SET "_INST="
)
IF "!_INST!" == "1" (
 SET "_INST="
)
IF NOT "x%_INST%" == "x" (
 SET "_INST=-%_INST%"
)

MKDIR "..\working" 1>nul 2>nul
COPY /Y %_1%\%_1%.bar ..\working\%_1%%_ENVN%%_INST%.bar
@REM IF NOT "x%_ENVN%" == "x" (
 CALL mqsiapplybaroverride -b ..\working\%_1%%_ENVN%%_INST%.bar -k %_1% -p ..\source\%_1%\%_1%%_ENVN%%_INST%.properties
@REM )
MKDIR "..\deployment\%_1%" 1>nul 2>nul
MOVE /Y ..\working\%_1%%_ENVN%%_INST%.bar ..\deployment\%_1%\
@REM RMDIR "..\working" 1>nul 2>nul

ENDLOCAL