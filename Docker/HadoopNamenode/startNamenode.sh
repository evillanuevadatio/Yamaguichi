#!/bin/bash

#service ssh restart
#/etc/init.d/sshd restart
/usr/sbin/sshd start
#sshpass -p screencast ssh-copy-id root@localhost

export HOSTNAME=`hostname`
sed -i "s#localhost#$HOSTNAME#g" /opt/hadoop-2.7.3/etc/hadoop/core-site.xml

hdfs namenode -format -force

# Start HDFS services
start-dfs.sh
# Wait for HDFS services to be up and running
sleep 5

# Create a tmp directory and make it accessible to everyone
hadoop fs -mkdir -p /tmp
hadoop fs -chmod -R 777 /tmp

# Run in daemon mode, don't exit
while true; do
  sleep 100;
done
