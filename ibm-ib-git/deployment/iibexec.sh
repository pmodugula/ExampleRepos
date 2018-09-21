#!/usr/bin/env bash
# File: deployment\iibexec.bat
# Date: 2015-11-20
# Author: Gabriel Chan
# Comment:
#  - remove sourcing of instance specific scripts
#  - allow specifying execution group in args
# Version: 0.1.0
# Date: 2015-10-30
# Author: Gabriel Chan
# Comment: clone from iibstate.sh to exec script


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
# check state of message flow
#   return code is 0 if up
#                  1 if down
#                  2 if not found
#                  3 if error (or unrecognized)
_checkSTATE() {
  OUTPUT="$(${_MSG_FLOW_STATE} 2>&1 )"
  _RC=$?
  if [ -n "$(grep -E '^BIP127[68]I' <<< "$OUTPUT")" ]; then
    # application or message flow currently stopped
    _RC=1
  elif [ -n "$(grep -E '^BIP127[57]I' <<< "$OUTPUT")" ]; then
    # application or message flow is currently running
    _RC=0
  elif [ -n "$(grep -E '^BIP1019E' <<< "$OUTPUT")" ]; then
    _RC=2
  else
    _RC=3
  fi
}

_execSTATE() {
  PADTEXT='    STATE> '
  _PAD=$PADTEXT notify "${_MSG_FLOW_STATE}"
  OUTPUT=
  _checkSTATE
  _RC=$?
  _PAD=$PADTEXT notify "$OUTPUT"
  if [ -n "$(grep -E '^BIP8071I:' <<< "$OUTPUT")" ]; then
    # msgflow not found in execution group
    _RC=0
  else
    _RC=1
  fi
  return $_RC
}

_execSTOP() {
  PADTEXT='    FLOWSTOP> '
  OUTPUT=
  debug "iibexec.sh: _execSTOP - checking message flow state"
  _checkSTATE
  _RC=$?
  if [ "$_RC" -eq 1 ]; then
    # already down
    inf "iibexec.sh: _execSTOP - message flow already stopped"
    _RC=0
  elif [ "$_RC" -eq 0 ]; then
    # msg flow is running. Do stop
    _PAD=$PADTEXT notify "${_MSG_FLOW_STOP}"
    OUTPUT="$(${_MSG_FLOW_STOP} 2>&1 )"
    _RC=$?
    _PAD=$PADTEXT notify "$OUTPUT"
  else
    warn "iibexec.sh: _execSTOP - unknown status"
    _PAD='    STATE> ' warn "$OUTPUT"
  fi
  return $_RC
}

_execSTART() {
  PADTEXT='    FLOWSTART> '
  OUTPUT=
  debug "iibexec.sh: _execSTART - checking message flow state"
  _checkSTATE
  _RC=$?
  if [ "$_RC" -eq 0 ]; then
    # already running
    inf "iibexec.sh: _execSTART - message flow already running"
    _RC=0
  elif [ "$_RC" -eq 1 ]; then
    # msg flow is stopped. Do start
    _PAD=$PADTEXT notify "${_MSG_FLOW_START}"
    OUTPUT="$(${_MSG_FLOW_START} 2>&1 )"
    _RC=$?
    _PAD=$PADTEXT notify "$OUTPUT"
  else
    warn "iibexec.sh: _execSTART - unknown status"
    _PAD='    STATE> ' warn "$OUTPUT"
  fi
  return $_RC
}

# ------------------------------------



# TODO: USER or USERNAME
sourceConfig ../resources/$USER-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibexec.sh: Failed to source ../resources/$USER-env.sh"
fi

_NODE_=$1
_PROJ_=$2
sourceConfig ../resources/iibdeploy-${_NODE_}-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibexec.sh: Failed to source../resources/iibdeploy-${_NODE_}-env.sh"
fi
sourceConfig ../resources/${_HOSTNAME}-env.sh
_RC=$?
if [ "$_RC" -ne 0 ]; then
  debug "iibexec.sh: Failed to source ../resources/${_HOSTNAME}-env.sh"
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
  sourceConfig ../resources/iibexec-localhost-env.sh
  _RC=$?
  if [ "$_RC" -ne 0 ]; then
    warn "iibexec.sh: failed to source ../resources/iibexec-localhost-env.sh"
  fi
  REMOVE=REMOVE-localhost.sh
else
  sourceConfig ../resources/iibexec-env.sh
  _RC=$?
  if [ "$_RC" -ne 0 ]; then
    warn "iibexec.sh: failed to source ../resources/iibexec-env.sh"
  fi
  REMOVE=REMOVE.sh
fi

_RC=
_ACTION="$(tr '[A-Z]' '[a-z]' <<< $_ACTION)"
if [ "$_ACTION" = "stop" ]; then
  debug "iibexec.sh: calling _execSTOP"
  _execSTOP
  _RC=$?
elif [ "$_ACTION" = "start" ]; then
  debug "iibexec.sh: calling _execSTART"
  _execSTART
  _RC=$?
elif [ "$_ACTION" = "state" ]; then
  debug "iibexec.sh: calling _execSTATE"
  _execSTATE
  _RC=$?
else
  warn "iibexec.sh: Action $_ACTION not defined"
  _RC=1
fi

return $_RC 2> /dev/null || exit $_RC
