FROM openjdk:21-jdk

WORKDIR /app

COPY starter/target/starter-1.0-SNAPSHOT.jar app.jar

EXPOSE 8081

#CMD ["java", "-jar", "--enable-preview", "app.jar"]
CMD ["java", "-jar", "app.jar"]