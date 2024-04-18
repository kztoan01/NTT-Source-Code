FROM openjdk:21-oraclelinux8

#COPY ./target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]

WORKDIR /app
COPY ./DiscoveryService/.mvn/ .mvn
COPY ./DiscoveryService/mvnw ./DiscoveryService/pom.xml ./
COPY ./DiscoveryService/mvnw dependency:go-offline
COPY ./DiscoveryService/src ./src
EXPOSE 8080
ENTRYPOINT ["sh", "./mvnw", "spring-boot:run"]

