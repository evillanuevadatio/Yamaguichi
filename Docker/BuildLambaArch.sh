#!/bin/bash

# Clean images
sudo docker rmi node-base
# Hadoop
sudo docker rmi hadoop-base
sudo docker rmi hadoop-datanode
sudo docker rmi hadoop-namenode
# SPARK
sudo docker rmi spark-base
sudo docker rmi spark-master
sudo docker rmi spark-slave
# Zookeeper
sudo docker rmi zookeeper-base
sudo docker rmi zoo-node
# Kafka
sudo docker rmi kafka-base
sudo docker rmi kafka-broker
# Neo4j
sudo docker rmi neo4j-base
sudo docker rmi neo4j-node
# Cassandra
sudo docker rmi cassandra-base
sudo docker rmi cass-node



# Create Network for hadoop
#sudo docker network create --driver=host \ 
#    --subnet=172.10.2.0/30 --gateway=172.10.0.1 --ip-range=172.10.0.0/50 nethadoop

# Create Image
sudo docker build -t node-base ./NodeBase/
sudo docker build -t hadoop-base ./HadoopBase/
sudo docker build -t hadoop-namenode ./HadoopNamenode/

