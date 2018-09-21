@REM This file: common-library-msgnode-log4j-java\mqsicreatebar-env.bat

SET "_CLASSPATH=\common-library-jars\lib\log4j-1.2.17.jar;\common-library-jars-build\lib\jplugin2.jar"
SET "_CLASSES=src\*.java src\com\fglsports\custom\node\*.java src\eai\core\log4j\*.java"
SET "_JAVAC=^"%_JAVA_HOME%javac^" -classpath ^"%_CLASSPATH%^""
SET "_JAVAC_ARGS=%_CLASSES% -d ."
@REM SET "_VERSION_=-0.0.1"
SET "_JAR=^"%_JAVA_HOME%jar^" -cvf"
SET "_JAR_ARGS=%_1%%_VERSION_%.jar *.class com eai"
SET "_COPY_CLASSES=COPY bin\*.class ."
SET "_REMOVE_CLASSES=erase /Q *.class"
