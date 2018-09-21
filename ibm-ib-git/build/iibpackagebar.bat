@ECHO OFF
@REM File: iibpackagebar.bat
@REM Version: 0.1.5
@REM Date: 17-Oct 2014
@REM Author: Alex Russell

SETLOCAL ENABLEEXTENSIONS

@REM For example: %1 => core-batch-initiator

SET "_1=%1"

MKDIR "%_1%" 1>nul 2>nul
IF EXIST "%_1%\mqsipackagebar-args.txt" (
 SET /P "_ARGS=" < %_1%\mqsipackagebar-args.txt
)
cd ..
CALL mqsipackagebar -a build\%_1%\%_1%.bar -w source -k %_1% %_ARGS%
cd build

ENDLOCAL