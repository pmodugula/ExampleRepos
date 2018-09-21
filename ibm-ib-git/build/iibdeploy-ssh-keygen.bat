@ECHO OFF
@REM File: build\iibdeploy-ssh-keygen.bat
@REM Version: 0.1.5
@REM Date: 28-May 2015
@REM Author: Alex Russell
@REM Comment: [0.1.4] Changed path for id RSA output
@REM Comment: [0.1.5] Changed HOME environment variables

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

SET "__SHELL=..\cygmin64\bin\ssh.exe %_USERNAME%@%_HOSTNAME%"
%__SHELL% 'chmod go-w $HOME;if [ ^! -f "$HOME/.ssh/id_rsa" ];then ssh-keygen -f "$HOME/.ssh/id_rsa" -N "";cd "$HOME/.ssh";cat id_rsa.pub ^>^> authorized_keys;chmod 644 authorized_keys;fi;cat "$HOME/.ssh/id_rsa"' > C:\eai\ibm-ib\resources\%_USERNAME%-%_HOSTNAME%-id_rsa
@REM chmod 600 /cygdrive/c/eai/ibm-ib/resources/%_USERNAME%-%_HOSTNAME%-id_rsa
