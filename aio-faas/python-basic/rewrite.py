from asyncio import Lock

from aio_sdk import env_util, logger_util

logger = logger_util.get_logger()
workspace = env_util.get_wd()


def inject_code(code):
    lock = Lock()
    lock.acquire()
    try:
        with open(workspace + '/inject_code.py', 'w', encoding='utf-8') as f:
            f.write(code)
            logger.info('inject code success')
    finally:
        lock.release()
