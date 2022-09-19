FROM juliocesarmidia/java14.0.2-maven3.8.1:latest
LABEL maintainer="Julio Cesar <julio@blackdevs.com.br>"

WORKDIR /usr/src/app

COPY . .
# generate package
# RUN mvn --batch-mode package --file pom.xml -D skipTests

CMD java -Xmx500m -javaagent:./elastic-apm-agent-1.32.0.jar -Duser.timezone=GMT -Djava.security.egd=file:/dev/./urandom -jar target/quarkus-app/quarkus-run.jar
