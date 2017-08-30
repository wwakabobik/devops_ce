#!/bin/bash

if [ ! -d $TESTDIR ]; then
    mkdir $TESTDIR
    if [ ! $? = 0 ]; then
	echo "Deploy: FAILED"
	exit -1
    fi
fi

cp $OUTDIR/$APPNAME.exe $TESTDIR/$APPNAME.exe

if [ $? -eq 0 ]; then
    chmod +x $TESTDIR/$APPNAME.exe
    if [ $? -eq 0 ]; then
	echo "Deploy: SUCCESS"
	exit 0
    else
	echo "Deploy: FAILED"
    exit -1
    fi
else
    echo "Deploy: FAILED"
    exit -1
fi