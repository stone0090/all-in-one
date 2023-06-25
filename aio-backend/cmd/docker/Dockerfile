FROM shi0090/centos-7.9:java-8u301

ENV APP_NAME aio
ENV HEALTH_URL http://localhost:9090/aio/heartbeat/status

# 将应用启动脚本复制到镜像中
COPY cmd/java /home/admin/${APP_NAME}/bin/
RUN mv /home/admin/${APP_NAME}/bin/start.sh /start.sh

# copy 复制文件到容器地址 , 目标路径不存在时，会自动创建
COPY aio-web/target/${APP_NAME}.jar /home/admin/${APP_NAME}/target/${APP_NAME}.jar

EXPOSE 9090

RUN chmod -R a+x /start.sh
CMD ["/bin/bash", "-c", "/start.sh"]