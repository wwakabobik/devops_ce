#!/bin/bash

if [ -f $LOGDIR/$CURTIMESTAMP"_test.log" ]; then
    countTestPassed=`cat $LOGDIR/$CURTIMESTAMP"_test.log"| grep -o "PASSED" | wc -l`
    countTestFailed=`cat $LOGDIR/$CURTIMESTAMP"_test.log" | grep -o "FAILED" | wc -l`
fi
countIntegralErrors=`cat $LOGDIR/$CURTIMESTAMP.log | grep -o "FAILED" | wc -l`

color="green"

if [ $countIntegralErrors -ge 1 ]; then
    color="red"
else
    if [ $countTestFailed -ge 1 ]; then
	color="yellow"
    fi
fi

echo "<HTML><HEAD><TITLE>Report Example</TITLE></HEAD><BODY bgcolor='$color'>Job run on $CURTIMESTAMP"
echo "<p><li>`cat $LOGDIR/$CURTIMESTAMP.log | grep 'Repo update'`</li>"
echo "<li>`cat $LOGDIR/$CURTIMESTAMP.log | grep 'Build'`</li>"
echo "<li>`cat $LOGDIR/$CURTIMESTAMP.log | grep 'Deploy'`</li>"
echo "<li>`cat $LOGDIR/$CURTIMESTAMP.log | grep 'Test'`</li></p>"
if [ -f $LOGDIR/$CURTIMESTAMP"_test.log" ]; then
    echo "<p><HR></p><p><li>Test passed: $countTestPassed</li><li>Test failed: $countTestFailed</li></p><p><HR></p>"
fi
echo "<p>See corresponding logs for detailed result</p></BODY></HTML>"