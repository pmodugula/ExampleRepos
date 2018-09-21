#!/usr/bin/env bash
# File: build\iibclean.bat
# Version: 0.2.6
# Date: 20150611
# Author: Gabriel Chan
# Comment: conversion to shell script
# Version: 0.2.5
# Date: 10-Nov 2014
# Author: Alex Russell and Navin Khanna
# Comment: Also clean build\<project> and deployment\<project> folders for good measure
# Comment: Split CLEAN_HANLDER into ERASE_HANDLER and RMDIR_HANDLER

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
$o <project> <collection> <environment> [<instance>]
For example: iibclean * caldc PP
For example: iibclean doc-transfer-* caldc PP
For example: iibclean doc-transfer-pmm-publisher caldc PP
For example: iibclean doc-transfer-*-wms-subscriber caldc PP
EOF
  RC=1
  exit $RC
}

doClean() {
  trace "doClean($*)"
  _a=$1
  _message="project '$_a' for collection '$_COLLECTION' processing.."
  _b=1
  inf "$_message"
  for i in ../${_COLLECTION}/$_a ../${_COLLECTION}-build/${_a}.b ../${_COLLECTION}${_ENVN_}${_INST_}-configure/$_a.c ../${_COLLECTION}${_ENVN_}${_INST_}-deploy/$_a ../${_COLLECTION}${_ENVN_}${_INST_}-configure/$_a.d; do
    if [ -n "$i" ] && [ -d "$i" ]; then
      rm -Rf "$i" >/dev/null 2>&1
      _b=$?
    fi
  done
  for i in ../build/$_a ../deployment/$_a; do
    if [ -n "$i" ] && [ -f "$i" ]; then
      rm -Rf "$i" >/dev/null 2>&1
      _b=$?
    fi
  done
  if [ -f "../source/$_a/bar.txt" ]; then
    debug "iibclean.sh: Reading ../source/$_a/bar.txt"
    while read -r -u 5 line || [ "$line" ]; do
      trace "iibclean.sh: read line: $line"
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
      if [ -e "../${_COLLECTION}-build/${_trimmed_token}" ]; then
        debug "iibclean.sh: removing ../${_COLLECTION}-build/${_trimmed_token}"
        rm -Rf "../${_COLLECTION}-build/${_trimmed_token}" >/dev/null 2>&1
        _b=$?
      fi
    done 5< "../source/$_a/bar.txt"
  fi
  if [ "$_b" -eq 0 ]; then
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
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' cleaned [PASS]"
    inf "$_message"
  else
    EC=1
    _message="project '${_PROJECT}' for collection '${_COLLECTION}' not cleaned [FAIL]"
    warn "$_message"
  fi
  return $EC 2>/dev/null || exit $EC
}

################################################
################################################

trace "iibclean.sh ($*)"

### parse args
if [ "${#*}" -gt 0 ]; then
  _globstate=$(set +o | grep noglob)
  set -o noglob
  COUNT_ARGS=${#*}
  COMMAND_ARGS=($*)
  $_globstate
  debug "iibclean.sh: $COUNT_ARGS arguments read"
  debug "iibclean.sh: $*"
fi
if [ -z "$COUNT_ARGS" ] || [ "$1" = "/?" ] || [ "$1" = "/h" ] || [ "$1" = "--help" ] || [ "$1" = "-h" ]; then
  printUsage
fi

if [ "${COUNT_ARGS}" -lt 2 ]; then
  printUsage
fi

_PROJECT=$1
_COLLECTION=$2


if [ "${COUNT_ARGS}" -ge 3 ]; then
  _ENVN=$3
  if [ -n "$_ENVN" ]; then
    _ENVN_="-$_ENVN"
  fi
fi
if [ "${COUNT_ARGS}" -ge 4 ]; then
  _INST=$4
  if [ -n "$_INST" ]; then
    _INST_="-$_INST"
  fi
fi

_ENV_="-env"

sourceConfig "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}${_ENV_}.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  _message="Collection $_COLLECTION settings do not exist: $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}${_ENV_}.sh"
  warn "$_message"
  RC=2
  doFinish
fi

if [ -d "../${_COLLECTION}-build" ]; then
  for i in "../${_COLLECTION}-build"; do
    if [ -f "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat" ]; then
      debug "iibclean.sh: Reading $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat"
      while read -r line || [ "$line" ]; do
        trace "iibclean.sh: read line: $line"
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
          debug "iibclean.sh: Calling doClean $_TOKEN"
          doClean "$_TOKEN"
        fi
      done < "$RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat"
    else
      error "iibclean.sh: $RESOURCE_DIR/${_COLLECTION}${_ENVN_}${_INST_}.dat not found"
    fi
  done
fi



