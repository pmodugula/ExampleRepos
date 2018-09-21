@REM File: iibcreatebar.bat
@REM Version: 0.1.3
@REM Date: 9-Oct 2014
@REM Author: Navin Khanna

SETLOCAL
@REM ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION

SET "_1=%1"

CALL "..\resources\%USERNAME%-env.bat"

MKDIR "%_1%" 1>nul 2>nul
ECHO CVS > %_1%\xcopy-exclude.cvs
xcopy /E /I /Y ..\source\%_1% %_1% /EXCLUDE:..\build\%_1%\xcopy-exclude.cvs 1>nul 2>nul
ERASE /Q %_1%\xcopy-exclude.cvs
@REM CALL mqsicreatebar -data ..\source -compileOnly -p %_1%
PUSHD "%_1%"
CALL mqsicreatebar-env.bat
CALL %_JAVAC% %_JAVAC_ARGS%
IF NOT "x%_COPY_CLASSES%" == "x" (
 CALL %_COPY_CLASSES%
 SET "_COPY_CLASSES="
)
CALL %_JAR% %_JAR_ARGS%
IF NOT "x%_REMOVE_CLASSES%" == "x" (
 CALL %_REMOVE_CLASSES%
 SET "_REMOVE_CLASSES="
)
POPD

ENDLOCAL