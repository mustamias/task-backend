FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/task-bk-0.0.1-SNAPSHOT.jar target/app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/app.jar"]