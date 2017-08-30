#!/bin/bash

cd /home/qaexperts/devops_ce

locres=0

#setup environment
export INDIR="$HOME/devops_ce"
export OUTDIR="$HOME/devops_ce/bin"
export TESTDIR="$HOME/devops_ce/test"
export APPNAME="simple_app"
export CURTIMESTAMP=`date +%Y%m%d-%H%M%S`
export LOGDIR=$HOME/devops_ce/logs

LOGFILE=$LOGDIR/$CURTIMESTAMP.log
REPORTFILE="$HOME/devops_ce/report.html"

#redirect sderr to stout
2>&1

#create indir if not exists
if [ ! -d "$INDIR" ]; then
    mkdir $INDIR
fi

#create output dir if not exists
if [ ! -d "$OUTDIR" ]; then
    mkdir $OUTDIR
fi

#create logdir if not exists
if [ ! -d "$LOGDIR" ]; then
    mkdir $LOGDIR
fi

#sync git repo
$INDIR/scripts/update_repo.sh >$LOGFILE
if [ ! $? -eq 0 ]; then
    echo "Fatal error: repo sync failed";
    locres=1
fi

#build binary from source code
$INDIR/scripts/build.sh >>$LOGFILE
if [ ! $? -eq 0 ] && [ locres -eq 0 ]; then
    echo "Fatal error: build failed"
    locres=1
fi

#deploy binary
$INDIR/scripts/deploy.sh >>$LOGFILE
if [ ! $? -eq 0 ] && [ locres -eq 0 ]; then
    echo "Fatal error: deploy failed"
    locres=1
fi

#test binary
$INDIR/scripts/test.sh >>$LOGFILE
if [ ! $? -eq 0 ] && [ locres -eq 0 ]; then
    echo "Fatal error: test failed"
    locres=1
fi

#generate result
rm $REPORTFILE
$INDIR/scripts/report.sh >>$REPORTFILE
if [ ! $? -eq 0 ]; then
    echo "Fatal error: report failed" >>$LOGFILE
    exit -1
fi
