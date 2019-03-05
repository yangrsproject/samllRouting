#!/usr/bin/env bash
DOCKER_ID=$(docker ps | grep small | awk '{printf $1}')

if [ -z "$DOCKER_ID" ]; then
	echo "The docker container is not exists!"
	exit
fi

echo "Dockerid is " $DOCKER_ID

docker stop $DOCKER_ID & sleep 3000 & docker ps | grep small


