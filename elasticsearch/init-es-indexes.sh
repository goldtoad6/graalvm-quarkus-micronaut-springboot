#!/usr/bin/env bash

echo
echo "Deleting existing indexes"
echo "-------------------------"
curl -X DELETE localhost:9200/quarkus.movies.jvm
curl -X DELETE localhost:9200/micronaut.movies.jvm
curl -X DELETE localhost:9200/springboot.movies.jvm
curl -X DELETE localhost:9200/quarkus.movies.native
curl -X DELETE localhost:9200/micronaut.movies.native
curl -X DELETE localhost:9200/springboot.movies.native
echo

echo "Fix High Disk Watermark"
echo "-----------------------"
# Reference: https://stackoverflow.com/questions/63880017/elasticsearch-docker-flood-stage-disk-watermark-95-exceeded

curl -X PUT  http://localhost:9200/_cluster/settings \
  -H "Content-Type: application/json" \
  -d '{ "transient": { "cluster.routing.allocation.disk.threshold_enabled": false } }'

curl -X PUT http://localhost:9200/_all/_settings \
  -H "Content-Type: application/json" \
  -d '{ "index.blocks.read_only_allow_delete": null }'

echo
echo "Creating indexes"
echo "----------------"
curl -X PUT localhost:9200/quarkus.movies.jvm -H "Content-Type: application/json" -d @movies-mapping.json
curl -X PUT localhost:9200/micronaut.movies.jvm -H "Content-Type: application/json" -d @movies-mapping.json
curl -X PUT localhost:9200/springboot.movies.jvm -H "Content-Type: application/json" -d @movies-mapping.json
curl -X PUT localhost:9200/quarkus.movies.native -H "Content-Type: application/json" -d @movies-mapping.json
curl -X PUT localhost:9200/micronaut.movies.native -H "Content-Type: application/json" -d @movies-mapping.json
curl -X PUT localhost:9200/springboot.movies.native -H "Content-Type: application/json" -d @movies-mapping.json
