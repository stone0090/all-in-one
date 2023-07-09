FROM shi0090/aio-scheduler-python-basic:1.0.0

COPY /dist/aio_sdk-latest.tar.gz /tmp/aio_sdk-latest.tar.gz
COPY requirements.txt /tmp/requirements.txt
COPY *.py /home/admin/aio-faas/python-basic/
COPY cmd/aio-faas-python-basic/start.sh /home/admin/aio-faas/python-basic/start.sh

#USER root

RUN pip3 install /tmp/aio_sdk-latest.tar.gz  \
    && pip3 install -r /tmp/requirements.txt -i https://pypi.doubanio.com/simple --trusted-host pypi.doubanio.com --no-cache-dir  \
    && rm -rf /tmp/aio_sdk-latest.tar.gz \
    && rm -rf /tmp/requirements.txt

#RUN chown -R admin:admin /home/admin/ \
#    && chmod -R 755 /home/admin/
#
#USER admin

ENV AIO_LOG_DIR /home/admin/logs
ENV AIO_FAAS_PYTHON_WD /home/admin/aio-faas/python-basic

EXPOSE 6000 6001

CMD ["/bin/bash", "-c", "/home/admin/aio-scheduler/start.sh"]