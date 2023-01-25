FROM juliocesarmidia/java14.0.2-maven3.8.1:latest as builder
LABEL maintainer="Julio Cesar <julio@blackdevs.com.br>"

WORKDIR /app

# install dependencies
COPY ./pom.xml ./pom.xml
COPY ./src ./src
# generate package
RUN mvn \
    --no-transfer-progress package \
    --file pom.xml -D skipTests

RUN ls -lth /app

FROM openjdk:14.0.2-jdk-slim as runner

WORKDIR /app
# copy application from build stage
COPY --from=builder /app/* /app/
COPY elastic-apm-agent-1.32.0.jar elastic-apm-agent-1.32.0.jar

EXPOSE 8080

CMD java $JAVA_OPTIONS -javaagent:/app/elastic-apm-agent-1.32.0.jar -jar /app/quarkus-app/quarkus-run.jar
