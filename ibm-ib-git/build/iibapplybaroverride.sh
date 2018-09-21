#!/usr/bin/env bash
# File: build\iibapplybaroverride.bat
# Version: 0.1.6
# Date: 2015-06-16
# Author: Gabriel Chan
# Comment: conversion from batch to shell script
# Version: 0.1.5
# Date: 28-Nov 2014
# Author: Alex Russell
# Comment: now using notion of 'working' directory as initial BAR now is same as Production BAR (never was until first attempt to deploy to Production)

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

trace "iibapplybaroverride.sh ($*)"

# For example: %1 => core-batch-initiator
# SET "_WORKPATH=..\source"
# For example: %2 => DV
# For example: %3 => 2

_1=$1
_ENVN=$2
_INST=$3
if [ -n "$_ENVN" ] && [ -z "$(grep '^-' <<< $_ENVN)" ]; then
  _ENVN="-$_ENVN"
fi

if [ -n "$_INST" ]; then
  if [ "$_INST" -eq 0 ] || [ "$_INST" -eq 1 ]; then
    _INST=
  elif [ -z "$(grep '^-' <<< $_INST)" ]; then
    _INST="-$_INST"
  fi
fi

mkdir "../working" >/dev/null 2>&1
debug "iibapplybaroverride.sh: cp $_1/$_1.bar ../working/$_1${_ENVN}${_INST}.bar"
cp $_1/$_1.bar ../working/$_1${_ENVN}${_INST}.bar
debug "iibapplybaroverride.sh: Calling mqsiapplybaroverride -b ../working/$_1${_ENVN}${_INST}.bar -k $_1 -p ../source/$_1/$_1${_ENVN}${_INST}.properties"
OUTPUT="$(execMQSICommand mqsiapplybaroverride -b ../working/$_1${_ENVN}${_INST}.bar -k $_1 -p ../source/$_1/$_1${_ENVN}${_INST}.properties )"
_RC=$?
_PAD=$_MQSI_LOG_FORMAT _WRAP=$_MQSI_LOG_WRAP notify "$OUTPUT"
mkdir "../deployment/$_1" > /dev/null 2>&1
debug "iibapplybaroverride.sh: mv ../working/$_1${_ENVN}${_INST}.bar ../deployment/$_1"
mv ../working/$_1${_ENVN}${_INST}.bar ../deployment/$_1

return $_RC 2> /dev/null || exit $_RC
