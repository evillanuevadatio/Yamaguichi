FROM alpine:3.5
MAINTAINER Eder Villanueva (chartrix@gmail.com)


# Default to UTF-8 file.encoding
ENV LANG C.UTF-8


ENV JAVA_DISTRIBUTION openjdk
ENV JAVA_VERSION 8

RUN apk add "$JAVA_DISTRIBUTION$JAVA_VERSION" --update && \
     rm -rf /var/cache/apk/* && \
     ln -s "/usr/lib/jvm/java-1.$JAVA_VERSION-openjdk/bin/javac" /usr/bin/javac

RUN apk add --update bash &&   \
    apk add --update curl &&   \
    apk add --update procps && \
    apk add --update sed gawk  &&  \
    apk add --update openssh  &&   rm -rf /var/cache/apk/*

ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk

ENV PATH ${PATH}:${JAVA_HOME}/bin
ENV PATH ${PATH}:${JAVA_HOME}/jre/bin



