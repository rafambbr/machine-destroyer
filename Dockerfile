FROM java:openjdk-8-jre-alpine
VOLUME ["/tmp"]
RUN sh -c 'touch /app.jar'
ADD . $SRC_DIR
COPY target/machine-destroyer.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
