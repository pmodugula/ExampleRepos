#!/usr/bin/env bash
# File: build\iibdeploy.bat
# Version: 0.2.13
# Date: 2015-11-20
# Author: Gabriel Chan
# Comment:
#  - remove sourcing of instance specific scripts
#  - allow specifying execution group in args
# Version: 0.2.12
# Date: 2015-06-15
# Author: Gabriel Chan
# Comment: conversion to shell script
# Version: 0.2.11
# Date: 26-Nov 2014
# Author: Alex Russell, Navin Khanna
# Comment: Also clean deployment\<project> folder for good measure (change is actually in DEPLOY.bat)
# Comment: [0.2.11] Added /mqsc support

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
iibdeploy <project> <collection> [<environment>] [<instance>] [<execgroup>] [/force|/mqsc|/jmsc]
For example: iibdeploy * local
For example: iibdeploy * caldc DV
For example: iibdeploy * IBNQ1 QA
For example: iibdeploy doc-transfer-* caldc PP /force
For example: iibdeploy doc-transfer-pmm-publisher caldc PP /force
For example: iibdeploy doc-transfer-*-wms-subscriber caldc PP /force
EOF
 RC=1
 exit $RC
}

doFinish() {
    trace "iibdeploy.sh: doFinish($*)"
  if [ "$_EC" -eq "$RC" ]; then
    EC=0
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' was deployed [PASS]"
    inf "$_message"
  else
    EC=1
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' was not deployed [FAIL]"
    warn "$_message"
  fi
  return $EC 2>/dev/null || exit $EC
}

doDeploy() {
  trace "iibdeploy.sh: doDeploy($*)"
  _a=$1
  _message="project '$_a' for collection '${_COLLECTION}' processing.."
  _b=1
  inf "$_message"
  debug "iibdeploy.sh: calling DEPLOY.sh \"$_COLLECTION\" \"$_a\""
  . $CWD/DEPLOY.sh "${_COLLECTION}" "${_a}"
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

trace "iibdeploy.sh ($*)"

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
  debug "iibdeploy.sh: $COUNT_ARGS arguments read"
  debug "iibdeploy.sh: $*"
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
elif [ "${COMMAND_ARGS[$LAST_INDEX]}" = "/mqsc" ]; then
  _ONLY_MQSC=0
  unset COMMAND_ARGS[$LAST_INDEX]
  COUNT_ARGS=$LAST_INDEX
elif [ "${COMMAND_ARGS[$LAST_INDEX]}" = "/jmsc" ]; then
  _ONLY_JMSC=0
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

# override _SERVER read from resource file if _EXECGROUP is specified
if [ -n "$_EXECGROUP" ]; then
  debug "Override _SERVER with $_EXECGROUP"
  _SERVER=$_EXECGROUP
fi
if [ -n "$_INSTPARAM" ]; then
  debug "Reset _INST to command line param value"
  _INST=$_INSTPARAM
fi

# debug "iibdeploy.sh: Calling MQSI_RESTART.sh _NODE=$_NODE _PROJECT=$_PROJECT _COLLECTION=$_COLLECTION _ENVN=$_ENVN _INST=$_INST /stop"
# . $CWD/MQSI_RESTART.sh "$_NODE" "$_PROJECT" "$_COLLECTION" "$_ENVN" "$_INST" "/stop"
_MQSI_RESTART=0
if [ -n "$(grep '*' <<< "$_PROJECT")" ]; then
  for i in ../${_COLLECTION}-build/*; do
    trace "iibdeploy.sh: iterating through ../${_COLLECTION}-build/* -- at $i"
    if [ -f "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat" ]; then
      debug "iibdeploy.sh: Reading $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat"
      _PROCESSED=0
      while read -r -u 5 line || [ "$line" ]; do
        trace "iibdeploy.sh: read line: $line"
        if [ -n "$(grep -E '^\s*@REM' <<< $line)" ]; then continue; fi
        if [ -n "$(grep -E '^\s*REM'  <<< $line)" ]; then continue; fi
        if [ -n "$(grep -E '^\s*#'    <<< $line)" ]; then continue; fi
        if [ -z "$(sed 's/\s//g;'     <<< $line)" ]; then continue; fi
        line="$(tr -d '\r' <<< $line)"
        # split line by ':', take first token, trim trailing spaces (this is the project)
        # if _project contains '*', prepend '.'
        # check if token contains _project, then return 0 else 1
        _TOKEN="$(sed 's/^\([^:]*\):.*/\1/g' <<< $line)"
        _trimmed_token=$(sed 's/^\(.*?\)\s*/\1/g;' <<< $_TOKEN)
        _local_project=$(sed 's/\*/\.\*/g;' <<< $_PROJECT)
        if [ -n "$(grep -E "^${_local_project}$" <<< ${_trimmed_token})" ]; then
          debug "iibdeploy.sh: Calling doDeploy $_TOKEN"
          doDeploy "$_TOKEN"
          if [ "$EC" -eq 0 ]; then
            if [ "$_PROCESSED" -eq 0 ]; then
              _PROCESSED=2
            fi
          else
            if [ "$_PROCESSED" -eq 2 ]; then
              _PROCESSED=1
            fi
          fi
        fi
      done 5< "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat"
      if [ "$_PROCESSED" -eq 0 ]; then
        warn "iibdeploy.sh: No entries were successfully deployed"
        RC=1
      elif [ "$_PROCESSED" -eq 1 ]; then
        warn "iibdeploy.sh: Some entries failed to deploy"
        RC=1
      elif [ "$_PROCESSED" -eq 2 ]; then
        warn "iibdeploy.sh: All entries successfully deployed"
        RC=0
      fi
    else
      error "iibdeploy.sh: $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat not found"
    fi
  done
else
  debug "iibdeploy.sh: Calling doDeploy $_PROJECT"
  doDeploy "$_PROJECT"
  RC=$EC
fi
# debug "iibdeploy.sh: Calling MQSI_RESTART.sh _NODE=$_NODE _PROJECT=$_PROJECT _COLLECTION=$_COLLECTION _ENVN=$_ENVN _INST=$_INST /start"
# . $CWD/MQSI_RESTART.sh "$_NODE" "$_PROJECT" "$_COLLECTION" "$_ENVN" "$_INST" "/start"

doFinish