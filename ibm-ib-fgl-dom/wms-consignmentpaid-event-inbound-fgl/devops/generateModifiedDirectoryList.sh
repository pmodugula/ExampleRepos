#!/bin/bash

######################################################################
# Purpose: Generate a list of files modified in last commit
######################################################################
#git diff-tree --no-commit-id --name-only -r HEAD > git_modified_files.txt

#git log --no-merges --name-only --numstat --no-notes --since="$1" > git_modified_files.txt

#Derek to debug --- issue with repo sequences
#git log --no-merges --name-only --no-notes --pretty=oneline ${bamboo_planRepository_previousRevision}..${bamboo_planRepository_revision} > git_modified_files.txt

echo "Skipping changeset -- build all should be enabled..."
touch git_modified_files.txt

echo "Done generating changeset file from git into git_modified_files.txt";
echo "Contents..."
#cat git_modified_files.txt
