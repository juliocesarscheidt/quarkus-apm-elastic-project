# Quarkus API with APM using Elastic Stack

A sample API fetching data from MySQL, exposing metrics with prometheus and using application monitoring with Elastic APM.

```bash

docker-compose up -d mysql elasticsearch kibana apm_server
docker-compose logs -f --tail 50 mysql elasticsearch kibana apm_server

docker-compose up -d --build quarkusapi
docker-compose logs -f --tail 50 quarkusapi

docker-compose up -d metricbeat
docker-compose logs -f --tail 50 metricbeat

```
