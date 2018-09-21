#!/usr/bin/env bash
# File: build\iibbuild.bat
# Version: 0.2.9
#   conversion to unix
# Date: 2015-06-05
# Author: Gabriel Chan
# Version: 0.2.8
# Date: 14-Nov 2014
# Author: Alex Russell, Navin Khanna

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
$0 <project> <collection> [<environment>] [<instance>] [/force]

For example: iibbuild * local
For example: iibbuild * caldc DV
For example: iibbuild * IBNQ1 QA
For example: iibbuild doc-transfer-* caldc PP /force
For example: iibbuild doc-transfer-pmm-publisher caldc PP /force
For example: iibbuild doc-transfer-*-wms-subscriber caldc PP /force
EOF
  RC=1
  exit $RC
}

doBuild() {
  trace "doBuild($*)"
  _a=$1
  _message="project '${_a}' for collection '${_COLLECTION}' processing.."
  _b=1
  inf "$_message"
  # need to source so it gets our variables
  # echo "Calling: . $CWD/build.sh \"$_COLLECTION\" \"$_a\"" 1>&2
  . $CWD/build.sh "$_COLLECTION" "$_a"
  EC=$?
  if [ "$EC" -eq 0 ]; then
    _message="project '$_a' for collection '${_COLLECTION}' was processed"
    inf "$_message"
  else
    _message="project '$_a' for collection '${_COLLECTION}' was not processed"
    warn "$_message"
  fi
}


doFinish() {
  trace "doFinish($*)"
  if [ "$_EC" -eq "$RC" ]; then
    EC=0
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' was built [PASS]"
    inf "$_message"
  else
    EC=1
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' was not built [FAIL]"
    warn "$_message"
  fi
  return $EC 2>/dev/null || exit $EC
}


##########################################
##########################################
trace "iibbuild.sh ($*)"
_MQSIPROFILE=$(locateMQSIProfile)
if [ -z "${_MQSIPROFILE}" ]; then
# if [ -z "$MQSI_FILEPATH" ]; then
  error "this command must be run in an Integration Bus Console!"
  return 1 2>/dev/null || exit $EC
else
  inf "Using MQSI: ${_MQSIPROFILE%%bin*}"
fi
### parse args
if [ "${#*}" -gt 0 ]; then
  _globstate=$(set +o | grep noglob)
  set -o noglob
  COUNT_ARGS=${#*}
  COMMAND_ARGS=($@)
  $_globstate
  debug "iibbuild.sh: $COUNT_ARGS arguments read"
  debug "iibbuild.sh: $*"
fi
if [ -z "$COUNT_ARGS" ] || [ "$1" = "/?" ] || [ "$1" = "/h" ] || [ "$1" = "--help" ] || [ "$1" = "-h" ]; then
  printUsage
fi


# extract last option iff it's "/force"
LAST_INDEX=$((COUNT_ARGS-1))
if [ "${COMMAND_ARGS[$LAST_INDEX]}" = "/force" ]; then
  _FORCE=1
  unset COMMAND_ARGS[$LAST_INDEX]
  COUNT_ARGS=$LAST_INDEX
else
  _FORCE=0
fi

if [ "${COUNT_ARGS}" -lt 2 ]; then
  printUsage
fi

_PROJECT=${COMMAND_ARGS[0]}
_COLLECTION=${COMMAND_ARGS[1]}
_ENVN=${COMMAND_ARGS[2]}
_ENVN_=${COMMAND_ARGS[2]}
_INST=${COMMAND_ARGS[3]}
_INST_=${COMMAND_ARGS[3]}
if [ -n "$_ENVN_" ]; then
  _ENVN_="-$_ENVN_"
fi
if [ -n "$_INST_" ]; then
  _INST_="-$_INST_"
fi

_ENV_="-env"

sourceConfig "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}${_ENV_}.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  _message="Collection $_COLLECTION settings do not exist: $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}${_ENV_}.sh"
  warn "$_message"
  RC=2
  return $_RC 2> /dev/null | exit $_RC
fi

if [ ! -d "../${_COLLECTION}-build" ]; then
  mkdir "../${_COLLECTION}-build"
fi
if [ -f "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat" ]; then
  debug "iibbuild.sh: Reading $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat"
  _PROCESSED=0
  while read -r -u 5 line || [ "$line" ]; do
    trace "iibbuild.sh: read line: $line"
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
    # echo "line: ${_trimmed_token}, ${_local_project}, $line" 1>&2
    if [ -n "$(grep -E "^${_local_project}$" <<< ${_trimmed_token})" ]; then
      debug "iibbuild.sh: Calling doBuild $_TOKEN"
      doBuild "$_TOKEN"
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
      warn "iibbuild.sh: No entries were successfully built"
      RC=1
    elif [ "$_PROCESSED" -eq 1 ]; then
      warn "iibbuild.sh: Some entries failed to build"
      RC=1
    elif [ "$_PROCESSED" -eq 2 ]; then
      warn "iibbuild.sh: All entries successfully built"
      RC=0
    fi
else
  error "iibbuild.sh: $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat not found"
fi

doFinish
