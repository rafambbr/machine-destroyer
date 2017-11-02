FROM java:openjdk-8-jre-alpine
VOLUME /tmp

RUN sh -c 'ls ./var/lib'

# Set the working directory to /app
WORKDIR /tmp

# Copy the current directory contents into the container at /app
ADD . /tmp

RUN sh -c 'ls /tmp'

ADD machine-destroyer.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]