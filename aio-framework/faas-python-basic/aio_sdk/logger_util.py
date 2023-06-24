import os
import sys
import logging
from logging.handlers import RotatingFileHandler

from aio_sdk import env_util

rotating_type = 'size'
max_bytes = 1024 * 1024 * 10
backup_count = 5
log_level = 'INFO'
log_format = '%(asctime)s %(levelname)s %(filename)s %(funcName)s %(lineno)d %(message)s'
log_date_format = '%Y-%m-%d %H:%M:%S'
workspace = env_util.get_workspace()
log_file = workspace + '/logs/{}.log'.format(os.path.basename(sys.argv[0]).split('.')[0])
log_dir = os.path.dirname(log_file)


def get_logger():
    if not os.path.exists(log_dir):
        os.makedirs(log_dir)

    # RotatingFileHandler
    log_file_handler = RotatingFileHandler(filename=log_file,
                                           mode='a',
                                           maxBytes=max_bytes,
                                           backupCount=backup_count,
                                           encoding='utf-8')
    log_file_handler.setLevel(log_level)
    log_file_handler.setFormatter(logging.Formatter(fmt=log_format, datefmt=log_date_format))

    # StreamHandler
    log_console_handler = logging.StreamHandler()
    log_console_handler.setLevel(log_level)
    log_console_handler.setFormatter(logging.Formatter(fmt=log_format, datefmt=log_date_format))

    logger = logging.getLogger()
    logger.setLevel(log_level)
    logger.addHandler(log_file_handler)
    logger.addHandler(log_console_handler)
    return logger
