@ECHO OFF
@REM File: build\iibdeploy-scp.bat
@REM Version: 0.1.2
@REM Date: 28-May 2015
@REM Author: Alex Russell
@REM Comment: [0.1.1] Changed path for id RSA output
@REM Comment: [0.1.2] Changed HOME environment variables

SET "HOMEDRIVE=C:"
SET "HOMEPATH=\Users\%USERNAME%"
SET "HOME=%HOMEDRIVE%%HOMEPATH%"

@REM For example: %1=>10.103.1.162
@REM For example: %1=>10.100.10.197
@REM For example: %1=>10.100.10.218
@REM For example: %1=>10.100.10.219

SET "_1=%1"
CALL C:\eai\ibm-ib\resources\%USERNAME%-env.bat
CALL ..\resources\%_1%-env.bat
SET "_HOSTNAME=%_1%"

MKDIR ..\cygmin64\home 1>nul 2>nul
MKDIR ..\cygmin64\home\%USERNAME% 1>nul 2>nul

CALL ..\cygmin64\bin\scp.exe -i /cygdrive/c/eai/ibm-ib/resources/%_USERNAME%-%_HOSTNAME%-id_rsa %2 %_USERNAME%@%_HOSTNAME%:%3
