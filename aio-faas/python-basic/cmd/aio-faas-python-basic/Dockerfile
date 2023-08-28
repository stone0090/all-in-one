FROM shi0090/centos-7.9:python-3.6.8

ENV AIO_SCHEDULER_WORK_PATH /home/admin/aio-scheduler
ENV AIO_SCHEDULER_LOG_PATH /home/admin/logs
ENV AIO_FAAS_WORK_PATH /home/admin/aio-faas/python-basic
ENV AIO_FAAS_LOG_PATH /home/admin/logs/python-basic

COPY requirements.txt /tmp/requirements.txt
COPY dist/aio_sdk-latest.tar.gz /tmp/aio_sdk-latest.tar.gz
COPY aio-scheduler/start.sh ${AIO_SCHEDULER_WORK_PATH}/
COPY aio-scheduler/aio-scheduler ${AIO_SCHEDULER_WORK_PATH}/
COPY aio-scheduler/conf/prod.ini ${AIO_SCHEDULER_WORK_PATH}/conf/
COPY *.py ${AIO_FAAS_WORK_PATH}/
COPY *.json ${AIO_FAAS_WORK_PATH}/
COPY cmd/aio-faas-python-basic/start.sh ${AIO_FAAS_WORK_PATH}/start.sh

RUN pip3 install /tmp/aio_sdk-latest.tar.gz  \
    && pip3 install -r /tmp/requirements.txt -i https://mirrors.aliyun.com/pypi/simple/ --trusted-host https://mirrors.aliyun.com --no-cache-dir  \
    && rm -rf /tmp/aio_sdk-latest.tar.gz \
    && rm -rf /tmp/requirements.txt

EXPOSE 6000
EXPOSE 6001

CMD ["/bin/bash", "-c", "${AIO_SCHEDULER_WORK_PATH}/start.sh"]