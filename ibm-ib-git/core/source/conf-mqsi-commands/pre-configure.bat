FOR /F "tokens=1" %%b IN ('type "..\source\%2\mqsi.txt"') DO (
 FOR /F "tokens=1" %%h IN ('type "..\source\%2\mqsi%_ENVN_%-hosts.txt"') DO (
  SET "_HOSTNAME=%%h"
  FOR /F "tokens=1" %%n IN ('type "..\source\%2\mqsi-!_HOSTNAME!-nodes.txt"') DO (
   SET "_NODE=%%n"
   IF NOT EXIST "..\source\%2\%%b%_ENVN_%.template" (
    CALL perl template.pl ..\source\%2\%%b.template ..\source\%2\template%_ENVN_%.properties < ..\resources\credentials%_ENVN_%.properties > ..\source\%2\%%b-%%n.sh
   ) ELSE (
    CALL perl template.pl ..\source\%2\%%b%_ENVN_%.template ..\source\%2\template%_ENVN_%.properties < ..\resources\credentials%_ENVN_%.properties > ..\source\%2\%%b-%%n.sh
   )
  )
 )
)
