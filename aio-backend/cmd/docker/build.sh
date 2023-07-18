path=$(pwd)
#version=$(date "+%Y%m%d%H%M%S")
version=1.0.0
cd ../..
mvn -e -B -U clean package -Dmaven.test.skip=true
docker build -t aio-backend:${version} -f "$path/Dockerfile" .
docker run -dp 9090:9090 aio-backend:${version}