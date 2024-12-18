FROM openjdk:11-jre-slim
COPY ./target/GestionDePatients-0.0.1-SNAPSHOT.jar /usr/app/GestionDePatients-0.0.1-SNAPSHOT.jar
WORKDIR /usr/app
EXPOSE 9009
ENTRYPOINT ["java", "-jar", "GestionDePatients.jar"]