#!/bin/bash

######################################################################
# Purpose: Refresh the Git Repositories for IBM-IB Project Safely
######################################################################

#echo "Starting Refresh Remote...";

git config --global pull.ff only
git config --global alias.up "!git remote update -p; git merge --ff-only @{u}"

repos=(core support testing message-schemas build cygmin64 documentation installation wms-library wms-inbound wms-outbound pmm-library pmm-outbound pmm-inbound)

#for i in "${repos[@]}"
#do
#    echo "Processing $i repository...";
#    cd ../$i;
#    git checkout development;
#    git up;
#done

echo "Syncing Local Repository with Remote Central Repository ...";
git up;
echo "Finished Refresh Remote...";
