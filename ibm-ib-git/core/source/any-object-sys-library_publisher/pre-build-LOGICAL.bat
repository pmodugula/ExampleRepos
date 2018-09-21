@ECHO OFF
@REM File: any-object-sys-library_publisher\pre-build.bat
@REM Version: 0.1.1
@REM Date: 13-May 2015
@REM Author: Alex Russell
@REM Comment [0.1.1] Added :REGEXP

TYPE ..\source\any-object-sys-library_publisher\template%_ENVN_%%_INST_%.properties|perl envvar.pl > .env1.bat
CALL .env1.bat & ERASE /q .env1.bat
ECHO %1|perl envvar.pl > .env2.bat
CALL .env2.bat & ERASE /q .env2.bat
CALL :CLONE any-object-sys-library_publisher

GOTO :FINISH


:CLONE
@ECHO OFF
PUSHD "%~dp0"
cd ..\..\source\
ECHO CVS > exclude.txt
RMDIR /s /q %__any__%-%__object__%-%__sys__%-library 1>nul 2>nul
MKDIR %__any__%-%__object__%-%__sys__%-library
XCOPY /e /i %1 %__any__%-%__object__%-%__sys__%-library /EXCLUDE:exclude.txt
ERASE exclude.txt
MOVE /Y %__any__%-%__object__%-%__sys__%-library\any\object\sys %__any__%-%__object__%-%__sys__%-library\any\object\%__sys__%
MOVE /Y %__any__%-%__object__%-%__sys__%-library\any\object %__any__%-%__object__%-%__sys__%-library\any\%__object__%
MOVE /Y %__any__%-%__object__%-%__sys__%-library\any %__any__%-%__object__%-%__sys__%-library\%__any__%
CD %__any__%-%__object__%-%__sys__%-library\
ERASE /q build-env.bat
ERASE /q pre-build-LOGICAL.bat

CALL :REGEXP .project

CD %__any__%\%__object__%\%__sys__%

CALL :REGEXP _publisher.subflow
CALL :REGEXP _publisher.esql

POPD
GOTO :EOF


:REGEXP
TYPE %1|perl -e "while(<>){while(m/\$\{_(\S+?)_\}/){my$a='_'.$1.'_';s/\$\{_(\S+?)_\}/$ENV{$a}/}print$_}" > %1~
COPY %1~ %1 & pause & ERASE %1~
GOTO :EOF


:FINISH
EXIT /b
