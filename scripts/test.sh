#!/bin/bash

declare -A test_data
#TODO: point to refactor - read from file

#nothing to add
test_data[0,0]="0"
test_data[0,1]="0"
test_data[0,2]="0"
#test trivial
test_data[1,0]="4"
test_data[1,1]="2"
test_data[1,2]="2"
#test negative equation
test_data[2,0]="-83"
test_data[2,1]="70"
test_data[2,2]="-153"
#test maximum, assume 64-bit system limits (posix)
test_data[3,0]="9223372036854775807"
test_data[3,1]="9223372036854775807"
test_data[3,2]="1"
#test minimum, assume 64-bit system limits (posix)
test_data[4,0]="-9223372036854775808"
test_data[4,1]="-9223372036854775808"
test_data[4,2]="-1"

curDIR=$PWD
cd $INDIR/scripts
source ./test_driver.sh

echo "Running testsuite for return values..." >$LOGDIR/$CURTIMESTAMP"_test".log 2>&1
RunTestSuite $TESTDIR/$APPNAME.exe "$(declare -p test_data)" 0 >>$LOGDIR/$CURTIMESTAMP"_test".log 2>&1
echo "Running testsuite for output..." >>$LOGDIR/$CURTIMESTAMP"_test".log 2>&1
RunTestSuite $TESTDIR/$APPNAME.exe "$(declare -p test_data)" 1 >>$LOGDIR/$CURTIMESTAMP"_test".log 2>&1 

if [ ! $? -eq 0 ]; then
    echo "Testing: FAILED"
    exit -1
fi

cd $curDIR
echo "Testing: SUCCESS"
exit 0