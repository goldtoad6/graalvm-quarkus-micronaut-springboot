#!/bin/sh

SECONDS=0

if [ "$1" = "native" ];
then
  ./mvnw package -Pgraalvm -Dpackaging=docker-native -Djib.to.image=ivanfranchin/micronaut-simple-api-native:1.0.0
else
  ./mvnw package -Dpackaging=docker
fi

duration=$SECONDS
echo "$(($duration / 60)) minutes and $(($duration % 60)) seconds elapsed."