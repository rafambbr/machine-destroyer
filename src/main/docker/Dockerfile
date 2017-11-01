FROM java:openjdk-8-jdk-alpine
VOLUME ["/tmp"]
RUN sh -c 'touch /app.jar'
RUN sh -c 'ls ./home'
ADD ./machine-destroyer.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]