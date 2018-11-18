FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD dubbo-provider.jar dubbo-provider.jar
RUN sh -c 'touch /dubbo-provider.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /dubbo-provider.jar" ]