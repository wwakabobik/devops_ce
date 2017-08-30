#!/bin/bash

check_for_commits.sh
if [ ! $? = 0 ]; then
    first_try.sh
fi