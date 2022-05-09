FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /usr/app
COPY /target/patient-0.0.1-SNAPSHOT.jar Mediscreen-Patient.jar
CMD ["java", "-jar", "Mediscreen-Patient.jar"]
EXPOSE 8080