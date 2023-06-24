import sys
from flask import Flask

from aio_sdk import env_util, logger_util

logger = logger_util.get_logger()

workspace = env_util.get_workspace()
sys.path.insert(0, workspace + "/aio_sdk")
sys.path.insert(0, workspace + "/core")
sys.path.insert(0, workspace)

app = Flask(__name__)

logger.info('flask app start...')
logger.info('workspace: %s' % workspace)


@app.route('/')
def index():
    return 'hello world!'


@app.route('/status')
def status():
    return 'success'


@app.before_request
def before_request():
    logger.info('before_request...')


@app.after_request
def after_request(response):
    logger.info('after_request...')
    return response


@app.teardown_request
def teardown_request(exception):
    logger.info('teardown_request...')


@app.errorhandler(404)
def page_not_found(error):
    logger.info('page_not_found...')
    return 'This page does not exist', 404


@app.errorhandler(500)
def internal_server_error(error):
    logger.info('internal_server_error...')
    return 'Internal server error', 500


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=6001, debug=True, threaded=True)
