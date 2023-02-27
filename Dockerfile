FROM openjdk
ARG JAR_FILE=target/CbrfData-0.0.1-SNAPSHOT.jar
WORKDIR .
COPY target/CbrfData-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "CbrfData-0.0.1-SNAPSHOT.jar"]