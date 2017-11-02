FROM java:openjdk-8-jdk-alpine
VOLUME ["/tmp"]
RUN sh -c 'touch /app.jar'
ADD machine-destroyer.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]