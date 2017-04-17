#!/bin/bash

# Clean images
sudo docker rmi NodeBase
sudo docker rmi hadoop-base

# Create Network for hadoop
#sudo docker network create --driver=host --subnet=172.10.2.0/30 --gateway=172.10.0.1 --ip-range=172.10.0.0/50 nethadoop

# Create Image
sudo docker build -t node-base ./NodeBase/
sudo docker build -t hadoop-base ./HadoopBase/


