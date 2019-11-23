#!/bin/sh

docker run --name mongodb -d -p 27017:27017 -v /immonet_git/var/mongodb:/data/db mongo
docker run -d --name my-axon-server -p 8024:8024 -p 8124:8124
    -v /immonet_git/var/axonerver:/data
    --hostname axonserver -e AXONSERVER_HOSTNAME=axonserver axoniq/axonserver
