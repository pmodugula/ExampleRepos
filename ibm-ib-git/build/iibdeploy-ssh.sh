#!/usr/bin/env bash
# File build/iibdeploy-ssh.sh
# Version: 0.1.1
#   conversion from dos batch file
# Date: 2015-05-26
# Author: Gabriel Chan
# ---------------------
# Version: 0.1.0
# Date: 9-Dec 2014
# Author: Alex Russell
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
  CWD=$(cd $(dirname $_SOURCE_FILE); pwd)
  RESOURCE_DIR=$CWD/../resources
  FUNCTIONS_LIB=$CWD/build_functions
  if [ -f $FUNCTIONS_LIB ]; then
    . $FUNCTIONS_LIB
  fi
fi
# OSVER=$(osVersion)

_1=$1
sourceConfig "$RESOURCE_DIR/${USER}-env.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibdeploy-ssh.sh: failed to source ${USER}-env.sh"
fi
sourceConfig "$RESOURCE_DIR/${_1}-env.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibdeploy-ssh.sh: failed to source ${_1}-env.sh
fi
_HOSTNAME=${_1}

if [ ! -d "$CWD/../cygmin64/home" ]; then
  mkdir "$CWD/../cygmin64/home" > /dev/null 2>&1
fi
if [ ! -d "$CWD/../cygmin64/${USER}" ]; then
  mkdir "$CWD/../cygmin64/${USER}" > /dev/null 2>&1
fi

shift
# TODO: change this to use system ssh
$CWD/../cygmin64/bin/ssh -i "$RESOURCE_DIR/${_USERNAME}-${_HOSTNAME}-id_rsa" "${_USERNAME}@${_HOSTNAME}" "$*"
