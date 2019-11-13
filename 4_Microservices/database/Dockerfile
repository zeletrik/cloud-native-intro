FROM debian:stretch-slim

ENV DYNAMODB_VERSION=latest

RUN mkdir -p /usr/share/man/man1

RUN apt-get update && \
 apt-get -y install net-tools && \
 apt-get -y install curl && \ 
 apt-get -y install --no-install-recommends default-jdk && \
 apt-get -y install zip && \
 apt-get -y install python3 && \
 apt-get -y install python3-pip

COPY log4j2.xml /usr/local/bin/

RUN curl -O https://s3-us-west-2.amazonaws.com/dynamodb-local/dynamodb_local_${DYNAMODB_VERSION}.tar.gz && \
    tar zxvf dynamodb_local_${DYNAMODB_VERSION}.tar.gz -C /usr/local/bin && \
    rm dynamodb_local_${DYNAMODB_VERSION}.tar.gz

RUN zip -d /usr/local/bin/DynamoDBLocal.jar log4j2.xml
RUN zip -u /usr/local/bin/DynamoDBLocal.jar /usr/local/bin/log4j2.xml

COPY docker-entrypoint.sh /usr/local/bin/
COPY scripts /usr/local/bin/scripts

RUN ln -s usr/local/bin/docker-entrypoint.sh /docker-entrypoint.sh 
RUN ln -s usr/local/bin/scripts /

RUN chmod 777 usr/local/bin/docker-entrypoint.sh
RUN chmod 777 /docker-entrypoint.sh
RUN pip3 install boto3

EXPOSE 8000 8008

ENTRYPOINT ["docker-entrypoint.sh"]
