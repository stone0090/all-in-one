#!/bin/bash
set +e
path=$(pwd)
version=1.0.0
cd ../..
python setup.py sdist
pip3 install dist/aio_sdk-latest.tar.gz
docker build -t shi0090/aio-faas-python-basic:${version} -f "$path/Dockerfile" .
#docker run -d -p 6000:6000 -p 6001:6001 shi0090/aio-faas-python-basic:${version}