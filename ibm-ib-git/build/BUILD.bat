IF NOT "x" == "x%1" (
 IF NOT "x" == "x%2" (
  SET _SWITCH=0
  SET "_BUILD_TYPE="
  SET "_BUILD_TYPE_="
  IF EXIST "..\source\%2\build-env.bat" (
   CALL "..\source\%2\build-env.bat"
   IF NOT "x" == "x!_BUILD_TYPE!" (
    SET "_BUILD_TYPE_=-!_BUILD_TYPE!"
   )
  )
  IF EXIST "..\source\%2\pre-build!_BUILD_TYPE_!.bat" (
   CALL "..\source\%2\pre-build!_BUILD_TYPE_!.bat" %3
  )
  SET _FLAG=0
  IF NOT "x!_BUILD_TYPE!" == "xLOGICAL" (
   IF !_FORCE! EQU 1 (
    SET _FLAG=1
   )
   IF EXIST "..\source\%2\bar.txt" (
    IF NOT EXIST "..\%1-build\%2.b" (
     SET _FLAG=1
    )
   ) ELSE (
    IF NOT EXIST "..\%1-build\%2" (
     SET _FLAG=1
    )
   )
  )
  SET "_ACTION="
  IF !_FLAG! EQU 1 (
   IF EXIST "..\source\%2\dropins.txt" (
    SET "_ACTION=CREATE"
   )
   IF EXIST "..\source\%2\jplugin.txt" (
    SET "_ACTION=CREATE"
   )
   IF EXIST "..\source\%2\shared-classes.txt" (
    SET "_ACTION=CREATE"
   )
   IF EXIST "..\source\%2\bar.txt" (
    SET "_ACTION=PACKAGE"
   )
  )
  IF NOT "x" == "x!_ACTION!" (
   ERASE /Q "%2" 1>nul 2>nul
   IF EXIST "..\source\%2\bar.txt" (
    ERASE /Q "..\%1-build\%2.b" 1>nul 2>nul
   ) ELSE (
    ERASE /Q "..\%1-build\%2" 1>nul 2>nul
   )
  )
  SET _DONE=0
  IF !_DONE! EQU 0 (
   IF "x!_ACTION!" == "xCREATE" (
    IF EXIST "..\source\%2\mqsicreatebar-env.bat" (
     IF NOT "x" == "x%_NO_DRY_RUN%" (
      CALL iibcreatebar %2
     )
    )
    SET _DONE=1
   )
  )
  IF !_DONE! EQU 0 (
   IF "x!_ACTION!" == "xPACKAGE" (
    FOR /F "tokens=1* delims=:" %%b IN ('type "..\source\%2\bar.txt"^|FINDSTR /V "^@REM"^|FINDSTR /V "^#"') DO (
     SET _FLAG=0
     IF !_FORCE! EQU 1 (
      SET _FLAG=1
     )
     IF NOT EXIST "..\%1-build\%%b" (
      SET _FLAG=1
     )
     IF !_FLAG! EQU 1 (
      ERASE /Q "..\%1-build\%%b" 1>nul 2>nul
      ERASE /Q "%%b" 1>nul 2>nul
      IF NOT "x" == "x%_NO_DRY_RUN%" (
       IF !_BUILD_FOR_MQSIPACAKGEBAR! NEQ 1 (
        SET "_BUILD_FOR_MQSIPACAKGEBAR=1"
        IF EXIST "..\source\.metadata" (
         RMDIR /s /q ..\source\.metadata 1>nul 2>nul
        )
        IF NOT EXIST "..\source\.metadata" (
         CALL mqsicreatebar -data ..\source -compileOnly
        )
       )
       CALL mqsicreatebar -data ..\source -p %%b -compileOnly
      )
      ECHO. > "..\%1-build\%%b"
     )
    )
    IF NOT "x" == "x%_NO_DRY_RUN%" (
     CALL iibpackagebar.bat %2
    )
    SET "_DONE=1"
   )
  )
  IF !_DONE! NEQ 0 (
   IF EXIST "..\source\%2\bar.txt" (
    ERASE /Q "..\%1%_ENVN_%%_INST_%-configure\%2.c" 1>nul 2>nul
    ECHO. > "..\%1-build\%2.b"
   ) ELSE (
    ECHO. > "..\%1-build\%2"
   )
  )
  IF EXIST "..\source\%2\post-build!_BUILD_TYPE_!.bat" (
   CALL "..\source\%2\post-build!_BUILD_TYPE_!.bat"
  )
 )
)