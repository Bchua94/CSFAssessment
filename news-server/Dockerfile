FROM openjdk:19-jdk-alpine
WORKDIR /app
ENV PROFILE prod
COPY target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]