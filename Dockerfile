FROM java:8
VOLUME ["/tmp"]
RUN sh -c 'touch /app.jar'
ADD target/machine-destroyer.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]