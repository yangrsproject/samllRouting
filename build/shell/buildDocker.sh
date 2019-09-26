#!/usr/bin/env bash
set -e

DOCKER_HOME="$(cd "`dirname "$0"`"/; cd ../docker/; pwd)"

JAR_HOME="$(cd "`dirname "$0"`"/; cd ../../target/; pwd)"

cp "$JAR_HOME"/*.jar "$DOCKER_HOME"

docker build -t small "$DOCKER_HOME"
