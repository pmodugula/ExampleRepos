#!/usr/bin/env bash
# File: iibpackagebar.bat
# Version: 0.1.6
# Date: 2015-06-09
# Author: Gabriel Chan
# Version: 0.1.5
# Date: 17-Oct 2014
# Author: Alex Russell

# set -x
# For example: $1 => core-batch-initiator

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

trace "iibpackagebar.sh ($*)"

_1=$1

mkdir "$_1" > /dev/null 2>&1
if [ -f "$_1/mqsipackagebar-args.txt" ]; then
  debug "iibpackagebar.sh: reading args from $_1/mqsipackagebar-args.txt"
  _ARGS=$(cat $_1/mqsipackagebar-args.txt)
  debug "iibpackagebar.sh: _args=$_ARGS"
fi
cd ..
trace "iibpackagebar.sh: cd .."

#echo Calling $(commandShell) mqsipackagebar -a build/$_1/$_1.bar -w source -k $_1 $_ARGS 1>&2
#$(commandShell) mqsipackagebar -a build/$_1/$_1.bar -w source -k $_1 $_ARGS
#cd build
debug "iibpackagebar.sh: Calling execMQSICommand mqsipackagebar -a build/$_1/$_1.bar -w source -k $_1 $_ARGS"
OUTPUT=$(execMQSICommand mqsipackagebar -a build/$_1/$_1.bar -w source -k $_1 $_ARGS)
_RC=$?
_PAD=$_MQSI_LOG_FORMAT _WRAP=$_MQSI_LOG_WRAP notify "$OUTPUT"
cd build
trace "iibpackagebar.sh: cd build"

return $_RC 2> /dev/null || exit $_RC
