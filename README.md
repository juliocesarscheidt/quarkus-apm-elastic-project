
```bash

docker-compose up -d elasticsearch
docker-compose up -d kibana
docker-compose up -d apm_server

docker-compose logs -f --tail 50

docker exec -it apm_server sh
cat /usr/share/apm-server/apm-server.yml


docker exec -it elasticsearch sh

curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_cluster/settings -d '{ "transient": { "cluster.routing.allocation.disk.threshold_enabled": false } }'

curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_all/_settings -d '{"index.blocks.read_only_allow_delete": null}'

```

<https://www.elastic.co/guide/en/apm/agent/java/master/setup-javaagent.html>

<https://www.elastic.co/guide/en/apm/guide/master/running-on-docker.html>

<https://www.elastic.co/guide/en/kibana/current/docker.html>

```bash

cd app/

mvn install

mvn clean package

export ELASTIC_APM_SERVICE_NAME=quarkus-test
export ELASTIC_APM_APPLICATION_PACKAGES=com.github.juliocesarscheidt
export ELASTIC_APM_SERVER_URL=http://localhost:8200
export ELASTIC_APM_SERVICE_VERSION=v1.0.0

java -Xmx500m \
  -javaagent:./elastic-apm-agent-1.32.0.jar \
  -Delastic.apm.service_name=quarkus-test \
  -Delastic.apm.server_url=http://localhost:8200 \
  -Delastic.apm.secret_token= \
  -Delastic.apm.application_packages=com.github.juliocesarscheidt \
  -Duser.timezone=GMT \
  -Djava.security.egd=file:/dev/./urandom \
  -jar target/app-runner.jar


curl --silent -X GET --url 'http://localhost:8080/v1/messages' | jq -r '.'

curl --silent -X POST -H 'Content-type: application/json' --data-raw '{"userId": 1, "content": "Hello World"}' --url 'http://localhost:8080/v1/messages' | jq -r '.'

while true; do curl --silent -X GET 'http://localhost:8080/v1/messages/1' | jq -r '.' > /dev/null; done

```
