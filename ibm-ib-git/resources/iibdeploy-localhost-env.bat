@REM File: resources\iibdeploy-localhost-env.bat
@REM Version: 0.1.1
@REM Date: 27-Oct
@REM Author: Alex Russell

SET "_COPY=copy /Y"
SET "_COPY_BAR=%_COPY%"
SET "_SOURCE_BAR=..\deployment\%_PROJ_%\"
SET "_PATH_BAR=%_PROJ_%"
SET "_TARGET_BAR=%_PATH_BAR%"
SET "_SHELL=cmd /e:on /c"
SET "_SHELL_BRKADM=%_SHELL%"
SET "_SHELL_BAR="
SET "_DEPLOY_BAR=mqsideploy %_NODE% -e %_SERVER% -a %_PATH_BAR%\%_PROJ_%%_ENVN%%_INST%.bar"

SET "_SEP=\"
SET "_REMOVE=erase /Q"
SET "_REMOVE_JAR=%_REMOVE%"
SET "_SOURCE_JAR=..\build\%_PROJ_%\"
SET "_DESTIN_JAR=..\build\%_PROJ_%\"
SET "_COPY_JAR=%_COPY%"
SET "_PATH_CLASSES=%ProgramData%\IBM\MQSI\shared-classes\"
SET "_TARGET_CLASSES=^"%_PATH_CLASSES%^""
SET "_REMOVE_CLASSES=%_REMOVE_JAR% ^"%_PATH_CLASSES%%_PROJ_%*.jar^""
SET "_PATH_JPLUGIN=%MQSI_FILEPATH%\jplugin\"
SET "_TARGET_JPLUGIN=^"%_PATH_JPLUGIN%^""
SET "_REMOVE_JPLUGIN=%_REMOVE_JAR% ^"%_PATH_JPLUGIN%%_PROJ_%*.jar^""
SET "_DEPLOY_CLASSES="
SET "_DEPLOY_JPLUGIN="