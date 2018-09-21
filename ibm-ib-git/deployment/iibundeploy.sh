#!/usr/bin/env bash
# File: deployment\iibundeploy.bat
# Date: 2015-11-20
# Author: Gabriel Chan
# Comment:
#  - remove sourcing of instance specific scripts
#  - allow specifying execution group in args
# Version: 0.1.0
# Date: 2015-10-30
# Author: Gabriel Chan
# Comment: clone from iibdeploy.sh to undeploy script


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
  CWD=$(cd $(dirname $_SOURCE_FILE)/../build; pwd)
  RESOURCE_DIR=$CWD/../resources
  FUNCTIONS_LIB=$CWD/build_functions
  if [ -f $FUNCTIONS_LIB ]; then
    . $FUNCTIONS_LIB
  fi
fi

# ------------------------------------
# functions
# ------------------------------------
_execUNDEPLOY() {
  PADTEXT='    UNDEPLOY> '
  _PAD=$PADTEXT notify "${_UNDEPLOY_BAR}"
  OUTPUT="$(${_UNDEPLOY_BAR} 2>&1 )"
  _RC=$?
  # ignore following error responses
  if [ "$_RC" -ne 0 ]; then
    if [ -n "$(grep '^BIP1063W:' <<< "$OUTPUT")" ]; then
      # msgflow not found in execution group
      _RC=0
    fi
  fi
  _PAD=$PADTEXT notify "$OUTPUT"
  return $_RC
}

# ------------------------------------



# TODO: USER or USERNAME
sourceConfig ../resources/$USER-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibundeploy.sh: Failed to source ../resources/$USER-env.sh"
fi

_NODE_=$1
_PROJ_=$2
sourceConfig ../resources/iibdeploy-${_NODE_}-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibundeploy.sh: Failed to source../resources/iibdeploy-${_NODE_}-env.sh"
fi
sourceConfig ../resources/${_HOSTNAME}-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  debug "iibundeploy.sh: Failed to source ../resources/${_HOSTNAME}-env.sh"
fi

_ENVN=
if [ -n "$3" ]; then
  _ENVN=$3
fi
_ENVN_=$_ENVN
if [ -n "$_ENVN" ]; then
  _ENVN_="-$_ENVN"
fi

_INST=
if [ -n "$4" ]; then
  _INST=$4
fi
#if [ "$_INST" = "0" ] || [ "$_INST" = "1" ]; then
#  _INST_=$_INST
#fi
#_INST_=$_INST
#if [ -n "$_INST" ]; then
#  _INST_="-$_INST"
#fi

if [ "$_HOSTNAME" = "localhost" ]; then
  sourceConfig ../resources/iibundeploy-localhost-env.sh
  _RC=$?
  if [ "$_RC" -ne 0 ]; then
    warn "iibundeploy.sh: failed to source ../resources/iibundeploy-localhost-env.sh"
  fi
  REMOVE=REMOVE-localhost.sh
else
  sourceConfig ../resources/iibundeploy-env.sh
  _RC=$?
  if [ "$_RC" -ne 0 ]; then
    warn "iibundeploy.sh: failed to source ../resources/iibundeploy-env.sh"
  fi
  REMOVE=REMOVE.sh
fi

debug "iibundeploy.sh: calling _execUNDEPLOY"
_execUNDEPLOY
_RC=$?
return $_RC 2> /dev/null || exit $_RC
