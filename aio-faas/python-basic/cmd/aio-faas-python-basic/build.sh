#!/bin/bash
set +e

# 设定版本
version=1.0.0

# 打包aio-scheduler
cd ../../../../aio-scheduler/cmd/aio-scheduler-python-basic
path=$(pwd)
cd ../..
docker build -t shi0090/aio-scheduler-python-basic:${version} -f "$path/Dockerfile" .
#docker run -d -p 6000:6000 shi0090/aio-scheduler-python-basic:${version}

# 打包aio-faas
cd ../aio-faas/python-basic/cmd/aio-faas-python-basic
path=$(pwd)
cd ../..
python setup.py sdist
pip3 install dist/aio_sdk-latest.tar.gz
docker build -t shi0090/aio-faas-python-basic:${version} -f "$path/Dockerfile" .
#docker run -d -p 6000:6000 -p 6001:6001 shi0090/aio-faas-python-basic:${version}