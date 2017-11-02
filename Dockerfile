FROM java:openjdk-8-jre-alpine
VOLUME /tmp
ADD /maven/machine-destroyer.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]