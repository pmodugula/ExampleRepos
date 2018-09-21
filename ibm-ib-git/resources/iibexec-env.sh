#!/usr/bin/env bash
# File: iibundeploy-env.bat
# Version: 0.1.0
# Date: 2015-10-30
# Author: Gabriel Chan

# SET "_ID_RSA=/cygdrive/c/eai/ibm-ib/resources/%_USERNAME%-%_HOSTNAME%-id_rsa"
_ID_RSA=~/${_USERNAME}-${_HOSTNAME}-id_rsa

_SHELL="ssh -i $_ID_RSA $_USERNAME@$_HOSTNAME"
_SHELL_BRKADM="ssh -i $_ID_RSA -oStrictHostKeyChecking=false $_USERNAME@$_HOSTNAME"
_BRK_USER=brkadm

_BAR_STATE="$_SHELL_BRKADM sudo su - ${_BRK_USER} -c 'mqsilist ${_NODE_} -e ${_SERVER} -k ${_PROJ_} -d 2'"
_MSG_FLOW_STOP="$_SHELL_BRKADM sudo su - ${_BRK_USER} -c 'mqsistopmsgflow ${_NODE_} -e ${_SERVER} -k ${_PROJ_}'"
_MSG_FLOW_START="$_SHELL_BRKADM sudo su - ${_BRK_USER} -c 'mqsistartmsgflow ${_NODE_} -e ${_SERVER} -k ${_PROJ_}'"
_MSG_FLOW_STATE="$_SHELL_BRKADM sudo su - ${_BRK_USER} -c 'mqsilist ${_NODE_} -e ${_SERVER} -k ${_PROJ_}'"