FOR /F "tokens=1,2" %%b IN ('type "..\source\%2\mqsc.txt"') DO (
 FOR /F "tokens=*" %%n IN ('type "..\source\%2\mqsc%_HOSTNAME_%-qmgrs.txt"') DO (
  SET "_QMGR=%%n"
  IF NOT EXIST "..\source\%2\%%b%_ENVN_%.txt" (
   CALL perl template.pl ..\source\%2\%%b.template ..\source\%2\template%_ENVN_%.properties < nul > ..\source\%2\%%b-%%n%%c
  ) ELSE (
   IF EXIST "..\source\%2\%%b-%%n%%c" (
    ERASE /Q ..\source\%2\%%b-%%n%%c 1>nul 2>nul
   )
   FOR /F "tokens=1,2" %%u IN ('type "..\source\%2\%%b%_ENVN_%.txt"') DO (
    SET "_USER=%%u"
    SET "_GROUP=%%v"
    CALL perl template.pl ..\source\%2\%%b.template ..\source\%2\template%_ENVN_%.properties < nul >> ..\source\%2\%%b-%%n%%c
   )
  )
 )
)
