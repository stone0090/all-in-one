FROM shi0090/centos-7.9:python-3.6.8

ENV WORKSPACE /home/admin/
WORKDIR ${WORKSPACE}

COPY /dist/aio_sdk-latest.tar.gz /tmp/aio_sdk-latest.tar.gz
COPY requirements.txt /tmp/requirements.txt
COPY aio_core /home/admin/aio_core
COPY cmd/start_prod.sh /home/admin/start.sh

RUN pip3 install /tmp/aio_sdk-latest.tar.gz  \
    && pip3 install -r /tmp/requirements.txt -i https://pypi.doubanio.com/simple --trusted-host pypi.doubanio.com --no-cache-dir  \
    && rm -rf /tmp/aio_sdk-latest.tar.gz \
    && rm -rf /tmp/requirements.txt

EXPOSE 6001
CMD ["/bin/bash", "-c", "/home/admin/start.sh"]