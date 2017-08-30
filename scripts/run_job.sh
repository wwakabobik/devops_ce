#!/bin/bash

$HOME/devops_ce/scripts/check_for_commits.sh
if [ ! $? = 0 ]; then
    echo "Job started on "`date +%Y%m%d-%H%M%S` >>$HOME/devops_ce/logs/scheduler.log 2>&1
    $HOME/devops_ce/scripts/first_try.sh
else
    echo "Nothing to update, job stopped" >>$HOME/devops_ce/logs/scheduler.log 2>&1
fi