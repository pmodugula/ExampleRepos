#!/usr/bin/env bash
# File: iibcreatebar.bat
# Version: 0.1.4
# Date: 2015-06-09
# Author: Gabriel Chan
# Version: 0.1.3
# Date: 9-Oct 2014
# Author: Navin Khanna

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

trace "iibcreatebar.sh ($*)"

_1=$1
debug "iibcreatebar.sh: sourcing $RESOURCE_DIR/$USER-env.sh"
sourceConfig "$RESOURCE_DIR/$USER-env.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibcreatebar.sh: failed to source $RESOURCE_DIR/$USER-env.sh"
fi
mkdir $_1 > /dev/null 2>&1
_source_dir=../source/$_1
_target_dir=$_1
if [ -n "$(which tar)" ]; then
  debug "iibcreatebar.sh: copying directory via tar"
  (cd ${_source_dir}; tar -c --exclude-vcs -f -) | (cd ${_target_dir}; tar -xf -)
else
  debug "iibcreatebar.sh: copying via cp, then find (to remove CVS dirs)"
  cp -r "${_source_dir}" "${_target_dir}"
  find "${_target_dir}" -type d -name CVS -exec rm -Rf {} \;
fi
_current_dir=$(pwd)
cd $_1
debug "iibcreatebar.sh: sourcing mqsicreatebar-env.sh"
sourceConfig "mqsicreatebar-env.sh"
_RC=$?
if [ "$_RC" -ne 0 ]; then
  warn "iibcreatebar.sh: failed to source $mqsicreatebar-env.sh"
fi
$_JAVAC $_JAVAC_ARGS
if [ -n "$_COPY_CLASSES" ]; then
  $_COPY_CLASSES
  _COPY_CLASSES=
fi
$_JAR $_JAR_ARGS
if [ -n "$_REMOVE_CLASSES" ]; then
  $_REMOVE_CLASSES
  _REMOVE_CLASSES=
fi
cd $_current_dir
