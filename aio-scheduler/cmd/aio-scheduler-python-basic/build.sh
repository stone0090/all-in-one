#!/bin/bash
set +e
path=$(pwd)
version=1.0.0
cd ../..
docker build -t shi0090/aio-scheduler-python-basic:${version} -f "$path/Dockerfile" .
#docker run -d -p 6000:6000 shi0090/aio-scheduler-python-basic:${version}