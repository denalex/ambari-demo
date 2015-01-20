#!/usr/bin/env bash
mvn clean install
scp target/rabbitmq-view-1.0.0.jar root@c6401:/var/lib/ambari-server/resources/views/
ssh root@c6401 "rm -rf /var/lib/ambari-server/resources/views/work/RABBIT* && ambari-server restart"
