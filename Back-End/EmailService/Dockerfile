# FROM openjdk:21-oraclelinux8

# #COPY ./target/*.jar app.jar
# #EXPOSE 8080
# #ENTRYPOINT ["java","-jar","/app.jar"]

# WORKDIR /app
# COPY ./EmailService/.mvn/ .mvn
# COPY ./EmailService/mvnw ./EmailService/pom.xml ./
# COPY ./EmailService/mvnw dependency:go-offline
# COPY ./EmailService/src ./src
# EXPOSE 8081
# ENTRYPOINT ["sh", "./mvnw", "spring-boot:run"]

#--------------------------------------------------------------------------
FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /workdir/server
COPY ./EmailService/pom.xml /workdir/server/pom.xml
RUN mvn dependency:go-offline
    
COPY ./EmailService/src /workdir/server/src
RUN mvn -Pprod clean package -DskipTests
    
FROM builder AS dev-envs
RUN <<EOF
apt-get update
apt-get install -y --no-install-recommends git
EOF
    
RUN <<EOF
useradd -s /bin/bash -m vscode
groupadd docker
usermod -aG docker vscode
EOF
# install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /
CMD ["mvn", "spring-boot:run"]
    
FROM builder as prepare-production
RUN mkdir -p target/dependency
WORKDIR /workdir/server/target/dependency
RUN jar -xf ../*.jar
    
FROM eclipse-temurin:17-jre-focal
    
EXPOSE 8081
VOLUME /tmp
ARG DEPENDENCY=/workdir/server/target/dependency
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.NTTT.EmailService.EmailServiceApplication"]