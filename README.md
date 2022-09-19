# Quarkus API with APM using Elastic Stack

A sample API fetching data from MySQL, exposing metrics with prometheus and using application monitoring with Elastic APM.

## Up and Running

```bash

docker-compose up -d mysql elasticsearch kibana apm_server
docker-compose logs -f --tail 50 mysql elasticsearch kibana apm_server

docker-compose up -d --build quarkusapi
docker-compose logs -f --tail 50 quarkusapi

docker-compose up -d metricbeat
docker-compose logs -f --tail 50 metricbeat

```

## Services

> API: port 8080
> Elasticsearch: port 9200
> Kibana: port 5601
> APM Server: port 8200

## Trying messages API

```bash

# service endpoints
curl --silent -X GET --url "http://127.0.0.1:8080/v1/messages"

curl --silent -X POST -H 'Content-type: application/json' \
  --data-raw '{"userId": 1, "content": "Hello World"}' \
  --url "http://127.0.0.1:8080/v1/messages"

curl --silent -X GET --url "http://127.0.0.1:8080/v1/messages/1"

curl --silent -X GET --url "http://127.0.0.1:8080/v1/messages/user/1"

# micrometer endpoints
curl --silent -X GET --url "http://127.0.0.1:8080/q/metrics"

curl --silent -X GET --url "http://127.0.0.1:8080/q/health/ready"

curl --silent -X GET --url "http://127.0.0.1:8080/q/health/live"

# generate some traffic
while true; do
  USER_ID=$RANDOM
  curl --silent -X POST -H 'Content-type: application/json' \
    --data-raw "{\"userId\": $USER_ID, \"content\": \"Message from user - $USER_ID\"}" \
    --url "http://127.0.0.1:8080/v1/messages" > /dev/null
  curl --silent -X GET --url "http://127.0.0.1:8080/v1/messages/user/$USER_ID" > /dev/null
  curl --silent -X GET --url "http://127.0.0.1:8080/v1/messages" > /dev/null
done

```

## APM Traces

Some examples of traces:

[List message](./images/list-message-traces.png)

[Create message](./images/create-message-traces.png)

## Metricbeat metrics

[List message](./images/metricbeat-metrics.png)
