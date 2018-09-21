#!/usr/bin/env bash
# File: build\iibconfigure.bat
# Version: 0.2.10
# Date: 2015-06-16
# Author: Gabriel Chan
# Version: 0.2.9
# Date: 14-Nov 2014
# Author: Alex Russell, Navin Khanna
# Comment: added support for /mqsc flag..

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
iibconfigure <project> <collection> [<environment>] [<instance>] [/force|/mqsc|/jmsc]
For example: iibconfigure * local
For example: iibconfigure * caldc DV
For example: iibconfigure * IBNQ1 QA
For example: iibconfigure doc-transfer-* caldc PP /force
For example: iibconfigure doc-transfer-pmm-publisher caldc PP /force
For example: iibconfigure doc-transfer-*-wms-subscriber caldc PP /force
EOF
  RC=1
  exit $RC
}

doConfigure() {
  trace "doConfigure($*)"
  _a=$1
  _message="project '$_a' for collection '$_COLLECTION' processing.."
  _b=1
  inf "$_message"
  . $CWD/CONFIGURE.sh "$_COLLECTION" "$_a"
  EC=$?
  if [ "$EC" -eq 0 ]; then
    _message="project '$_a' for collection '$_COLLECTION' was processed"
    inf "$_message"
  else
    _message="project '$_a' for collection '$_COLLECTION' was not processed"
    warn "$_message"
  fi
}

doFinish() {
  trace "doFinish($*)"
  if [ "$_EC" -eq "$RC" ]; then
    EC=0
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' configured [PASS]"
    inf "$_message"
  else
    EC=1
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' not configured [FAIL]"
    warn "$_message"
  fi
  return $EC 2>/dev/null || exit $EC
}


######################################################
######################################################
trace "iibconfigure.sh ($*)"
_MQSIPROFILE=$(locateMQSIProfile)
if [ -z "$_MQSIPROFILE" ]; then
# if [ -z "$MQSI_FILEPATH" ]; then
  error "this command must be run in an Integration Bus Console!"
  return 1 2> /dev/null || exit 1
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
  debug "iibconfigure.sh: $COUNT_ARGS arguments read"
  debug "iibconfigure.sh: $*"
fi
if [ -z "$COUNT_ARGS" ] || [ "$COUNT_ARGS" -eq 0 ] || [ "$1" = "/?" ] || [ "$1" = "/h" ] || [ "$1" = "--help" ] || [ "$1" = "-h" ]; then
  printUsage
fi

# extract last option iff it's "/force"
_FORCE=0
_ONLY_MQSC=0
_ONLY_JMSC=0
LAST_INDEX=$(($COUNT_ARGS-1))
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
_ENV_="-env"
_ENVN_=$_ENVN
_INST_=$_INST
if [ -n "$_ENVN_" ]; then
 _ENVN_="-${_ENVN_}"
fi
if [ -n "$_INST_" ]; then
 _INST_="-${_INST_}"
fi

sourceConfig "$RESOURCE_DIR/$USER-env.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibconfigure.sh: failed to source $RESOURCE_DIR/$USER-env.sh"
fi
sourceConfig "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}${_ENV_}.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  _message="collection '$_COLLECTION' settings do not exist: ${RESOURCE_DIR}/${_COLLECTION}${_ENVN_}${_INST_}${_ENV_}.sh"
  warn "$_message"
  _RC=2
  return $_RC 2> /dev/null | exit $_RC
fi

for i in "../${_COLLECTION}-build"; do
  if [ -d "$i" ]; then
    debug "iibconfigure.sh: processing each $i"
    if [ -f "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat" ]; then
      debug "iibconfigure.sh: Reading $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat"
      _PROCESSED=0
      while read -r -u 5 line || [ "$line" ]; do
        trace "iibconfigure.sh: read line: $line"
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
          debug "iibconfigure.sh: Calling doConfigure $_TOKEN"
          doConfigure "$_TOKEN"
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
        warn "iibconfigure.sh: No entries were successfully configured"
        RC=1
      elif [ "$_PROCESSED" -eq 1 ]; then
        warn "iibconfigure.sh: Some entries failed to configure"
        RC=1
      elif [ "$_PROCESSED" -eq 2 ]; then
        warn "iibconfigure.sh: All entries successfully configured"
        RC=0
      fi
    else
      error "iibconfigure.sh: $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat not found"
    fi
  fi
done

doFinish