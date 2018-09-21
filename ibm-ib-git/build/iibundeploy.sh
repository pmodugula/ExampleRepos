#!/usr/bin/env bash
# File: build\iibundeploy.sh
# Date: 2015-11-20
# Author: Gabriel Chan
# Comment:
#  - remove sourcing of instance specific scripts
#  - allow specifying execution group in args
# Version: 0.1.0
# Date: 2015-10-30
# Author: Gabriel Chan
# Comment: clone from iibdeploy.sh script

# check if we were sourced or executed
if [ -z "$CWD" ]; then
  if [ "${#BASH_ARGV[@]}" -gt 0 ] && [ "${BASH_ARGV[0]}" = "${BASH_SOURCE[0]}" ]; then
    _IS_SOURCED=1
    _SOURCE_FILE="${BASH_ARGV[0]}"
  else
    _IS_SOURCED=0
    _SOURCE_FILE="$0"
  fi
  CWD=$(cd $(dirname $_SOURCE_FILE); pwd)
  RESOURCE_DIR=$CWD/../resources
  FUNCTIONS_LIB=$CWD/build_functions
  if [ -f $FUNCTIONS_LIB ]; then
    . $FUNCTIONS_LIB
  fi
fi

_NO_DRY_RUN=1
_EC=0
RC=0

printUsage() {
 cat << EOF
iibundeploy <project> <collection> [<environment>] [<instance>] [<execgroup>]
Need to specify project explicitly. No wildcards
For example: iibundeploy doc-transfer-pmm-publisher caldc PP
EOF
 RC=1
 exit $RC
}

doFinish() {
    trace "iibundeploy.sh: doFinish($*)"
  if [ "$EC" -eq "0" ]; then
    EC=0
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' was undeployed [PASS]"
    inf "$_message"
  else
    EC=1
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' was not undeployed [FAIL]"
    warn "$_message"
  fi
  return $EC 2>/dev/null || exit $EC
}

doUndeploy() {
  trace "iibundeploy.sh: doUndeploy($*)"
  _a=$1
  _message="project '$_a' for collection '${_COLLECTION}' processing.."
  _b=1
  inf "$_message"
  debug "iibundeploy.sh: calling UNDEPLOY.sh \"$_COLLECTION\" \"$_a\""
  . $CWD/UNDEPLOY.sh "${_COLLECTION}" "${_a}"
  EC=$?
  if [ "$EC" -eq 0 ]; then
    _message="project '${_a}' for collection '${_COLLECTION}' was processed"
    inf "$_message"
  else
    _message="project '${_a}' for collection '${_COLLECTION}' was not processed"
    warn "$_message"
  fi
}

##########################################
##########################################

trace "iibundeploy.sh ($*)"

_MQSIPROFILE=$(locateMQSIProfile)
if [ -z "${_MQSIPROFILE}" ]; then
# if [ -z "$MQSI_FILEPATH" ]; then
  error "this command must be run in an Integration Bus Console!"
  return $EC 2>/dev/null || exit $EC
else
  inf "Using MQSI: ${_MQSIPROFILE%%bin*}"
fi
### parse args
if [ "${#*}" -gt 0 ]; then
  _globstate=$(set +o | grep noglob)
  set -o noglob
  COUNT_ARGS=${#*}
  COMMAND_ARGS=($*)
  $_globstate
  debug "iibundeploy.sh: $COUNT_ARGS arguments read"
  debug "iibundeploy.sh: $*"
fi
if [ -z "$COUNT_ARGS" ] || [ "$1" = "/?" ] || [ "$1" = "/h" ] || [ "$1" = "--help" ] || [ "$1" = "-h" ]; then
  printUsage
fi

# extract last option iff it's "/force"
_FORCE=0
_ONLY_MQSC=0
_ONLY_JMSC=0
LAST_INDEX=$((COUNT_ARGS-1))
if [ "${COMMAND_ARGS[$LAST_INDEX]}" = "/force" ]; then
  _FORCE=1
  unset COMMAND_ARGS[$LAST_INDEX]
  COUNT_ARGS=$LAST_INDEX
fi

if [ "${COUNT_ARGS}" -lt 2 ]; then
  printUsage
fi

_PROJECT=${COMMAND_ARGS[0]}
_COLLECTION=${COMMAND_ARGS[1]}
_ENVN=${COMMAND_ARGS[2]}
_INST=${COMMAND_ARGS[3]}
_INSTPARAM=$_INST
_EXECGROUP=${COMMAND_ARGS[4]}
_ENV_="-env"
_ENVN_=$_ENVN
#_INST_=$_INST
if [ -n "$_ENVN_" ]; then
  _ENVN_="-${_ENVN_}"
fi
#if [ -n "$_INST_" ]; then
#  _INST_="-${_INST_}"
#fi

if [ "$_HOSTNAME" = "localhost" ]; then
  SHELL_BRKADM="SHELL-localhost.sh"
else
  SHELL_BRKADM="SHELL_BRKADM.sh"
fi

sourceConfig "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}${_ENV_}.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  _message="collection '${_COLLECTION}' settings do not exist: $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}${_ENV_}.sh"
  error "$_message"
  RC=2
    return $_RC 2> /dev/null | exit $_RC
fi

# override execution group if passed in
if [ -n "$_EXECGROUP" ]; then
  debug "Override _SERVER with $_EXECGROUP"
  _SERVER=$_EXECGROUP
fi
if [ -n "$_INSTPARAM" ]; then
  debug "Reset _INST to command line param value"
  _INST=$_INSTPARAM
fi

doUndeploy "$_PROJECT"


doFinish