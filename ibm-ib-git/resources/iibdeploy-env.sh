#!/usr/bin/env bash
# File: iibdeploy-env.bat
# Version: 0.1.9
# Date: 2015-08-07
# Author: Gabriel Chan
# Version: 0.1.8
# Date: 26-Nov 2014
# Author: Alex Russell
# Comment: [0.1.7] added SQL*Plus support
# Comment: [0.1.8] added JMSAdmin support
# Comment: [0.1.9] conversion to shell

# SET "_ID_RSA=/cygdrive/c/eai/ibm-ib/resources/%_USERNAME%-%_HOSTNAME%-id_rsa"
_ID_RSA=~/${_USERNAME}-${_HOSTNAME}-id_rsa

_COPY="scp -i $_ID_RSA -oStrictHostKeyChecking=false"
_SHELL="ssh -i $_ID_RSA -oStrictHostKeyChecking=false $_USERNAME@$_HOSTNAME"
_SHELL_BRKADM="ssh -i $_ID_RSA -oStrictHostKeyChecking=false $_USERNAME@$_HOSTNAME"

_COPY_MQSC="$_COPY"
_SOURCE_MQSC="../source/${_PROJ_}/"
_PATH_MQSC="/home/${_USERNAME}/${_PROJ_}/"
_TARGET_MQSC="${_USERNAME}@${_HOSTNAME}:${_PATH_MQSC}"
_MKDIR_MQSC="$_SHELL_BRKADM mkdir -p $_PATH_MQSC"
_CHMOD_MQSC="$_SHELL_BRKADM chmod 644 ${_PATH_MQSC}runmqsc${_ENVN_}${_INST_}.mqsc"
_DEPLOY_MQSC="$_SHELL_BRKADM sudo su - mqm -c 'runmqsc ${_QMGR} < ${_PATH_MQSC}runmqsc${_ENVN_}${_INST_}.mqsc'"

_COPY_JMSC="$_COPY"
_SOURCE_JMSC="../source/${_PROJ_}/"
_PATH_JMSC="/home/${_USERNAME}/${_PROJ_}/"
_TARGET_JMSC="${_USERNAME}@${_HOSTNAME}:${_PATH_JMSC}"
_MKDIR_JMSC="$_SHELL_BRKADM mkdir -p $_PATH_JMSC"
_CHMOD_JMSC="$_SHELL_BRKADM chmod 644 ${_PATH_JMSC}jmsadmin${_ENVN_}${_INST_}.jmsc"
_DEPLOY_JMSC="$_SHELL_BRKADM sudo su - mqm -c 'JMSAdmin < ${_PATH_JMSC}jmsadmin${_ENVN_}${_INST_}.jmsc'"

_COPY_PLSQL="$_COPY"
_SOURCE_PLSQL="../source/${_PROJ_}/"
_PATH_PLSQL="/home/${_USERNAME}/${_PROJ_}/"
_TARGET_PLSQL="${_USERNAME}@${_HOSTNAME}:${_PATH_PLSQL}"
_MKDIR_PLSQL="$_SHELL_BRKADM mkdir -p $_PATH_PLSQL"
_CHMOD_PLSQL="$_SHELL_BRKADM chmod g+w ${_PATH_PLSQL};chmod g+w ${_PATH_PLSQL}sqlplus${_ENVN_}${_INST_}.s*"
_DEPLOY_PLSQL="$_SHELL_BRKADM sudo su - brkadm -c 'sh ${_PATH_PLSQL}sqlplus${_ENVN_}${_INST_}.sh'"


__barfilenamefile="barfilename.txt"
_getBarFilename() {
  SOURCEPATH=$1
  if [[ -d "$SOURCEPATH" ]] && [[ -f "${SOURCEPATH}/${__barfilenamefile}" ]]; then
    name=$(cat ${SOURCEPATH}/${__barfilenamefile})
    echo -n $name
  else
    echo -n "${_PROJ_}${_ENVN_}${_INST_}.bar"
  fi
}

_COPY_BAR="$_COPY"
_SOURCE_BAR="../deployment/${_PROJ_}/"
_PATH_BAR="/home/${_USERNAME}/${_PROJ_}/"
# _TARGET_BAR="${_USERNAME}@${_HOSTNAME}:${_PATH_BAR}"
_TARGET_BAR="${_USERNAME}@${_HOSTNAME}:${_PATH_BAR}$(_getBarFilename ${_SOURCE_BAR})"
_MKDIR_BAR="$_SHELL_BRKADM mkdir -p $_PATH_BAR"
_CHMOD_BAR="$_SHELL_BRKADM chmod 644 ${_PATH_BAR}$(_getBarFilename ${_SOURCE_BAR})"
_DEPLOY_BAR="$_SHELL_BRKADM sudo su - brkadm -c 'mqsideploy ${_NODE_} -e ${_SERVER} -a ${_PATH_BAR}$(_getBarFilename ${_SOURCE_BAR})'"
# _CHMOD_BAR="$_SHELL_BRKADM chmod 644 ${_PATH_BAR}${_PROJ_}${_ENVN_}${_INST_}.bar"
# _DEPLOY_BAR="$_SHELL_BRKADM sudo su - brkadm -c 'mqsideploy ${_NODE_} -e ${_SERVER} -a ${_PATH_BAR}${_PROJ_}${_ENVN_}${_INST_}.bar'"

_SEP='/'

_SHELL_JAR="$_SHELL sudo su - brkadm -c"
_REMOVE="rm -f"
_REMOVE_JAR="$_SHELL_JAR $_REMOVE"
_3RDSRC_JAR="../source/${_PROJ_}/"
_3RDDST_JAR="../source/$i{_PROJ_}/"
_SOURCE_JAR="../build/${_PROJ_}/"
_DESTIN_JAR="../build/${_PROJ_}/"
_PATH_JAR="/home/$_USERNAME/"
_COPY_JAR=$_COPY

_PATH_JPLUGIN=/opt/ibm/mqsi/9.0.0.1/jplugin/
_MKDIR_JPLUGIN="$_SHELL_BRKADM mkdir -p ${_PATH_JAR}${_PROJ_}"
_CHMOD_JPLUGIN="$_SHELL_BRKADM chmod 640 ${_PATH_JAR}${_PROJ_}"
_TARGET_JPLUGIN="${_USERNAME}@${_HOSTNAME}:${_PATH_JAR}${_PROJ_}/"
_REMOVE_JPLUGIN="${_SHELL_BRKADM} sudo su - brkadm -c 'rm -f ${_PATH_JPLUGIN}*.jar'"
_DEPLOY_JPLUGIN="${_SHELL_BRKADM} sudo su - brkadm -c 'cp -rf ${_PATH_JAR}${_PROJ_}/* ${_PATH_JPLUGIN}'"

_PATH_CLASSES=/var/mqsi/shared-classes/
_MKDIR_CLASSES="${_SHELL_BRKADM} mkdir -p ${_PATH_JAR}${_PROJ_}"
_CHMOD_CLASSES="${_SHELL_BRKADM} chmod 640 ${_PATH_JAR}${_PROJ_}/*"
_TARGET_CLASSES="${_USERNAME}@${_HOSTNAME}:${_PATH_JAR}${_PROJ_}/"
_REMOVE_CLASSES="${_SHELL_BRKADM} sudo su - brkadm -c 'rm -f ${_PATH_CLASSES}*.jar'"
_DEPLOY_CLASSES="${_SHELL_BRKADM} sudo su - brkadm -c 'cp -rf ${_PATH_JAR}${_PROJ_}/* ${_PATH_CLASSES}'"

