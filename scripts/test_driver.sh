#!/bin/bash

function RunSeparateTest {
    command="$1 $3 $4"
    if [ $5 -eq 0 ]; then
	eval $command >/dev/null 2>&1
	test_output=$?
    else
	test_output=`eval $command`
	test_output=`echo $test_output | tr -d $'\r'`
    fi
    if [[ "$test_output" == "$2" ]]; then
	echo "PASSED"
    else
	echo "FAILED"
	echo "; got actual $test_output, but expected $2"
    fi
}

function RunTestSuite {
    eval "declare -A localarray="${2#*=} >/dev/null
    declare -p localarray
    dimension=0
    sizeA=$(( ${#localarray[@]}/3 ))
    while [ $dimension -lt $sizeA ]; do
	counter=$(( $dimension + 1 ))
	echo "Test $counter:" `RunSeparateTest $1 ${localarray[$dimension,0]} ${localarray[$dimension,1]} ${localarray[$dimension,2]} $3`
	dimension=$counter
    done
}
