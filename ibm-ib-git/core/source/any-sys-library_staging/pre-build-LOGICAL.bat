@ECHO OFF
@REM File: any-sys-library_staging\pre-build.bat
@REM Version: 0.1.1
@REM Date: 9-Sep 2015
@REM Author: Alex Russell
@REM Comment [0.1.1] Added :REGEXP

TYPE ..\source\any-sys-library_staging\template%_ENVN_%%_INST_%.properties|perl envvar.pl > .env1.bat
CALL .env1.bat & ERASE /q .env1.bat
ECHO %1|perl envvar.pl > .env2.bat
CALL .env2.bat & ERASE /q .env2.bat
CALL :CLONE any-sys-library_staging

GOTO :FINISH


:CLONE
@ECHO OFF
PUSHD "%~dp0"
cd ..\..\source\
ECHO CVS > exclude.txt
RMDIR /s /q %__any__%-%__sys__%-library 1>nul 2>nul
MKDIR %__any__%-%__sys__%-library
XCOPY /e /i %1 %__any__%-%__sys__%-library /EXCLUDE:exclude.txt
ERASE exclude.txt
MOVE /Y %__any__%-%__sys__%-library\any\sys %__any__%-%__sys__%-library\any\%__sys__%
MOVE /Y %__any__%-%__sys__%-library\any %__any__%-%__sys__%-library\%__any__%
CD %__any__%-%__sys__%-library\
ERASE /q build-env.bat
ERASE /q pre-build-LOGICAL.bat

CALL :REGEXP .project

CD %__any__%\%__sys__%

CALL :REGEXP _inbound.subflow
MOVE /Y Object.subflow %_Object__%.subflow
CALL :REGEXP %_Object__%.subflow
MOVE /Y sys.esql %__sys__%.esql
CALL :REGEXP %__sys__%.esql

CD insert

MOVE /Y InsertIntoObject.subflow InsertInto%_Object__%.subflow
CALL :REGEXP InsertInto%_Object__%.subflow
MOVE /Y Object.esql %_Object__%.esql
CALL :REGEXP %_Object__%.esql

POPD
GOTO :EOF


:REGEXP
TYPE %1|perl -e "while(<>){while(m/\$\{_(\S+?)_\}/){my$a='_'.$1.'_';s/\$\{_(\S+?)_\}/$ENV{$a}/}print$_}" > %1~
COPY %1~ %1 & pause & ERASE %1~
GOTO :EOF


:FINISH
EXIT /b
