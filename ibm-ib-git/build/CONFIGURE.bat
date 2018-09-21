IF NOT "x" == "x%1" (
 IF NOT "x" == "x%2" (
  IF NOT EXIST "..\source\%2\bar.txt" (
   IF EXIST "..\source\%2\pre-configure.bat" (
    CALL "..\source\%2\pre-configure.bat" %1 %2
   )
  ) ELSE (
   SET _FLAG=0
   IF EXIST "..\source\%2\bar.txt" (
    IF EXIST "..\%1-build\%2.b" (
     IF !_FORCE! EQU 1 (
      SET _FLAG=1
     )
     IF NOT EXIST "..\%1%_ENVN_%%_INST_%-configure\%2.c" (
      SET _FLAG=1
     )
    )
   ) ELSE (
    IF !_FORCE! EQU 1 (
     SET _FLAG=1
    )
   )   
   IF !_FLAG! EQU 1 (
    IF %_ONLY_MQSC% EQU 0 (
     ERASE /Q "..\%1%_ENVN_%%_INST_%-deploy\%2.d" 1>nul 2>nul
    )
    IF EXIST "..\source\%2\pre-configure.bat" (
     CALL "..\source\%2\pre-configure.bat" %1 %2
    )
@REM    IF %_ONLY_MQSC% EQU 1 (
     IF EXIST "..\source\%2\mqsc.txt" (
      SET /P "_FLAG=" < "..\source\%2\mqsc.txt"
      IF NOT "x" == "x!_FLAG!" (
       IF NOT EXIST "..\source\%2\runmqsc%_ENVN_%%_INST_%.template" (
        CALL perl template.pl ..\source\%2\runmqsc.template ..\source\%2\template-default.properties < ..\source\%2\template%_ENVN_%%_INST_%.properties > ..\source\%2\runmqsc%_ENVN_%%_INST_%.mqsc
       ) ELSE (
        CALL perl template.pl ..\source\%2\runmqsc%_ENVN_%%_INST_%.template ..\source\%2\template-default.properties < ..\source\%2\template%_ENVN_%%_INST_%.properties > ..\source\%2\runmqsc%_ENVN_%%_INST_%.mqsc
       )
      )
     )
@REM    )
@REM    IF %_ONLY_JMSC% EQU 1 (
     IF EXIST "..\source\%2\jmsc.txt" (
      SET /P "_FLAG=" < "..\source\%2\jmsc.txt"
      IF NOT "x" == "x!_FLAG!" (
       IF NOT EXIST "..\source\%2\jmsadmin%_ENVN_%%_INST_%.template" (
        CALL perl template.pl ..\source\%2\jmsadmin.template ..\source\%2\template-default.properties < ..\source\%2\template%_ENVN_%%_INST_%.properties > ..\source\%2\jmsadmin%_ENVN_%%_INST_%.jmsc
       ) ELSE (
        CALL perl template.pl ..\source\%2\jmsadmin%_ENVN_%%_INST_%.template ..\source\%2\template-default.properties < ..\source\%2\template%_ENVN_%%_INST_%.properties > ..\source\%2\jmsadmin%_ENVN_%%_INST_%.jmsc
       )
      )
     )
@REM    )
    IF %_ONLY_MQSC% EQU 0 ( 
     IF %_ONLY_JMSC% EQU 0 ( 
      IF EXIST "..\source\%2\mqsiapplybaroverride.template" (
       CALL perl template.pl ..\source\%2\mqsiapplybaroverride.template ..\source\%2\template-default.properties < ..\source\%2\template%_ENVN_%%_INST_%.properties > ..\source\%2\%2%_ENVN_%%_INST_%.properties
      )
      IF NOT "x" == "x%_NO_DRY_RUN%" (
       CALL iibapplybaroverride.bat %2 %_ENVN% %_INST%
      )
      IF NOT EXIST "..\%1%_ENVN_%%_INST_%-configure\NUL" (
       MKDIR "..\%1%_ENVN_%%_INST_%-configure" 1>nul 2>nul
      )
      ECHO. > "..\%1%_ENVN_%%_INST_%-configure\%2.c"
     )
    )
    IF EXIST "..\source\%2\post-configure.bat" (
     CALL "..\source\%2\post-configure.bat" %1 %2
    )
   )
  )
  IF NOT EXIST "..\source\%2\bar.txt" (
   IF EXIST "..\source\%2\post-configure.bat" (
    CALL "..\source\%2\post-configure.bat" %1 %2
   )
  )
 )
)