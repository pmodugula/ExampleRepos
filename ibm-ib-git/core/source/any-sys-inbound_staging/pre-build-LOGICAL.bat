@ECHO OFF
@REM File: any-sys-inbound_staging\pre-build.bat
@REM Version: 0.1.1
@REM Date: 9-Sep 2015
@REM Author: Alex Russell
@REM Comment [0.1.1] Added :REGEXP

TYPE ..\source\any-sys-inbound_staging\template%_ENVN_%%_INST_%.properties|perl envvar.pl > .env1.bat
CALL .env1.bat & ERASE /q .env1.bat
ECHO %1|perl envvar.pl > .env2.bat
CALL .env2.bat & ERASE /q .env2.bat
CALL :CLONE any-sys-inbound_staging

GOTO :FINISH


:CLONE
@ECHO OFF
PUSHD "%~dp0"
cd ..\..\source\
ECHO CVS > exclude.txt
RMDIR /s /q %__any__%-%__sys__%-inbound 1>nul 2>nul
MKDIR %__any__%-%__sys__%-inbound
XCOPY /e /i %1 %__any__%-%__sys__%-inbound /EXCLUDE:exclude.txt
ERASE exclude.txt
MOVE /Y %__any__%-%__sys__%-inbound\any\sys %__any__%-%__sys__%-inbound\any\%__sys__%
MOVE /Y %__any__%-%__sys__%-inbound\any %__any__%-%__sys__%-inbound\%__any__%
CD %__any__%-%__sys__%-inbound\
ERASE /q build-env.bat
ERASE /q pre-build-LOGICAL.bat
ERASE runmqsc*.mqsc
ERASE %__any__%-%__sys__%-inbound*.properties

CALL :REGEXP .project
CALL :REGEXP mqsiapplybaroverride.template
CALL :REGEXP runmqsc.template
CALL :REGEXP template-default.properties
CALL :REGEXP template-DV.properties
CALL :REGEXP template-QA.properties
@REM CALL :REGEXP template-PP.properties
@REM CALL :REGEXP template.properties

CD %__any__%\%__sys__%

CALL :REGEXP inbound.msgflow
CALL :REGEXP inbound.esql

POPD
GOTO :EOF


:REGEXP
TYPE %1|perl -e "while(<>){while(m/\$\{_(\S+?)_\}/){my$a='_'.$1.'_';s/\$\{_(\S+?)_\}/$ENV{$a}/}print$_}" > %1~
COPY %1~ %1 & pause & ERASE %1~
GOTO :EOF


:FINISH
EXIT /b
