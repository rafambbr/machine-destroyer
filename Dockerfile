FROM java:openjdk-8-jre-alpine
VOLUME /tmp
WORKDIR /tmp
ADD . /tmp

RUN sh -c 'ls /tmp'
RUN sh -c 'cd /tmp'
RUN sh -c './mvnw clean install -U'

ADD target/machine-destroyer.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]