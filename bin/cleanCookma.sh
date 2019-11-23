#!/bin/sh

echo "Clean complete persistent Cookma Enviroment to have a clean start"

echo "Remove all Axon Server Files"
rm -Rf /immonet_git/var/axonserver/*

echo "Remove all H2 Files"
rm -Rf /immonet_git/var/h2/*

echo "Drop Database cookma in MongoDB"
mongo cookma --eval "{db.dropDatabase()}"

Echo "Restart Axon Server Docker Container"
docker restart axonserver
