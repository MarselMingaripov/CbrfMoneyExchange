FROM openjdk
ARG JAR_FILE=target/CbrfConverter-0.0.1-SNAPSHOT.jar
WORKDIR .
COPY target/CbrfConverter-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "CbrfConverter-0.0.1-SNAPSHOT.jar"]