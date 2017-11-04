FROM java:openjdk-8-jre-alpine
VOLUME /tmp
EXPOSE 8888

COPY machine-destroyer.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar