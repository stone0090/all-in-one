### 快速开始
1、下载依赖 
```bash
go mod vendor
```

2、本地启动
```bash
go run main.go -mode prod
```
```bash
go run main.go -mode dev
```

3、应用打包 
```bash
go build -o aio-scheduler main.go
```
```bash
CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o aio-scheduler main.go
```

4、程序执行
```bash
aio-scheduler -mode prod
```
```bash
aio-scheduler -mode dev
```