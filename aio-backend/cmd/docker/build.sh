version=$(date "+%Y%m%d%H%M%S")
mvn -e -B -U clean package -Dmaven.test.skip=true
docker build -t aio:${version} .
docker run -dp 8080:8080 aio:${version}