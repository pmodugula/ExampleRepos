#!/usr/bin/env bash
# File: deployment\iibdeploy.bat
# Date: 2015-11-20
# Author: Gabriel Chan
# Comment:
#  - remove sourcing of instance specific scripts
#  - allow specifying execution group in args
# Version: 0.1.15
# Date: 2015-08-06
# Author: Gabriel Chan
# Comment: conversion to shell script
# Version: 0.1.14
# Date: 25-Nov 2014
# Author: Alex Russell, Navin Khanna
# Comment: switched around MQSI_RESTART functionality..
# Comment: [0.1.14] added JMSAdmin support and reconditioned logic to support /mqsc|/jmsc

# For example: %1=>IBND1 %2=>core-batch-initiator %3=>DV [%4=>2] [%5=>/mqsc|/jmsc]
# For example: %1=>IBND1 %2=>core-log-handler-library-java

# set -x
# check if we were sourced or executed
if [ -z "$CWD" ]; then
  if [ "${#BASH_ARGV[@]}" -gt 0 ] && [ "${BASH_ARGV[0]}" = "${BASH_SOURCE[0]}" ]; then
    _IS_SOURCED=1
    _SOURCE_FILE="${BASH_ARGV[0]}"
  else
    _IS_SOURCED=0
    _SOURCE_FILE="$0"
  fi
  CWD=$(cd $(dirname $_SOURCE_FILE)/../build; pwd)
  RESOURCE_DIR=$CWD/../resources
  FUNCTIONS_LIB=$CWD/build_functions
  if [ -f $FUNCTIONS_LIB ]; then
    . $FUNCTIONS_LIB
  fi
fi

# ------------------------------------
# functions
# ------------------------------------
_execMQSC() {
  PADTEXT='    MQSC> '
  _PAD=$PADTEXT notify "${_MKDIR_MQSC}"
  OUTPUT="$(${_MKDIR_MQSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_COPY_MQSC} ${_SOURCE_MQSC}runmqsc${_ENVN_}${_INST_}.mqsc ${_TARGET_MQSC}"
  OUTPUT="$(${_COPY_MQSC} ${_SOURCE_MQSC}runmqsc${_ENVN_}${_INST_}.mqsc ${_TARGET_MQSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_CHMOD_MQSC}"
  OUTPUT="$(${_CHMOD_MQSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_DEPLOY_MQSC}"
  OUTPUT="$(${_DEPLOY_MQSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
}

_execJMSC() {
  PADTEXT='    JMSC> '
#  _PAD=$PADTEXT notify '>>> JMSC <<<'
  _PAD=$PADTEXT notify "${_MKDIR_JMSC}"
  OUTPUT="$(${_MKDIR_JMSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_COPY_JMSC} ${_SOURCE_JMSC}jmsadmin${_ENVN_}${_INST_}.jmsc ${_TARGET_JMSC}"
  OUTPUT="$(${_COPY_JMSC} ${_SOURCE_JMSC}jmsadmin${_ENVN_}${_INST_}.jmsc ${_TARGET_JMSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_CHMOD_JMSC}"
  OUTPUT="$(${_CHMOD_JMSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_DEPLOY_JMSC}"
  OUTPUT="$(${_DEPLOY_JMSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
}

_execPLSQL() {
  PADTEXT='    PLSQL> '
  # echo '>>> JMSC <<<'
  _PAD=$PADTEXT notify "${_MKDIR_JMSC}"
  OUTPUT="$(${_MKDIR_JMSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_COPY_JMSC} ${_SOURCE_JMSC}jmsadmin${_ENVN_}${_INST_}.jmsc ${_TARGET_JMSC}"
  OUTPUT="$(${_COPY_JMSC} ${_SOURCE_JMSC}jmsadmin${_ENVN_}${_INST_}.jmsc ${_TARGET_JMSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_CHMOD_JMSC}"
  OUTPUT="$(${_CHMOD_JMSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_DEPLOY_JMSC}"
  OUTPUT="$(${_DEPLOY_JMSC} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
}

_execECLIPSE() {
  PADTEXT='    DROPINS> '
  echo '>>> DROPINS <<<'
  echo '>>> Manually Stop Eclipse <<<'
  pause
  _PAD=$PADTEXT notify "${_REMOVE_JAR} ${_ECLIPSE_DROPINS}${_SEP}${_PROJ_}*.jar"
  OUTPUT="$(${_REMOVE_JAR} "${_ECLIPSE_DROPINS}${_SEP}${_PROJ_}*.jar" 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_COPY_JAR} ${_SOURCE_JAR}${_PROJ_}${_VERSION_}.jar \"${_ECLIPSE_DROPINS}\""
  OUTPUT="$(${_COPY_JAR} ${_SOURCE_JAR}${_PROJ_}${_VERSION_}.jar "${_ECLIPSE_DROPINS}" 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  # For now, _ECLIPSE_DROPINS is final resting place, so no need for step below (and _DEPLOY_DROPINS is not defined)
  # ECHO ${_SHELL_BAR} ${_DEPLOY_DROPINS}
  # CALL ${_SHELL_BAR} ${_DEPLOY_DROPINS}
  echo '>>> Manually Start Eclipse <<<'
}

_execSHARED_CLASSES() {
  PADTEXT='    SHARED-CLASSES> '
  # echo '>>> SHARED-CLASSES <<<'
  if [ -f "${_DESTIN_JAR}${_PROJ_}${_VERSION_}.jar"]; then
    _PAD=$PADTEXT notify "_MKDIR_CLASSES: ${_MKDIR_CLASSES}"
    OUTPUT="$(${_MKDIR_CLASSES} 2>&1 )"
    _PAD=$PADTEXT notify "$OUTPUT"
    _PAD=$PADTEXT notify "_REMOVE_CLASSES: ${_REMOVE_CLASSES}"
    OUTPUT="$(${_REMOVE_CLASSES} 2>&1 )"
    _PAD=$PADTEXT notify "$OUTPUT"
    _PAD=$PADTEXT notify "_COPY_JAR: ${_COPY_JAR} \"${_DESTIN_JAR}${_PROJ_}${_VERSION_}.jar\" ${_TARGET_CLASSES}"
    OUTPUT="$(${_COPY_JAR} "${_DESTIN_JAR}${_PROJ_}${_VERSION_}.jar" ${_TARGET_CLASSES} 2>&1 )"
    _PAD=$PADTEXT notify "$OUTPUT"
  fi
  if [ -d "${_SOURCE_JAR}lib" ]; then
    pushd "${_SOURCE_JAR}lib"
    for j in *.jar; do
      popd
      echo '>>> SHARED-CLASSES:$j <<<'
      OUTPUT="$(${REMOVE} "${_PATH_CLASSES}$j" 2>&1 )"
      _PAD=$PADTEXT notify "$OUTPUT"
      _PAD=$PADTEXT notify "_COPY_JAR: ${_COPY_JAR} \"${_DESTIN_JAR}lib${_SEP}$j\" ${_TARGET_CLASSES}"
      OUTPUT="$(${_COPY_JAR} "${_DESTIN_JAR}lib${_SEP}$j" ${_TARGET_CLASSES} 2>&1 )"
      _PAD=$PADTEXT notify "$OUTPUT"
      pushd "${_SOURCE_JAR}lib"
    done
    popd
  fi
  # FOR %%i IN ("${_DEPLOY_CLASSES}") DO (
  #  IF EXIST %%~si\NUL (
  _PAD=$PADTEXT notify "_CHMOD_CLASSES: ${_CHMOD_CLASSES}"
  OUTPUT="$(${_CHMOD_CLASSES} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "_SHELL_JAR: ${_DEPLOY_CLASSES}"
  OUTPUT="$(${_DEPLOY_CLASSES} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  #  )
  # )
}

_execJPLUGIN() {
  PADTEXT='    JPLUGIN> '
  # echo '>>> JPLUGIN <<<'
  _PAD=$PADTEXT notify "_MKDIR_JPLUGIN: ${_MKDIR_JPLUGIN}"
  OUTPUT="$(${_MKDIR_JPLUGIN} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "_REMOVE_JPLUGIN: ${_REMOVE_JPLUGIN}"
  OUTPUT="$(${_REMOVE_JPLUGIN} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "_COPY_JAR: ${_COPY_JAR} \"${_DESTIN_JAR}${_PROJ_}${_VERSION_}.jar\" ${_TARGET_JPLUGIN}"
  OUTPUT="$(${_COPY_JAR} "${_DESTIN_JAR}${_PROJ_}${_VERSION_}.jar" ${_TARGET_JPLUGIN} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  # IF EXIST "${_SOURCE_JAR}lib" (
  #  PUSHD "${_SOURCE_JAR}lib"
  #  FOR %%j IN ("*.jar") DO (
  #   POPD
  #   echo '>>> JPLUGIN:%%j <<<'
  #   CALL %REMOVE% "${_PATH_JPLUGIN}%%j"
  #   echo _COPY_JAR: ${_COPY_JAR} "${_DESTIN_JAR}lib${_SEP}%%j" ${_TARGET_JPLUGIN}
  #   CALL ${_COPY_JAR} "${_DESTIN_JAR}lib${_SEP}%%j" ${_TARGET_JPLUGIN}
  #   PUSHD "${_SOURCE_JAR}lib"
  #  )
  #  POPD
  # )
  # FOR %%i IN ("${_DEPLOY_JPLUGIN}") DO (
  #  IF EXIST %%~si (
  _PAD=$PADTEXT notify "_CHMOD_JPLUGIN: ${_CHMOD_JPLUGIN}"
  OUTPUT="$(${_CHMOD_JPLUGIN} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "_SHELL_JAR: ${_DEPLOY_JPLUGIN}"
  OUTPUT="$(${_DEPLOY_JPLUGIN} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  #  )
  # )
}

_execBAR() {
  PADTEXT='    BAR> '
  # echo '>>> BAR <<<'
  _PAD=$PADTEXT notify "${_MKDIR_BAR}"
  OUTPUT="$(${_MKDIR_BAR} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_COPY_BAR} ${_SOURCE_BAR}${_PROJ_}${_ENVN_}${_INST_}.bar ${_TARGET_BAR}"
  OUTPUT="$(${_COPY_BAR} ${_SOURCE_BAR}${_PROJ_}${_ENVN_}${_INST_}.bar ${_TARGET_BAR} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_CHMOD_BAR}"
  OUTPUT="$(${_CHMOD_BAR} 2>&1 )"
  _PAD=$PADTEXT notify "$OUTPUT"
  _PAD=$PADTEXT notify "${_DEPLOY_BAR}"
  OUTPUT="$(${_DEPLOY_BAR} 2>&1 )"
  _RC=$?
  _PAD=$PADTEXT notify "$OUTPUT"
  return $_RC
}

# ------------------------------------



# TODO: USER or USERNAME
sourceConfig ../resources/$USER-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibdeploy.sh: Failed to source ../resources/$USER-env.sh"
fi

_NODE_=$1
_PROJ_=$2
sourceConfig ../resources/iibdeploy-${_NODE_}-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibdeploy.sh: Failed to source../resources/iibdeploy-${_NODE_}-env.sh"
fi
sourceConfig ../resources/${_HOSTNAME}-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  debug "iibdeploy.sh: Failed to source ../resources/${_HOSTNAME}-env.sh"
fi

_ENVN=
if [ -n "$3" ]; then
  _ENVN=$3
fi
_ENVN_=$_ENVN
if [ -n "$_ENVN" ]; then
  _ENVN_="-$_ENVN"
fi

_INST=
if [ -n "$4" ]; then
  _INST=$4
fi
#if [ "$_INST" = "0" ] || [ "$_INST" = "1" ]; then
#  _INST_=$_INST
#fi
#_INST_=$_INST
#if [ -n "$_INST" ]; then
#  _INST_="-$_INST"
#fi

_ONLY_MQSC=0
if [ "$4" = "/mqsc" ] || [ "$5" = "/mqsc" ]; then
  _ONLY_MQSC=1
fi
_ONLY_JMSC=0
if [ "$4" = "/jmsc" ] || [ "$5" = "/jmsc" ]; then
  _ONLY_JMSC=1
fi

if [ "$_HOSTNAME" = "localhost" ]; then
  sourceConfig ../resources/iibdeploy-localhost-env.sh
  _RC=$?
  if [ "$_RC" -ne 0 ]; then
    warn "iibdeploy.sh: failed to source ../resources/iibdeploy-localhost-env.sh"
  fi
  REMOVE=REMOVE-localhost.sh
else
  sourceConfig ../resources/iibdeploy-env.sh
  _RC=$?
  if [ "$_RC" -ne 0 ]; then
    warn "iibdeploy.sh: failed to source ../resources/iibdeploy-env.sh"
  fi
  REMOVE=REMOVE.sh
fi

mkdir ../cygmin64/home > /dev/null 2>&1
mkdir ../cygmin64/home/$USER > /dev/null 2>&1

if [ "$_ONLY_JMSC" -eq 0 ]; then
  if [ -e "../source/$2/mqsc.txt" ]; then
    debug "iibdeploy.sh: Begin _execMQSC"
    _execMQSC
    # return
  fi
fi
if [ "$_ONLY_MQSC" -eq 0 ]; then
  if [ -e "../source/$2/jmsc.txt" ]; then
    debug "iibdeploy.sh: Begin _execJMSC"
    _execJMSC
    # return
  fi
fi
if [ "$_ONLY_MQSC" = "0" ] && [ "$_ONLY_JMSC" = "0" ]; then
  if [ -e "../source/${_PROJ_}/sql.txt" ]; then
    debug "iibdeploy.sh: Begin _execPLSQL"
    _execPLSQL
    # return
  fi
  if [ -e "../source/${_PROJ_}/dropins.txt" ]; then
    # eclipse toolkit dropins
    for i in ${_ECLIPSE_DROPINS}; do
      if [ -d $i ]; then
        debug "iibdeploy.sh: Begin _execECLIPSE"
        _execECLIPSE
        //return
      fi
    done
  fi
  if [ "$_MQSI_RESTART" -ne 0 ]; then
    debug "iibdeploy.sh: calling MQSI_RESTART.sh /stop"
    . ./MQSI_RESTART.sh $_NODE_ $1 $2 $_ENVN $_INST /stop
  fi
  if [ -e "../source/${_PROJ_}/jplugin.txt" ]; then
    debug "iibdeploy.sh: calling _execJPLUGIN"
    _execJPLUGIN
    # return
  fi
  if [ -e "../source/${_PROJ_}/shared-classes.txt" ]; then
    debug "iibdeploy.sh: calling _execSHARED_CLASSES"
    _execSHARED_CLASSES
    # return
  fi
  if [ "$_MQSI_RESTART" -eq 1 ]; then
    debug "iibdeploy.sh: calling MQSI_RESTART.sh /start"
    . ./MQSI_RESTART.sh $_NODE_ $_PROJ_ $_ENVN $_INST /start
  fi
  if [ -e "../source/${_PROJ_}/bar.txt" ]; then
    debug "iibdeploy.sh: calling _execBAR"
    _execBAR
    _RC=$?
    # return
  fi
fi
return $_RC 2> /dev/null || exit $_RC
