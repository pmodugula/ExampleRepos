@REM File: iibdeploy-env.bat
@REM Version: 0.1.8
@REM Date: 26-Nov 2014
@REM Author: Alex Russell
@REM Comment: [0.1.7] added SQL*Plus support
@REM Comment: [0.1.8] added JMSAdmin support

SET "_ID_RSA=/cygdrive/c/eai/ibm-ib/resources/%_USERNAME%-%_HOSTNAME%-id_rsa"

SET "_COPY=..\cygmin64\bin\scp.exe -i %_ID_RSA% -oStrictHostKeyChecking=false"
SET "_SHELL=..\cygmin64\bin\ssh.exe -i %_ID_RSA% -oStrictHostKeyChecking=false %_USERNAME%@%_HOSTNAME%"
SET "_SHELL_BRKADM=..\cygmin64\bin\ssh.exe -i %_ID_RSA% -oStrictHostKeyChecking=false %_USERNAME%@%_HOSTNAME%"

SET "_COPY_MQSC=%_COPY%"
SET "_SOURCE_MQSC=../source/%_PROJ_%/"
SET "_PATH_MQSC=/home/%_USERNAME%/%_PROJ_%/"
SET "_TARGET_MQSC=%_USERNAME%@%_HOSTNAME%:%_PATH_MQSC%"
SET "_MKDIR_MQSC=%_SHELL_BRKADM% ^"mkdir -p %_PATH_MQSC%;chmod go+rx %_PATH_MQSC%^""
SET "_CHMOD_MQSC=%_SHELL_BRKADM% ^"chmod 644 %_PATH_MQSC%runmqsc%_ENVN_%%_INST_%.mqsc^""
SET "_DEPLOY_MQSC=%_SHELL_BRKADM% ^"sudo su - mqm -c 'runmqsc %_QMGR% ^^^< %_PATH_MQSC%runmqsc%_ENVN_%%_INST_%.mqsc'^""

SET "_COPY_JMSC=%_COPY%"
SET "_SOURCE_JMSC=../source/%_PROJ_%/"
SET "_PATH_JMSC=/home/%_USERNAME%/%_PROJ_%/"
SET "_TARGET_JMSC=%_USERNAME%@%_HOSTNAME%:%_PATH_JMSC%"
SET "_MKDIR_JMSC=%_SHELL_BRKADM% ^"mkdir -p %_PATH_JMSC%;chmod go+rx %_PATH_JMSC%^""
SET "_CHMOD_JMSC=%_SHELL_BRKADM% ^"chmod 644 %_PATH_JMSC%jmsadmin%_ENVN_%%_INST_%.jmsc^""
SET "_DEPLOY_JMSC=%_SHELL_BRKADM% ^"sudo su - mqm -c 'JMSAdmin ^^^< %_PATH_JMSC%jmsadmin%_ENVN_%%_INST_%.jmsc'^""

SET "_COPY_PLSQL=%_COPY%"
SET "_SOURCE_PLSQL=../source/%_PROJ_%/"
SET "_PATH_PLSQL=/home/%_USERNAME%/%_PROJ_%/"
SET "_TARGET_PLSQL=%_USERNAME%@%_HOSTNAME%:%_PATH_PLSQL%"
SET "_MKDIR_PLSQL=%_SHELL_BRKADM% ^"mkdir -p %_PATH_PLSQL%;chmod go+rx %_PATH_PLSQL%^""
SET "_CHMOD_PLSQL=%_SHELL_BRKADM% ^"chmod g+w %_PATH_PLSQL%;chmod g+w %_PATH_PLSQL%sqlplus%_ENVN_%%_INST_%.s*^""
SET "_DEPLOY_PLSQL=%_SHELL_BRKADM% ^"sudo su - brkadm -c 'sh %_PATH_PLSQL%sqlplus%_ENVN_%%_INST_%.sh'^""

SET "_COPY_BAR=%_COPY%"
SET "_SOURCE_BAR=../deployment/%_PROJ_%/"
SET "_PATH_BAR=/home/%_USERNAME%/%_PROJ_%/"
SET "_TARGET_BAR=%_USERNAME%@%_HOSTNAME%:%_PATH_BAR%"
SET "_MKDIR_BAR=%_SHELL_BRKADM% ^"mkdir -p %_PATH_BAR%;chmod go+rx %_PATH_BAR%^""
SET "_CHMOD_BAR=%_SHELL_BRKADM% ^"chmod 644 %_PATH_BAR%%_PROJ_%%_ENVN_%%_INST_%.bar^""
SET "_DEPLOY_BAR=%_SHELL_BRKADM% ^"sudo su - brkadm -c 'mqsideploy %_NODE_% -e %_SERVER% -a %_PATH_BAR%%_PROJ_%%_ENVN_%%_INST_%.bar'^""

SET "_SEP=/"

SET "_SHELL_JAR=%_SHELL% sudo su - brkadm -c"
SET "_REMOVE=rm -f"
SET "_REMOVE_JAR=%_SHELL_JAR% %_REMOVE%"
SET "_3RDSRC_JAR=..\source\%_PROJ_%\"
SET "_3RDDST_JAR=../source/%_PROJ_%/"
SET "_SOURCE_JAR=..\build\%_PROJ_%\"
SET "_DESTIN_JAR=../build/%_PROJ_%/"
SET "_PATH_JAR=/home/%_USERNAME%/"
SET "_COPY_JAR=%_COPY%"

SET "_PATH_JPLUGIN=/opt/ibm/mqsi/9.0.0.1/jplugin/"
SET "_MKDIR_JPLUGIN=%_SHELL_BRKADM% ^"mkdir -p %_PATH_JAR%%_PROJ_%^""
SET "_CHMOD_JPLUGIN=%_SHELL_BRKADM% ^"chmod 640 %_PATH_JAR%%_PROJ_%/*^""
SET "_TARGET_JPLUGIN=%_USERNAME%@%_HOSTNAME%:%_PATH_JAR%%_PROJ_%/"
SET "_REMOVE_JPLUGIN=%_SHELL_BRKADM% ^"sudo su - brkadm -c 'rm -f %_PATH_JPLUGIN%*.jar'^""
SET "_DEPLOY_JPLUGIN=%_SHELL_BRKADM% ^"sudo su - brkadm -c 'cp -rf %_PATH_JAR%%_PROJ_%/* %_PATH_JPLUGIN%'^""

SET "_PATH_CLASSES=/var/mqsi/shared-classes/"
SET "_MKDIR_CLASSES=%_SHELL_BRKADM% ^"mkdir -p %_PATH_JAR%%_PROJ_%^""
SET "_CHMOD_CLASSES=%_SHELL_BRKADM% ^"chmod 640 %_PATH_JAR%%_PROJ_%/*^""
SET "_TARGET_CLASSES=%_USERNAME%@%_HOSTNAME%:%_PATH_JAR%%_PROJ_%/"
SET "_REMOVE_CLASSES=%_SHELL_BRKADM% ^"sudo su - brkadm -c 'rm -f %_PATH_CLASSES%*.jar'^""
SET "_DEPLOY_CLASSES=%_SHELL_BRKADM% ^"sudo su - brkadm -c 'cp -rf %_PATH_JAR%%_PROJ_%/* %_PATH_CLASSES%'^""
