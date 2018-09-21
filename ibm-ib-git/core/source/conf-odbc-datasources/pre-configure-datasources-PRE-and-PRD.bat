CALL perl ..\..\build\template.pl odbc-Oracle.template DSNSTG.properties template%_ENVN_%.properties < ..\..\resources\credentials%_ENVN_%.properties > ..\..\source\%2\odbc-STG%ENVN%.ini
CALL perl ..\..\build\template.pl odbc-Oracle.template DSNAUD.properties template%_ENVN_%.properties < ..\..\resources\credentials%_ENVN_%.properties > ..\..\source\%2\odbc-AUD%ENVN%.ini
CALL perl ..\..\build\template.pl odbc-Oracle-EnableNCharSupport.template DSNPMM.properties template%_ENVN_%.properties < ..\..\resources\credentials%_ENVN_%.properties > ..\..\source\%2\odbc-PMM%ENVN%.ini
CALL perl ..\..\build\template.pl odbc-DB2.template DSNWMS-CDC.properties template%_ENVN_%.properties < ..\..\resources\credentials%_ENVN_%.properties > ..\..\source\%2\odbc-WMS%ENVN%-CDC.ini
CALL perl ..\..\build\template.pl odbc-DB2.template DSNWMS-MDC.properties template%_ENVN_%.properties < ..\..\resources\credentials%_ENVN_%.properties > ..\..\source\%2\odbc-WMS%ENVN%-MDC.ini
CALL perl ..\..\build\template.pl db2cli.template DSNWMS-CDC.properties template%_ENVN_%.properties < ..\..\resources\credentials%_ENVN_%.properties > ..\..\source\%2\db2cli-WMS%ENVN%-CDC.ini
CALL perl ..\..\build\template.pl db2cli.template DSNWMS-MDC.properties template%_ENVN_%.properties < ..\..\resources\credentials%_ENVN_%.properties > ..\..\source\%2\db2cli-WMS%ENVN%-MDC.ini
