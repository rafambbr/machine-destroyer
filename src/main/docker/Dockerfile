FROM java:8
VOLUME /tmp
RUN sh -c 'touch /app.jar'
COPY machine-destroyer-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]