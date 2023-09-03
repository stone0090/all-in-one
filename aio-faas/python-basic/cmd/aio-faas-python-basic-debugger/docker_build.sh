#!/bin/bash
set +e

version=debugger

# 更新aio-scheduler
dockerfile_path=$(pwd)
cd ../..
dockerfile_exec_path=$(pwd)
cd ../../aio-scheduler/cmd/aio-scheduler-builder-debugger || exit
sh docker_build.sh

# 更新aio_sdk
cd "${dockerfile_exec_path}" || exit
rm -rf aio-scheduler aio_sdk.egg-info dist
python setup.py sdist
pip3 install dist/aio_sdk-latest.tar.gz

# 准备镜像中需要用到的文件
mkdir -p aio-scheduler/conf/
cp ../../aio-scheduler/cmd/aio-scheduler-builder-debugger/start.sh aio-scheduler/
cp ../../aio-scheduler/aio-scheduler aio-scheduler/
cp ../../aio-scheduler/conf/prod.ini aio-scheduler/conf/

# 构建镜像
docker build -t shi0090/aio-faas-python-basic:${version} -f "${dockerfile_path}/Dockerfile" .

rm -rf aio-scheduler aio_sdk.egg-info dist