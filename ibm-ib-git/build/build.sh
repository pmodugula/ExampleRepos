#!/usr/bin/env bash
# File: build\build.bat
# Version: 0.0.1
#   conversion to unix
# Date: 2015-06-05
# Author: Gabriel Chan

#set -x
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

trace "build.sh ($*)"

# return codes for mqsicreatebar and iibpackagebar.sh
_RC0=0
_RC1=0

_ARG1=$1
_ARG2=$2
_ARG3=$3
if [ -n "$_ARG1" ]; then
  if [ -n "$_ARG2" ]; then
    _SWITCH=0
    _BUILD_TYPE=
    _BUILD_TYPE_=
    trace "build.sh: Calling sourceConfig \"../source/$_ARG2/build-env.sh\""
    sourceConfig "../source/$_ARG2/build-env.sh"
    _RC=$?
    if [ "$_RC" -ne 0 ]; then
      warn "build.sh: failed to source ../source/$_ARG2/build-env.sh"
    fi
    if [ -n "${_BUILD_TYPE}" ]; then
      _BUILD_TYPE_="-${_BUILD_TYPE}"
    fi
    if [ -f "../source/$_ARG2/pre-build${_BUILD_TYPE_}.sh" ] && [ -x "../source/$_ARG2/pre-build${_BUILD_TYPE_}.sh" ]; then
      # TODO: convert pre-build.*.sh to bash
      trace "build.sh: . ../source/$_ARG2/pre-build${_BUILD_TYPE_}.sh"
      . ../source/$_ARG2/pre-build${_BUILD_TYPE_}.sh $_ARG3
    fi
    _FLAG=0
    if [ "${_BUILD_TYPE}" != "LOGICAL" ]; then
      trace "build.sh: _BUILD_TYPE != LOGICAL"
      if [ "${_FORCE:-0}" -eq 1 ]; then
        trace "build.sh: _FORCE flag set, so set _FLAG=1"
        _FLAG=1
      fi
      if [ -f "../source/$_ARG2/bar.txt" ]; then
        if [ ! -f "../${_ARG1}-build/${_ARG2}.b" ]; then
          trace "build.sh: set _FLAG=1"
          _FLAG=1
        fi
      else
        if [ ! -f "../${_ARG1}-build/${_ARG2}" ]; then
          trace "build.sh: set _FLAG=1"
          _FLAG=1
        fi
      fi
    fi
    # _ACTION=
    if [ "$_FLAG" -eq 1 ]; then
      if [ -f "../source/$_ARG2/dropins.txt" ] || [ -f "../source/$_ARG2/jplugin.txt" ] || [ -f "../source/$_ARG2/shared-classes.txt" ]; then
        trace "build.sh: set _ACTION=CREATE"
        _ACTION=CREATE
      fi
      if [ -f "../source/$_ARG2/bar.txt" ]; then
        trace "build.sh: set _ACTION=PACKAGE"
        _ACTION=PACKAGE
      fi
    fi
    if [ -n "$_ACTION" ]; then
      rm "$_ARG2" >/dev/null 2>&1
      if [ -f "../source/$_ARG2/bar.txt" ]; then
        rm "../${_ARG1}-build/${_ARG2}.b" >/dev/null 2>&1
      else
        rm "../${_ARG1}-build/${_ARG2}" >/dev/null 2>&1
      fi
    fi
    _DONE=0
    if [ "$_DONE" -eq 0 ] && [ "$_ACTION" = "CREATE" ]; then
      if [ -f "../source/$_ARG2/mqsicreatebar-env.sh" ] && [ -n "$_NO_DRY_RUN" ]; then
        # TODO: convert ../source/$2/mqsicreatebar-env.sh
        # TODO: convert iibcreatebar
        debug "build.sh: Calling . ./iibcreatebar.sh \"$_ARG2\""
        . $CWD/iibcreatebar.sh "$_ARG2"
      fi
      _DONE=1
    fi
    if [ "$_DONE" -eq 0 ] && [ "$_ACTION" = "PACKAGE" ]; then
      if [ -f "../source/$_ARG2/bar.txt" ]; then
        while read -r -u 6 line || [ "$line" ]; do
          if [ -n "$(grep -E '^\s*@REM' <<< $line)" ]; then continue; fi
          if [ -n "$(grep -E '^\s*REM'  <<< $line)" ]; then continue; fi
          if [ -n "$(grep -E '^\s*#'    <<< $line)" ]; then continue; fi
          line="$(tr -d '\r' <<< $line)"
          _b="$(sed 's/^\([^:]*\):.*/\1/g' <<< $line)"
          _FLAG=0
          if [ "$_FORCE" -eq 1 ] || [ ! -f "../${_ARG1}-build/$_b" ]; then
            _FLAG=1
          fi
          if [ "$_FLAG" -eq 1 ]; then
            rm "../${_ARG1}-build/$_b" >/dev/null 2>&1
            rm "$_b" >/dev/null 2>&1
            if [ -n "$_NO_DRY_RUN" ]; then
              if [ "${_BUILD_FOR_MQSIPACKAGEBAR:-0}" -ne 1 ]; then
                _BUILD_FOR_MQSIPACKAGEBAR=1
                if [ -e "../source/.metadata" ]; then
                  rm -Rf "../source/.metadata" > /dev/null 2>&1
                fi
                if [ ! -e "../source/.metadata" ]; then
                  debug "build.sh: Calling mqsicreatebar -data ../source -compileOnly"
                  OUTPUT="$(execMQSICommand mqsicreatebar -data ../source -compileOnly)"
                  _RC0=$?
                  _PAD=$_MQSI_LOG_FORMAT _WRAP=$_MQSI_LOG_WRAP notify "$OUTPUT"
                fi
              fi
              debug "build.sh: Calling mqsicreatebar -data ../source -p $_b -compileOnly"
              OUTPUT="$(execMQSICommand mqsicreatebar -data ../source -p $_b -compileOnly)"
              _RC0=$?
              _PAD=$_MQSI_LOG_FORMAT _WRAP=$_MQSI_LOG_WRAP notify "$OUTPUT"
            fi
            echo > "../${_ARG1}-build/$_b"
          fi
        done 6< "../source/$_ARG2/bar.txt"
      fi
      if [ -n "$_NO_DRY_RUN" ]; then
        debug "build.sh: Calling . $CWD/iibpackagebar.sh $_ARG2"
        . $CWD/iibpackagebar.sh $_ARG2
        _RC1=$?
      fi
      _DONE=1
    fi
    if [ "$_DONE" -ne 0 ]; then
      if [ -f "../source/${_ARG2}/bar.txt" ]; then
        trace "build.sh: removing ../${_ARG1}${_ENVN_}${_INST_}-configure/${_ARG2}.c"
        rm "../${_ARG1}${_ENVN_}${_INST_}-configure/${_ARG2}.c" >/dev/null 2>&1
        trace "build.sh: creating ../${_ARG1}-build/${_ARG2}.b"
        echo > "../${_ARG1}-build/${_ARG2}.b"
      else
        trace "build.sh: creating ../${_ARG1}-build/${_ARG2}"
        echo > "../${_ARG1}-build/${_ARG2}"
      fi
    fi
    if [ -f "../source/${_ARG2}/post-build${_BUILD_TYPE_}.sh" ]; then
      # TODO: convert post-build
      debug "build.sh: Calling . ../source/${_ARG2}/post-build${_BUILD_TYPE_}.sh"
      . ../source/${_ARG2}/post-build${_BUILD_TYPE_}.sh
    fi
  fi
fi

_RC=$(($_RC0 + $_RC1))

return $_RC 2>/dev/null || exit $_RC
