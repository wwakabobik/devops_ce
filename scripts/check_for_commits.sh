#!/bin/bash

needToUpdate=`git status -s -uno | wc -l`
echo $needToUpdate

if [ ! $needToUpdate -eq 0 ]; then
    exit -1
else
    exit 0
fi