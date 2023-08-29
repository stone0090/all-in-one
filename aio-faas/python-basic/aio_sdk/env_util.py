import argparse
import os

parser = argparse.ArgumentParser(description='flask app with mode')
parser.add_argument('--mode', choices=['dev', 'prod'], default='dev', help='execution mode')
parser.add_argument('--port', default='6001', help='service port')
args = parser.parse_args()
print(f"正在启动faas服务，运行模式为：[{args.mode}]，服务端口为：[{args.port}]")


def get_service_port():
    if args.mode == 'prod':
        return str(args.port)
    else:
        return '6001'


def get_work_path():
    if args.mode == 'prod':
        return os.getenv('AIO_FAAS_WORK_PATH') + "-" + str(args.port)
    else:
        return os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


def get_log_path():
    if args.mode == 'prod':
        return os.getenv('AIO_FAAS_LOG_PATH') + '-' + str(args.port)
    else:
        return os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), 'logs')
