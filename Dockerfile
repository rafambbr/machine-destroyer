FROM java:8
VOLUME ["/tmp"]
RUN sh -c 'touch /app.jar'
RUN sh -c 'grep -ir machine-destroyer /var/lib/docker/tmp/'
ADD target/machine-destroyer.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]