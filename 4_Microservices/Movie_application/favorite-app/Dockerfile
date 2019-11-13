FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /target/favorite-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]