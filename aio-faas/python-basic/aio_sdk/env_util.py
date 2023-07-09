import os

mode = 'dev'  # prod or dev


def get_wd():
    if mode == 'prod':
        return os.getenv("AIO_FAAS_PYTHON_WD")
    else:
        return os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))


def get_log_dir():
    if mode == 'prod':
        return os.getenv("AIO_LOG_DIR")
    else:
        return os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
