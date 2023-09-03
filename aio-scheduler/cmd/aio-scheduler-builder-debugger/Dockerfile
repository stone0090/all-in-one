FROM shi0090/centos-7.9:python3.6.8-go1.18-debugger as builder

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
    && CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -gcflags "all=-N -l" -o aio-scheduler main.go