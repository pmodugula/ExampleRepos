#!/bin/bash

######################################################################
# Purpose: Refresh the Git Repositories for IBM-IB Project Safely
######################################################################

echo "Starting Refresh Remote...";
GIT_STAGING_DIR=gitstaging

echo $1;
if [ ! -d "$GIT_STAGING_DIR" ]; then
    mkdir $GIT_STAGING_DIR;
fi

cd $GIT_STAGING_DIR;
git clone $1 --branch $2 --depth 10
echo "Done."
