#!/bin/bash

gcc $INDIR/$APPNAME.c -o $OUTDIR/$APPNAME.exe
result=$?
if [ $result -eq 0 ]; then
    echo "Build status: SUCCESS"
else
    echo "Build status: FAILED"
fi