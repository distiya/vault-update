#!/usr/bin/env bash
# Use this script to test if a given TCP host/port are available
sh /wait.sh vaultConfig:8080 -s -t 0 -- java -jar /app.jar