#!/bin/bash

cd $INDIR
git init
git pull https://github.com/wwakabobik/devops_ce.git master
result=$?

if [ $result -eq 0 ]; then
    echo "Repo update: SUCCESS"
    exit 0
else
    echo "Repo update: FAILED"
    exit -1
fi
