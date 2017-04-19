FROM node-base
MAINTAINER Eder Villanueva (chartrix@gmail.com)

RUN apk add --update openssh && \
    rm -rf /var/cache/apk/*

# Download hadoop 
RUN mkdir /opt/ && \
	curl -o /opt/hadoop-2.7.3.tar.gz  http://www-eu.apache.org/dist/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz && \
	tar -xzf /opt/hadoop-2.7.3.tar.gz -C /opt/ && \
	rm /opt/hadoop-2.7.3.tar.gz && \
	mkdir -p /opt/data/tmp && \
	mkdir -p /opt/data/namenode && \
	mkdir -p /opt/data/datanode


# passwordless ssh
RUN rm -f /etc/ssh/ssh_host_dsa_key /etc/ssh/ssh_host_rsa_key /root/.ssh/id_rsa && \
	 ssh-keygen -q -N "" -t dsa -f /etc/ssh/ssh_host_dsa_key && \
	 ssh-keygen -q -N "" -t rsa -f /etc/ssh/ssh_host_rsa_key && \
	 ssh-keygen -q -N "" -t rsa -f /root/.ssh/id_rsa && \
	 cp /root/.ssh/id_rsa.pub /root/.ssh/authorized_keys

ADD ssh_config /root/.ssh/config
RUN chmod 600 /root/.ssh/config
RUN chown root:root /root/.ssh/config

ENV HADOOP_HOME /opt/hadoop-2.7.3
ENV HADOOP_CONF_DIR="${HADOOP_HOME}/etc/hadoop"
ENV HADOOP_LIBEXEC_DIR="${HADOOP_HOME}/libexec"

RUN sed -i 's/export JAVA_HOME=${JAVA_HOME}/export JAVA_HOME=\/usr\/lib\/jvm\/java-1.8-openjdk/g' /opt/hadoop-2.7.3/etc/hadoop/hadoop-env.sh

ENV PATH $PATH:${HADOOP_HOME}/sbin/:${HADOOP_HOME}/bin
#RUN ssh-keygen -f /root/.ssh/id_rsa -t rsa -N ''

ADD conf/* /opt/hadoop-2.7.3/etc/hadoop/	

# Hdfs ports
EXPOSE 50010 50020 50070 50075 50090 8020 9000
# Mapred ports
EXPOSE 19888
#Yarn ports
EXPOSE 8030 8031 8032 8033 8040 8042 8088
#Other ports
EXPOSE 49707 2122

