@ECHO OFF
@REM File: any-object-sys-subscriber\pre-build.bat
@REM Version: 0.1.1
@REM Date: 13-May 2015
@REM Author: Alex Russell
@REM Comment [0.1.1] Added :REGEXP

TYPE ..\source\any-object-sys-subscriber\template%_ENVN_%%_INST_%.properties|perl envvar.pl > .env1.bat
CALL .env1.bat & ERASE /q .env1.bat
ECHO %1|perl envvar.pl > .env2.bat
CALL .env2.bat & ERASE /q .env2.bat
CALL :CLONE any-object-sys-subscriber

GOTO :FINISH


:CLONE
@ECHO OFF
PUSHD "%~dp0"
cd ..\..\source\
ECHO CVS > exclude.txt
RMDIR /s /q %__any__%-%__object__%-%__sys__%-subscriber 1>nul 2>nul
MKDIR %__any__%-%__object__%-%__sys__%-subscriber
XCOPY /e /i %1 %__any__%-%__object__%-%__sys__%-subscriber /EXCLUDE:exclude.txt
ERASE exclude.txt
MOVE /Y %__any__%-%__object__%-%__sys__%-subscriber\any\object\sys %__any__%-%__object__%-%__sys__%-subscriber\any\object\%__sys__%
MOVE /Y %__any__%-%__object__%-%__sys__%-subscriber\any\object %__any__%-%__object__%-%__sys__%-subscriber\any\%__object__%
MOVE /Y %__any__%-%__object__%-%__sys__%-subscriber\any %__any__%-%__object__%-%__sys__%-subscriber\%__any__%
CD %__any__%-%__object__%-%__sys__%-subscriber\
ERASE /q build-env.bat
ERASE /q pre-build-LOGICAL.bat
ERASE runmqsc*.mqsc
ERASE %__any__%-%__object__%-%__sys__%-subscriber*.properties

CALL :REGEXP .project
CALL :REGEXP mqsiapplybaroverride.template
CALL :REGEXP runmqsc.template
CALL :REGEXP template-default.properties
CALL :REGEXP template-DV.properties
CALL :REGEXP template-QA.properties
CALL :REGEXP template-PP.properties
CALL :REGEXP template.properties

CD %__any__%\%__object__%\%__sys__%

CALL :REGEXP subscriber.msgflow

POPD
GOTO :EOF


:REGEXP
TYPE %1|perl -e "while(<>){while(m/\$\{_(\S+?)_\}/){my$a='_'.$1.'_';s/\$\{_(\S+?)_\}/$ENV{$a}/}print$_}" > %1~
COPY %1~ %1 & pause & ERASE %1~
GOTO :EOF


:FINISH
EXIT /b
