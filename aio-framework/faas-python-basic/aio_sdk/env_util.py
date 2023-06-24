import os

mode = 'dev'  # prod or dev


def get_workspace():
    if mode == 'prod':
        return os.getenv("WORKSPACE")
    elif mode == 'dev':
        return os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
    else:
        raise Exception('mode error')
