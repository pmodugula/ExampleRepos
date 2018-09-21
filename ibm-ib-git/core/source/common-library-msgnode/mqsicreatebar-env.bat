@REM This file: common-library-msgnode\mqsicreatebar-env.bat

SET "_CLASSPATH=\common-library-jars-build\lib\jplugin2.jar;\common-library-jars-build\lib\ConfigManagerProxy.jar"
SET "_CLASSES=src\*.java"
@REM Remember to set _JAVA_HOME in %USERNAME%-env.bat
SET "_JAVAC=^"%_JAVA_HOME%javac^" -classpath ^"%_CLASSPATH%^""
SET "_JAVAC_ARGS=%_CLASSES% -d ."
@REM SET "_VERSION_=-0.0.1"
SET "_JAR=^"%_JAVA_HOME%jar^" -cvfm"
SET "_JAR_ARGS=%_1%%_VERSION_%.jar META-INF/MANIFEST.MF *.class generated icons HelpContexts.xml build.properties palette.properties palette.xmi plugin.xml EaiCoreLog4J.msgnode EaiCoreLog4J.properties"
SET "_COPY_CLASSES=COPY bin\*.class ."
SET "_REMOVE_CLASSES=erase /Q *.class"
