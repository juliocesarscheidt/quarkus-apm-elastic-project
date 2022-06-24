# Commands

```bash

docker-compose up -d mysql
docker-compose up -d elasticsearch
docker-compose up -d kibana
docker-compose up -d apm_server

docker-compose logs -f --tail 50

docker exec -it apm_server sh
cat /usr/share/apm-server/apm-server.yml


docker-compose up -d --build quarkusapi
docker-compose logs -f --tail 50 quarkusapi


# settings on ES to ignore disk threshold (for dev)
curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_cluster/settings -d '{ "transient": { "cluster.routing.allocation.disk.threshold_enabled": false } }'

curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_all/_settings -d '{"index.blocks.read_only_allow_delete": null}'


curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_cluster/settings -d '{"persistent": {"xpack.monitoring.collection.enabled": true}}'

curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_cluster/settings -d '{"persistent": {"xpack.monitoring.elasticsearch.collection.enabled": false}}'


curl -XGET -H "Content-Type: application/json" http://localhost:9200/_cat/nodes?v=true


# export variables from .env
export $(cat .env | xargs)


cd app/

mvn install -DskipTests=true

mvn clean compile package

# without APM
java -Xmx500m \
  -Duser.timezone=GMT \
  -Djava.security.egd=file:/dev/./urandom \
  -jar target/quarkus-app/quarkus-run.jar


export ELASTIC_APM_ENABLED=true
export ELASTIC_APM_SERVICE_NAME=quarkus-test
export ELASTIC_APM_APPLICATION_PACKAGES=com.github.juliocesarscheidt
export ELASTIC_APM_SERVER_URL=http://localhost:8200
export ELASTIC_APM_SERVICE_VERSION=v1.0.0

# with APM using env variables
java -Xmx500m \
  -javaagent:./elastic-apm-agent-1.32.0.jar \
  -Duser.timezone=GMT \
  -Djava.security.egd=file:/dev/./urandom \
  -jar target/quarkus-app/quarkus-run.jar

# with APM
java -Xmx500m \
  -javaagent:./elastic-apm-agent-1.32.0.jar \
  -Delastic.apm.service_name=quarkus-test \
  -Delastic.apm.server_url=http://localhost:8200 \
  -Delastic.apm.secret_token= \
  -Delastic.apm.application_packages=com.github.juliocesarscheidt \
  -Duser.timezone=GMT \
  -Djava.security.egd=file:/dev/./urandom \
  -jar target/quarkus-app/quarkus-run.jar


# kibana monitoring
http://localhost:5601/app/monitoring

# kibana APM
http://localhost:5601/app/apm


# metrics (prometheus)
curl --silent -X GET --url 'http://localhost:8080/q/metrics'
curl --silent -X GET --url 'http://localhost:8080/q/metrics' | egrep -i '*_counter'


# API calls
curl --silent -X GET --url 'http://localhost:8080/q/health/live' | jq -r '.'
curl --silent -X GET --url 'http://localhost:8080/q/health/ready' | jq -r '.'

curl --silent -X GET --url 'http://localhost:8080/v1/messages' | jq -r '.'

curl --silent -X GET --url 'http://localhost:8080/v1/messages/1' | jq -r '.'

curl --silent -X GET --url 'http://localhost:8080/v1/messages/user/1' | jq -r '.'

curl --silent -X POST -H 'Content-type: application/json' --data-raw '{"userId": 1, "content": "Hello World"}' --url 'http://localhost:8080/v1/messages' | jq -r '.'

curl --silent -X PUT -H 'Content-type: application/json' --data-raw '{"userId": 1, "content": "Hello World"}' --url 'http://localhost:8080/v1/messages/1'

while true; do curl --silent -X GET 'http://localhost:8080/v1/messages' | jq -r '.' > /dev/null; done

ab -c 1000 -n 10000 http://localhost:8080/v1/messages



docker exec elasticsearch /bin/bash -c "bin/elasticsearch-setup-passwords auto --batch"

docker exec elasticsearch /bin/bash -c "cat /usr/share/elasticsearch/config/elasticsearch.yml"


Changed password for user apm_system
PASSWORD apm_system = LtNqFuYeDo0NqHgXOnZt

Changed password for user kibana_system
PASSWORD kibana_system = ynh5pQjnZ6yxJYcYmAxR

Changed password for user kibana
PASSWORD kibana = ynh5pQjnZ6yxJYcYmAxR

Changed password for user logstash_system
PASSWORD logstash_system = gfwmBwmzgP5bGWKxQ6od

Changed password for user beats_system
PASSWORD beats_system = q4sd6h9rY0XLFuvbtxMW

Changed password for user remote_monitoring_user
PASSWORD remote_monitoring_user = 2HUObu9FIXSuLROP3E2j

Changed password for user elastic
PASSWORD elastic = K6yp0ZsQwEGN8NDpNbWg

```
