IF NOT "x" == "x%1" (
 IF NOT "x" == "x%2" (
  IF EXIST "..\source\%2\pre-deploy.bat" (
   CALL "..\source\%2\pre-deploy.bat" %1 %2
  )
  SET _FLAG=0
  IF EXIST "..\source\%2\bar.txt" (
   IF EXIST "..\%1%_ENVN_%%_INST_%-configure\%2.c" (
    SET _FLAG=1
   )
  ) ELSE (
   IF EXIST "..\%1%_ENVN_%%_INST_%-build\%2" (
    SET _FLAG=1
   ) ELSE (
    IF EXIST "..\%1%_ENVN_%%_INST_%-configure\%2" (
     SET _FLAG=1
    ) ELSE (
     SET __FLAG=0
     IF EXIST "..\source\%2\jplugin.txt" (
      SET __FLAG=1
     ) ELSE (
      IF EXIST "..\source\%2\shared-classes.txt" (
       SET __FLAG=1
      )
     )
     IF !__FLAG! EQU 1 (
      SET _FLAG=1
     )
    )
   )
  )
  IF !_FLAG! EQU 1 (
   IF !_FORCE! EQU 1 (
    SET _FLAG=2
   )
   IF EXIST "..\source\%2\bar.txt" (
    IF NOT EXIST "..\%1%_ENVN_%%_INST_%-deploy\%2.d" (
     SET _FLAG=2
    )
   ) ELSE (
    IF NOT EXIST "..\%1%_ENVN_%%_INST_%-deploy\%2" (
     SET _FLAG=2
    )
   )
  )
  IF !_FLAG! EQU 2 (
   cd ..\deployment
   IF EXIST "..\source\%2\bar.txt" (
    ERASE /Q "%1%_ENVN_%%_INST_%-deploy\%2.d" 1>nul 2>nul
   ) ELSE (
    ERASE /Q "%1%_ENVN_%%_INST_%-deploy\%2" 1>nul 2>nul
   )
   IF NOT "x" == "x%_NO_DRY_RUN%" (
    SET "_MQSC_SWITCH="
    IF %_ONLY_MQSC% EQU 1 (
     SET "_MQSC_SWITCH=/mqsc"
    )
    SET "_JMSC_SWITCH="
    IF %_ONLY_JMSC% EQU 1 (
     SET "_JMSC_SWITCH=/jmsc"
    )
    CALL iibdeploy.bat %_NODE% %2 %_ENVN% %_INST% %_MQSC_SWITCH%%_JMSC_SWITCH%
   )
   cd ..\build
   IF NOT EXIST "..\%1%_ENVN_%%_INST_%-deploy\NUL" (
    MKDIR "..\%1%_ENVN_%%_INST_%-deploy" 1>nul 2>nul
   )
   IF EXIST "..\source\%2\bar.txt" (
    ECHO. > "..\%1%_ENVN_%%_INST_%-deploy\%2.d"
   ) ELSE (
    ECHO. > "..\%1%_ENVN_%%_INST_%-deploy\%2"
   )
  )
  IF EXIST "..\source\%2\post-deploy.bat" (
   CALL "..\source\%2\post-deploy.bat"
  )
 )
)