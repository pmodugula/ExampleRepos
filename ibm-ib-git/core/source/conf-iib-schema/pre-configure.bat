ERASE /s /q ..\source\%2\sqlplus%_ENVN_%%_INST_%.sql 1>nul 2>nul
CALL ..\resources\%USERNAME%-env.bat 
SET "_PATH_PLSQL=/home/%_USERNAME%/%2"
FOR /F "tokens=*" %%b IN ('type "..\source\%2\sql.txt"') DO (
 IF NOT EXIST "..\source\%2\template%_ENVN_%%_INST_%.properties" (
  CALL perl template.pl ..\source\%2\%%b.template ..\source\%2\template-default.properties < nul > ..\source\%2\%%b%_ENVN_%%_INST_%.sql
 ) ELSE (
  CALL perl template.pl ..\source\%2\%%b.template ..\source\%2\template-default.properties < ..\source\%2\template%_ENVN_%%_INST_%.properties > ..\source\%2\%%b%_ENVN_%%_INST_%.sql
 )
 IF !ERRORLEVEL! NEQ 0 GOTO :CONFIGURE2
 TYPE ..\source\%2\%%b%_ENVN_%%_INST_%.sql >> ..\source\%2\sqlplus%_ENVN_%%_INST_%.sql
 IF !ERRORLEVEL! NEQ 0 GOTO :CONFIGURE2
)
ECHO quit; >> ..\source\%2\sqlplus%_ENVN_%%_INST_%.sql
CALL perl template.pl ..\source\%2\sqlplus%_ENVN_%.template < nul > ..\source\%2\sqlplus%_ENVN_%%_INST_%.sh
IF !ERRORLEVEL! NEQ 0 GOTO :CONFIGURE2
ECHO. > "..\%1%_ENVN_%%_INST_%-configure\%2"
:CONFIGURE2
