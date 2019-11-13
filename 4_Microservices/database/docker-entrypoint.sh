#!/bin/bash

echo "Starting DynamoDB"
java -jar /usr/local/bin/DynamoDBLocal.jar --sharedDb -inMemory  > /opt/dynamolog & 
timer="0"
while netstat -lnt | awk '$4 ~ /:8000$/ {exit 1}'; do 
    echo "Waiting for DynamoDB to be ready...";
    sleep 2;
    timer=$[$timer+2]
    if [ "$timer" == "20" ]; then
        echo "Failed to start Dynamo DB";
        exit 1
    fi
done

echo "DynamoDB started"
echo "Run init" &
python3 ./scripts/init.py
tail -f -n +0 /opt/dynamolog 
