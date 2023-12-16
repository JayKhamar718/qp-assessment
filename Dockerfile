FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar /app/questionpro.jar

EXPOSE 8080

CMD ["java", "-jar", "questionpro.jar"]