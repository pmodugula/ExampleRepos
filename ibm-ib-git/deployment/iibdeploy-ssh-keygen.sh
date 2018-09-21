#!/usr/bin/env bash
# File: deployment\iibdeploy-ssh-keygen.sh
# Version: 0.1.4
# Date: 27-Nov 2014
# Author: Alex Russell

# For example: %1=>10.103.1.162
# For example: %1=>10.100.10.197
# For example: %1=>10.100.10.218
# For example: %1=>10.100.10.219

_1=$1
if [ -e "../resources/$USER-env.sh" ]; then
  . ../resources/$USER-env.sh
fi
if [ -e "../resources/${_1}-env.sh" ]; then
  . ../resources/${_1}-env.sh
fi
_HOSTNAME=$_1

mkdir ../cygmin64/home > /dev/null 2>&1
mkdir ../cygmin64/home/$USER > /dev/null 2>&1

__SHELL="ssh ${_USERNAME}@${_HOSTNAME}"
$__SHELL 'if [ ! -f "$HOME/.ssh/id_rsa" ];then ssh-keygen -f "$HOME/.ssh/id_rsa" -N "";cd "$HOME/.ssh";cat id_rsa.pub >> authorized_keys;chmod 644 authorized_keys;fi;cat "$HOME/.ssh/id_rsa"' > ../resources/${_USERNAME}-${_HOSTNAME}-id_rsa
# @REM chmod 600 ../resources/%_USERNAME%-%_HOSTNAME%-id_rsa
