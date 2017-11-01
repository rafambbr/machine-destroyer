FROM java:openjdk-8-jdk-alpine
VOLUME ["/tmp"]
RUN sh -c 'touch /app.jar'
RUN sh -c 'pwd'
RUN sh -c 'ls ./'
RUN sh -c 'ls ./tmp'
COPY ./machine-destroyer.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]