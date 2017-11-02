FROM java:openjdk-8-jre-alpine
VOLUME /tmp

RUN sh -c 'ls ./var/lib'

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

RUN sh -c 'ls ./app'

ADD machine-destroyer.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]