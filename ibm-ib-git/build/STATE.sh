#!/usr/bin/env bash

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

trace "STATE.sh ($*)"

if [ -n "$1" ] && [ -n "$2" ]; then
  cd ../deployment
  if [ "${_NO_DRY_RUN:-0}" -eq 1 ]; then
    trace "STATE.sh: calling deployment/iibstate.sh \"$_NODE\" \"$2\" \"$_ENVN\" \"$_INST\""
    . ./iibstate.sh "$_NODE" "$2" "$_ENVN" "$_INST"
    _RC=$?
    cd ../build
  fi
fi

return $_RC 2> /dev/null || exit $_RC
