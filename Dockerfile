FROM openjdk:19-jdk-alpine
WORKDIR /app/news-server
ENV PROFILE prod
COPY ./news-server/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
