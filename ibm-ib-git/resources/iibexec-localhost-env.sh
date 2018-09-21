#!/usr/bin/env bash
# File: iibundeploy-env.bat
# Version: 0.1.0
# Date: 2015-10-30
# Author: Gabriel Chan


_BAR_STATE="mqsilist ${_NODE_} -e ${_SERVER} -k ${_PROJ_} -d 2"
_MSG_FLOW_STOP="mqsistopmsgflow ${_NODE_} -e ${_SERVER} -k ${_PROJ_}"
_MSG_FLOW_START="mqsistartmsgflow ${_NODE_} -e ${_SERVER} -k ${_PROJ_}"
_MSG_FLOW_STATE="mqsilist ${_NODE_} -e ${_SERVER} -k ${_PROJ_}"