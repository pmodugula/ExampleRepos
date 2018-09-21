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

trace "DEPLOY.sh ($*)"

if [ -n "$1" ] && [ -n "$2" ]; then
  if [ -e "../source/$2/pre-deploy.bat" ]; then
    # TODO convert pre-deploy
    trace "DEPLOY.sh: calling ../source/$2/pre-deploy.bat"
    . ../source/$2/pre-deploy.sh "$1" "$2"
  else
    trace "DEPLOY.sh: ../source/$2/pre-deploy.sh not found"
  fi
  _FLAG=0
  if [ -e "../source/$2/bar.txt" ]; then
    trace "DEPLOY.sh: ../source/$2/bar.txt found"
    trace "DEPLOY.sh: looking for ../$1${_ENVN_}${_INST_}-configure/$2.c"
    if [ -e "../$1${_ENVN_}${_INST_}-configure/$2.c" ]; then
      trace "DEPLOY.sh: ../$1${_ENVN_}${_INST_}-configure/$2.c found"
      _FLAG=1
    fi
  else
    if [ -e "../$1${_ENVN_}${_INST_}-build/$2" ]; then
      trace "DEPLOY.sh: ../$1${_ENVN_}${_INST_}-build/$2 found"
      _FLAG=1
    elif [ -e "../$1${_ENVN_}${_INST_}-configure/$2" ]; then
      trace "DEPLOY.sh: ../$1${_ENVN_}${_INST_}-configure/$2"
      _FLAG=1
    elif [ -e "../source/$2/jplugin.txt" ] || [ -e "../source/$2/shared-classes.txt" ]; then
      trace "DEPLOY.sh: one or both ../source/$2/jplugin.txt ../source/$2/shared-classes.txt found"
      _FLAG=1
    fi
  fi
  trace "DEPLOY.sh: _FLAG == $_FLAG"
  if [ "$_FLAG" -eq 1 ]; then
    trace "DEPLOY.sh: evaluating _FLAG==1"
    if [ "${_FORCE:-0}" -eq 1 ]; then
      trace "DEPLOY.sh: _FORCE == $_FORCE"
      _FLAG=2
    elif [ -e "../source/$2/bar.txt" ] && [ ! -e "../$1${_ENVN_}${_INST_}-deploy/$2.d" ]; then
      trace "DEPLOY.sh: found ../source/$2/bar.txt or ../$1${_ENVN_}${_INST_}-deploy/$2.d"
      _FLAG=2
    elif [ ! -e "../$1${_ENVN_}${_INST_}-deploy/$2" ]; then
      trace "DEPLOY.sh: ../$1${_ENVN_}${_INST_}-deploy/$2 .. setting _FLAG=2"
      _FLAG=2
    fi
  fi
  if [ "$_FLAG" -eq 2 ]; then
    trace "DEPLOY.sh: evaluating _FLAG==2"
    debug "DEPLOY.sh: cd to ../deployment"
    cd ../deployment
    if [ -e "../source/$2/bar.txt" ]; then
      debug "DEPLOY.sh: rm $1${_ENVN_}${_INST_}-deploy/$2.d"
      rm -f "$1${_ENVN_}${_INST_}-deploy/$2.d" >/dev/null 2>&1
    else
      debug "DEPLOY.sh: rm $1${_ENVN_}${_INST_}-deploy/$2"
      rm -f "$1${_ENVN_}${_INST_}-deploy/$2" >/dev/null 2>&1
    fi
    if [ "${_NO_DRY_RUN:-0}" -eq 1 ]; then
      _MQSC_SWITCH=
      if [ "${_ONLY_MQSC:-0}" -eq 1 ]; then
        _MQSC_SWITCH="/mqsc"
      fi
      _JMSC_SWITCH=
      if [ "${_ONLY_JMSC:-0}" -eq 1 ]; then
        _JMSC_SWITCH="/jmsc"
      fi
      trace "DEPLOY.sh: calling iibdeploy.sh \"$_NODE\" \"$2\" \"$_ENVN\" \"$_INST\" \"$_MQSC_SWITCH$_JMSC_SWITCH\""
      . ./iibdeploy.sh "$_NODE" "$2" "$_ENVN" "$_INST" "$_MQSC_SWITCH$_JMSC_SWITCH"
      _RC=$?
      debug "DEPLOY.sh: cd to ../build"
      cd ../build
      if [ ! -d "../$1${_ENVN_}${_INST_}-deploy" ]; then
        trace "DEPLOY.sh: creating dir ../$1${_ENVN_}${_INST_}-deploy"
        mkdir "../$1${_ENVN_}${_INST_}-deploy" > /dev/null 2>&1
      fi
      if [ -e "../source/$2/bar.txt" ]; then
        trace "DEPLOY.sh: creating file ../$1${_ENVN_}${_INST_}-deploy/$2.d"
        echo > "../$1${_ENVN_}${_INST_}-deploy/$2.d"
      else
        trace "DEPLOY.sh: creating file ../$1${_ENVN_}${_INST_}-deploy/$2"
        echo > "../$1${_ENVN_}${_INST_}-deploy/$2"
      fi
    fi
  fi
  if [ -e "../source/$2/post-deploy.sh" ]; then
    # TODO convert post-deploy.sh
    trace "DEPLOY.sh: calling ../source/$2/post-deploy.bat"
    . ../source/$2/post-deploy.sh
  fi
fi
return $_RC 2> /dev/null || exit $_RC
