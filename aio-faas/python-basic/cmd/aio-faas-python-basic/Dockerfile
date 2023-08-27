FROM shi0090/centos-7.9:python-3.6.8

ENV AIO_SCHEDULER_WORK_DIR /home/admin/aio-scheduler
ENV AIO_FAAS_PYTHON_WORK_DIR /home/admin/aio-faas/python-basic
ENV AIO_FAAS_PYTHON_LOG_DIR /home/admin/logs/python-basic

COPY requirements.txt /tmp/requirements.txt
COPY dist/aio_sdk-latest.tar.gz /tmp/aio_sdk-latest.tar.gz
COPY aio-scheduler/start.sh ${AIO_SCHEDULER_WORK_DIR}/
COPY aio-scheduler/aio-scheduler ${AIO_SCHEDULER_WORK_DIR}/
COPY aio-scheduler/conf/prod.ini ${AIO_SCHEDULER_WORK_DIR}/conf/
COPY *.py ${AIO_FAAS_PYTHON_WORK_DIR}/
COPY *.json ${AIO_FAAS_PYTHON_WORK_DIR}/
COPY cmd/aio-faas-python-basic/start.sh ${AIO_FAAS_PYTHON_WORK_DIR}/start.sh

RUN pip3 install /tmp/aio_sdk-latest.tar.gz  \
    && pip3 install -r /tmp/requirements.txt -i https://mirrors.aliyun.com/pypi/simple/ --trusted-host https://mirrors.aliyun.com --no-cache-dir  \
    && rm -rf /tmp/aio_sdk-latest.tar.gz \
    && rm -rf /tmp/requirements.txt

CMD ["/bin/bash", "-c", "${AIO_SCHEDULER_WORK_DIR}/start.sh"]