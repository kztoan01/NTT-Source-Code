# FROM openjdk:21-oraclelinux8

#COPY ./target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]

# WORKDIR /app
# COPY ./DiscoveryService/.mvn/ .mvn
# COPY ./DiscoveryService/mvnw ./DiscoveryService/pom.xml ./
# COPY ./DiscoveryService/mvnw dependency:go-offline
# COPY ./DiscoveryService/src ./src
# EXPOSE 8761
# CMD ["./mvnw", "spring-boot:run"]


#-------------------------------------------------

# syntax=docker/dockerfile:1.4

FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /workdir/server
COPY ./DiscoveryService/pom.xml /workdir/server/pom.xml
RUN mvn dependency:go-offline

COPY ./DiscoveryService/src /workdir/server/src
RUN mvn -Pprod install

FROM builder AS dev-envs
RUN apt-get update && apt-get install -y --no-install-recommends git
RUN useradd -s /bin/bash -m vscode && groupadd docker && usermod -aG docker vscode

# install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /
CMD ["mvn", "spring-boot:run"]

FROM builder as prepare-production
RUN mkdir -p target/dependency
WORKDIR /workdir/server/target/dependency
RUN jar -xf ../*.jar

FROM eclipse-temurin:17-jre-focal

EXPOSE 8761
VOLUME /tmp
ARG DEPENDENCY=/workdir/server/target/dependency
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.DiscoveryService.DiscoveryServiceApplication"]

