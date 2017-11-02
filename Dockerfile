FROM java:openjdk-8-jre-alpine
VOLUME /tmp
RUN sh -c 'ls ./tmp'
RUN sh -c 'ls ./home'
RUN sh -c 'ls ./var'
RUN sh -c 'ls ./var/lib'
RUN sh -c 'ls ./var/lib/docker'
RUN sh -c 'ls ./var/lib/docker/tmp'
ADD machine-destroyer.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]