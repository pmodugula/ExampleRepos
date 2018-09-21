IF NOT EXIST "..\source\%2\template-default.properties" (
 CALL perl template.pl ..\source\%2\log4j-NODE-SERVER.template ..\source\%2\template%_ENVN_%%_INST_%.properties < nul > ..\source\%2\log4j-%_NODE%-%_SERVER%.properties
) ELSE (
 CALL perl template.pl ..\source\%2\log4j-NODE-SERVER.template ..\source\%2\template-default.properties ..\source\%2\template%_ENVN_%%_INST_%.properties < ..\source\%2\template%_ENVN_%%_INST_%.properties < ..\source\%2\template%_ENVN_%%_INST_%.properties > ..\source\%2\log4j-%_NODE%-%_SERVER%.properties
)
