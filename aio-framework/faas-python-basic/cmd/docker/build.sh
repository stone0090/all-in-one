path=$(pwd)
#version=$(date "+%Y%m%d%H%M%S")
version=0.0.1
cd ../../
python setup.py sdist
pip3 install dist/aio_sdk-latest.tar.gz
docker build -t shi0090/faas-python-basic:${version} -f "$path/Dockerfile" .
docker run -dp 6001:6001 shi0090/faas-python-basic:${version}