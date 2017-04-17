FROM node-base
MAINTAINER Eder Villanueva (chartrix@gmail.com)

RUN apk add --update openssh && \
    rm -rf /var/cache/apk/*

# Download hadoop 
#RUN \
#    curl -o /opt/hadoop-2.7.3.tar.gz  http://www-eu.apache.org/dist/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz && \
#    tar -xzf /opt/hadoop-2.7.3.tar.gz -C /opt/  && \
#    rm /opt/hadoop-2.7.3.tar.gz 
RUN mkdir /opt/
RUN curl -o /opt/hadoop-2.7.3.tar.gz  http://www-eu.apache.org/dist/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz
RUN tar -xzf /opt/hadoop-2.7.3.tar.gz -C /opt/
RUN rm /opt/hadoop-2.7.3.tar.gz

ENV HADOOP_HOME /opt/hadoop-2.7.3
ENV HADOOP_CONF_DIR="${HADOOP_HOME}/etc/hadoop"
ENV HADOOP_LIBEXEC_DIR="${HADOOP_HOME}/libexec"

RUN sed -i 's/export JAVA_HOME=${JAVA_HOME}/export JAVA_HOME=\/usr\/lib\/jvm\/java-1.8-openjdk/g' /opt/hadoop-2.7.3/etc/hadoop/hadoop-env.sh

ENV PATH $PATH:${HADOOP_HOME}/sbin/:${HADOOP_HOME}/bin

# Hdfs ports
EXPOSE 50010 50020 50070 50075 50090 8020 9000
# Mapred ports
EXPOSE 19888
#Yarn ports
EXPOSE 8030 8031 8032 8033 8040 8042 8088
#Other ports
EXPOSE 49707 2122

