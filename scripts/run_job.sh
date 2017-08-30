#!/bin/bash

$HOME/devops_ce/scripts/check_for_commits.sh
if [ ! $? = 0 ]; then
    $HOME/devops_ce/scripts/first_try.sh
fi