#!/usr/bin/env bash
# File: MQSI_RESTART.bat
# Date: 2015-06-15
# Version: 0.1.1
# Author: Gabriel Chan
# Date: 30-Oct 2014
# Version: 0.1.0
# Author: Alex Russell

# For example: CALL MQSI_RESTART.bat <node> * <collection> <environment> <instance> /stop
# For example: CALL MQSI_RESTART.bat <node> * <collection> <environment> <instance> /start

# check if we were sourced or executed
if [ -z "$CWD" ]; then
  if [ "${#BASH_ARGV[@]}" -gt 0 ] && [ "${BASH_ARGV[0]}" = "${BASH_SOURCE[0]}" ]; then
    _IS_SOURCED=1
    _SOURCE_FILE="${BASH_ARGV[0]}"
  else
    _IS_SOURCED=0
    _SOURCE_FILE="$0"
  fi
  CWD=$(dirname $_SOURCE_FILE)
  RESOURCE_DIR=$CWD/../resources
  FUNCTIONS_LIB=$CWD/build_functions
  if [ -f $FUNCTIONS_LIB ]; then
    . $FUNCTIONS_LIB
  fi
fi

### parse args
if [ "${#*}" -gt 0 ]; then
  _globstate=$(set +o | grep noglob)
  set -o noglob
  COUNT_ARGS=${#*}
  COMMAND_ARGS=($@)
  $_globstate
  debug "MQSI_RESTART.sh: $COUNT_ARGS arguments read"
  debug "MQSI_RESTART.sh: $*"
fi

_NODE=${COMMAND_ARGS[0]}
_PROJ=${COMMAND_ARGS[1]}
_COLL=${COMMAND_ARGS[2]}

if [ "$COUNT_ARGS" -gt "${#COMMAND_ARGS}" ]; then
  debug "MQSI_RESTART.sh: one or more args is blank"
  COUNT_ARGS=${#COMMAND_ARGS}
fi
LAST_INDEX=$((${#COMMAND_ARGS}-1))
if [ "$COUNT_ARGS" -gt 0 ]; then
  COMMAND=${COMMAND_ARGS[$LAST_INDEX]}
  trace "MQSI_RESTART.sh: COMMAND == $COMMAND"
  if [ "$COMMAND" = "/stop" ] || [ "$COMMAND" = "/start" ]; then
    unset COMMAND_ARGS[$LAST_INDEX]
    COUNT_ARGS=$LAST_INDEX
    _MODE=$COMMAND
    trace "MQSI_RESTART.sh: set _MODE=$COMMAND"
  else
    debug "MQSI_RESTART.sh: invalid or no command found. exit with error"
    return 1 2> /dev/null || exit 1
  fi
  if [ "$COUNT_ARGS" -eq 5 ]; then
    _INST=${COMMAND_ARGS[4]}
    if [ -n "$_INST" ]; then
      _INST_="-${_INST}"
    fi
    trace "MQSI_RESTART.sh: set _INST to $_INST"
  fi
  if [ "$COUNT_ARGS" -ge 4 ]; then
    _ENVN=${COMMAND_ARGS[3]}
    if [ -n "$_ENVN" ]; then
      _ENVN_="-${_ENVN}"
    fi
    trace "MQSI_RESTART.sh: set _ENVN to $_ENVN"
  fi
fi

_FLAG=0
trace "MQSI_RESTART.sh: _MQSI_RESTART == ${_MQSI_RESTART}"
if [ "${_MQSI_RESTART:-0}" -eq 1 ]; then
  _FLAG=1
else
  for i in ../${_COLL}-build/*; do
    trace "MQSI_RESTART.sh: checking object $i"
    if [ -d $i ]; then
      trace "MQSI_RESTART.sh: object is dir"
      if [ -f "$RESOURCE_DIR/${_COLL}${_ENVN_}${_INST_}.dat" ]; then
        debug "MQSI_RESTART.sh: Reading $RESOURCE_DIR/${_COLL}${_ENVN_}${_INST_}.dat"
        while read -r -u 6 line || [ "$line" ]; do
          trace "MQSI_RESTART.sh: read line: $line"
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
            # Eclipse Toolkit dropins - For example: C:\Program Files (x86)\IBM\IntegrationToolkit90\dropins
            if [ -d "$_ECLIPSE_DROPINS" ]; then
              for j in $_ECLIPSE_DROPINS/*; do
                if [ -e "$j" ]; then
                  for j in "../source/${_trimmed_token}/dropins.txt"; do
                    if [ -e "$j" ]; then
                      _FLAG=1
                      continue 4
                    fi
                  done
                fi
              done
            fi
            # Integration Bus shared-classes
            for m in "../source/${_trimmed_token}/shared-classes.txt"; do
              if [ -e "$m" ]; then
                _FLAG=1
                continue 4
              fi
            done
            # Integration Bus JPlugin (on window as administrator)
            for m in "../source/${_trimmed_token}/jplugin.txt"; do
              if [ -e "$m" ]; then
                _FLAG=1
                continue 4
              fi
            done
          fi
        done 6< "$RESOURCE_DIR/${_COLL}${_ENVN_}${_INST_}.dat"
      else
        error "MQSI_RESTART.sh: $RESOURCE_DIR/${_COLL}${_ENVN_}${_INST_}.dat not found"
      fi
    else
      trace "MQSI_RESTART.sh:   not a directory"
    fi
  done
fi

if [ "$_MODE" = "/stop" ]; then
  if [ "$_FLAG" -eq 1 ]; then
    notify ">>> Automatically Stop Broker[${_NODE}] <<<"
    read
    echo $SHELL_BRKADM "mqsistop $_NODE"
    $SHELL_BRKADM "mqsistop $_NODE"
    _MQSI_RESTART_=1
  else
    debug "MQSI_RESTART.sh: _FLAG != 1. Skipping stop"
  fi
fi
if [ "$_MODE" = "/start" ]; then
  if [ "$_FLAG" -eq 1 ]; then
    notify ">>> Automatically Start Broker[${_NODE}] <<<"
    echo $SHELL_BRKADM "mqsistart $_NODE"
    $SHELL_BRKADM "mqsistart $_NODE"
  else
    debug "MQSI_RESTART.sh: _FLAG != 1. Skipping start"
  fi
fi
