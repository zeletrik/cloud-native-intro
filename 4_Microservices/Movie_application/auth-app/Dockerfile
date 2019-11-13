FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /target/auth-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3001
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]