#!/usr/bin/env bash
#
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

trace "CONFIGURE.sh ($*)"

if [ -n "$1" ] && [ -n "$2" ]; then
  if [ ! -e "../source/$2/bar.txt" ]; then
    if [ -f "../source/$2/pre-configure.sh" ]; then
      trace "configure.sh: . ../source/$2/pre-configure.sh \"$1\" \"$2\""
      . ../source/$2/pre-configure.sh "$1" "$2"
    fi
  else
    trace "configure.sh: ../source/$2/bar.txt exists"
    _FLAG=0
    if [ -e "../source/$2/bar.txt" ] && [ -f "../$1-build/$2.b" ]; then
      if [ -n "$_FORCE" ] && [ "$_FORCE" -eq 1 ]; then
        _FLAG=1
      fi
      if [ ! -e "../$1${_ENVN_}${_INST_}-configure/$2.c" ]; then
        _FLAG=1
      fi
    else
      if [ -n "$_FORCE" ] && [ "$_FORCE" -eq 1 ]; then
        _FLAG=1
      fi
    fi
    if [ "$_FLAG" -eq 1 ]; then
      trace "CONFIGURE.sh: _FLAG set to 1"
      if [ "$_ONLY_MQSC" -eq 0 ]; then
        trace "configure.sh: _ONLY_MQSC==$_ONLY_MQSC, removing $1${_ENVN_}${_INST_}-deploy/$2.d"
        rm "../$1${_ENVN_}${_INST_}-deploy/$2.d" >/dev/null 2>&1
      fi
      if [ -e "../source/$2/pre-configure.sh" ]; then
        trace "configure.sh: . ../source/$2/pre-configure.sh \"$1\" \"$2\""
        . ../source/$2/pre-configure.sh "$1" "$2"
      fi
      if [ -e "../source/$2/mqsc.txt" ]; then
        _FLAG=$(cat ../source/$2/mqsc.txt)
        trace "configure.sh: set _FLAG to contents of ../source/$2/mqsc.txt: $_FLAG"
        if [ -n "$_FLAG" ]; then
          if [ ! -e "../source/$2/runmqsc${_ENVN_}${_INST_}.template" ]; then
            # TODO: this may need to be replaced with some shell script; git-bash may not have perl
            debug "configure.sh: perl template.pl ../source/$2/runmqsc.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/runmqsc${_ENVN_}${_INST_}.mqsc"
            perl $CWD/template.pl ../source/$2/runmqsc.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/runmqsc${_ENVN_}${_INST_}.mqsc
          else
            debug "configure.sh: perl template.pl ../source/$2/runmqsc${_ENVN_}${_INST_}.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/runmqsc${_ENVN_}${_INST_}.mqsc"
            perl $CWD/template.pl ../source/$2/runmqsc${_ENVN_}${_INST_}.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/runmqsc${_ENVN_}${_INST_}.mqsc
          fi
        fi
      fi
      if [ -e "../source/$2/jmsc.txt" ]; then
        _FLAG=$(cat ../source/$2/jmsc.txt)
        trace "configure.sh: set _FLAG to contents of ../source/$2/jmsc.txt: $_FLAG"
        if [ -n "$_FLAG" ]; then
          if [ ! -e "../source/$2/jmsadmin${_ENVN_}${_INST_}.template" ]; then
            # TODO: this may need to be replaced with some shell script; git-bash may not have perl
            debug "configure.sh: perl template.pl ../source/$2/jmsadmin.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/jmsadmin${_ENVN_}${_INST_}.jmsc"
            perl $CWD/template.pl ../source/$2/jmsadmin.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/jmsadmin${_ENVN_}${_INST_}.jmsc
          else
            debug "configure.sh: perl template.pl ../source/$2/jmsadmin${_ENVN_}${_INST_}.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/jmsadmin${_ENVN_}${_INST_}.jmsc"
            perl $CWD/template.pl ../source/$2/jmsadmin${_ENVN_}${_INST_}.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/jmsadmin${_ENVN_}${_INST_}.jmsc
          fi
        fi
      fi
      if [ "$_ONLY_MQSC" -eq 0 ] && [ "$_ONLY_JMSC" -eq 0 ]; then
        trace "configure.sh: _only_mqsc==0 && _only_jmsc==0"
        if [ -e "../source/$2/mqsiapplybaroverride.template" ]; then
          # TODO: this may need to be replaced with some shell script; git-bash may not have perl
          debug "configure.sh: perl template.pl ../source/$2/mqsiapplybaroverride.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/$2${_ENVN_}${_INST_}.properties"
          perl $CWD/template.pl ../source/$2/mqsiapplybaroverride.template ../source/$2/template-default.properties < ../source/$2/template${_ENVN_}${_INST_}.properties > ../source/$2/$2${_ENVN_}${_INST_}.properties
        fi
        if [ -n "$_NO_DRY_RUN" ]; then
          trace "configure.sh: . iibapplybaroverride.sh \"$2\" \"$_ENVN\" \"$_INST\""
          . $CWD/iibapplybaroverride.sh "$2" "$_ENVN" "$_INST"
          _RC=$?
        fi
        if [ ! -d "../$1${_ENVN_}${_INST_}-configure" ]; then
          mkdir "../$1${_ENVN_}${_INST_}-configure" > /dev/null 2>&1
        fi
        echo > "../$1${_ENVN_}${_INST_}-configure/$2.c"
      fi
      if [ -e "../source/$2/post-configure.sh" ]; then
        trace "configure.sh: . ../source/$2/post-configure.sh \"$1\" \"$2\""
        . ../source/$2/post-configure.sh "$1" "$2"
      fi
    fi
  fi
  if [ ! -e "../source/$2/bar.txt" ]; then
    if [ -f "../source/$2/post-configure.sh" ]; then
      trace "configure.sh: . ../source/$2/post-configure.sh \"$1\" \"$2\""
      ../source/$2/post-configure.sh "$1" "$2"
    fi
  fi
fi

return $_RC 2> /dev/null || exit $_RC
