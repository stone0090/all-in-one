path=$(pwd)
version=$(date "+%Y%m%d%H%M%S")
cd ../../
mvn -e -B -U clean package -Dmaven.test.skip=true
docker build -t aio:${version} -f "$path/Dockerfile" .
docker run -dp 9090:9090 aio:${version}