import argparse
import os
import json

parser = argparse.ArgumentParser(description='flask app with mode')
parser.add_argument('--mode', choices=['dev', 'prod'], default='dev', help='execution mode')
args = parser.parse_args()
print(f"running in [{args.mode}] mode")

with open(os.path.join(os.getenv('AIO_FAAS_PYTHON_WORK_DIR'), 'config.json'), 'r') as config_file:
    config = json.load(config_file)


def get_flask_port():
    return config['flask_port']


def get_work_dir():
    if args.mode == 'prod':
        return os.getenv('AIO_FAAS_PYTHON_WORK_DIR') + "-" + str(get_flask_port())
    else:
        return os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


def get_log_dir():
    if args.mode == 'prod':
        return os.getenv('AIO_FAAS_PYTHON_LOG_DIR') + '-' + str(get_flask_port())
    else:
        return os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), 'logs')

