FROM shi0090/centos-7.9:go-1.18 as builder

WORKDIR /home/admin

COPY client /home/admin/client
COPY common /home/admin/common
COPY controller /home/admin/controller
COPY dal /home/admin/dal
COPY service /home/admin/service
COPY vendor /home/admin/vendor
COPY go.mod go.sum main.go /home/admin/

ENV PATH=$PATH:/usr/local/go/bin

RUN go mod vendor \
    && CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o aio-scheduler main.go

FROM shi0090/centos-7.9:python-3.6.8

WORKDIR /home/admin/

COPY --from=builder /home/admin/aio-scheduler /home/admin/aio-scheduler/
COPY /conf /home/admin/aio-scheduler/conf/
COPY /cmd/aio-scheduler-python-basic/start.sh /home/admin/aio-scheduler/

ENV AIO_SCHEDULER_WD /home/admin/aio-scheduler

EXPOSE 6000

CMD ["/bin/bash", "-c", "/home/admin/aio-scheduler/start.sh"]