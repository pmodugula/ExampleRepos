@REM com.sun.jndi.fscontext.RefFSContextFactory
SET "_KEEP_COMMENTED_LINES=Y"
@REM SET "_KEEP_BLANK_LINES=Y"
FOR /F "tokens=1" %%b IN ('type "..\source\%2\jmsc%_ENVN_%.txt"') DO (
 CALL ..\source\%2\JMSAdmin-config%_ENVN_%-env.bat
 CALL perl template.pl ..\source\%2\JMSAdmin-config.template ..\source\%2\template-default.properties ..\source\%2\template%_ENVN_%.properties < ..\resources\credentials%_ENVN_%.properties > ..\source\%2\JMSAdmin%_ENVN_%%_INST_%.config
 CALL perl template.pl ..\source\%2\jmsadmin.template ..\source\%2\template-default.properties ..\source\%2\template%_ENVN_%.properties < nul > ..\source\%2\jmsadmin%_ENVN_%%_INST_%.jmsc
)
