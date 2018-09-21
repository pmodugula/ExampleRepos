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

trace "iibcheck-classes.sh ($*)"

if [ -z "$(locateMQSIProfile)" ]; then
# if [ -z "$MQSI_FILEPATH" ]; then
 error "this command must be run in an Integration Bus Console!"
else
 ls -la "C:\Program Files (x86)\IBM\IntegrationToolkit90\dropins"
 ls -la "%MQSI_FILEPATH%\jplugin"
 ls -la "C:\ProgramData\IBM\MQSI\shared-classes"
fi