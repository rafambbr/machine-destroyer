FROM java:openjdk-8-jre-alpine
VOLUME /tmp
ADD . $SRC_DIR
RUN sh -c 'echo $SRC_DIR'
ADD target/machine-destroyer.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]