#!/bin/bash
set +e
path=$(pwd)
version=$(date "+%Y%m%d%H%M%S")
cd ../..
docker build -t shi0090/aio-scheduler-builder:${version} -f "$path/Dockerfile" .
container_id=$(docker run -d shi0090/aio-scheduler-builder:${version})
docker cp ${container_id}:/home/admin/aio-scheduler ./aio-scheduler
docker rm -f ${container_id}
docker rmi shi0090/aio-scheduler-builder:${version}