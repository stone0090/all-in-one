import os
import sys
import logging
from logging.handlers import RotatingFileHandler

from aio_sdk import env_util

print(f'log dir is [{env_util.get_log_dir()}]')
rotating_type = 'size'
max_bytes = 1024 * 1024 * 10
backup_count = 5
log_level = 'INFO'
log_format = '%(asctime)s %(levelname)s %(filename)s %(funcName)s %(lineno)d %(message)s'
log_date_format = '%Y-%m-%d %H:%M:%S'
log_dir = env_util.get_log_dir()
log_file = log_dir + '/{}.log'.format(os.path.basename(sys.argv[0]).split('.')[0])


def get_logger(name=None):
    if not os.path.exists(log_dir):
        os.makedirs(log_dir)

    logger = logging.getLogger(name)
    if logger.hasHandlers():
        return logger

    logger.setLevel(log_level)

    # RotatingFileHandler
    log_file_handler = RotatingFileHandler(filename=log_file,
                                           mode='a',
                                           maxBytes=max_bytes,
                                           backupCount=backup_count,
                                           encoding='utf-8')
    log_file_handler.setLevel(log_level)
    log_file_handler.setFormatter(logging.Formatter(fmt=log_format, datefmt=log_date_format))
    logger.addHandler(log_file_handler)

    # StreamHandler
    log_console_handler = logging.StreamHandler()
    log_console_handler.setLevel(log_level)
    log_console_handler.setFormatter(logging.Formatter(fmt=log_format, datefmt=log_date_format))
    logger.addHandler(log_console_handler)

    return logger
