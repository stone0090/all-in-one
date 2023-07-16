import sys
from flask import Flask, request

from api import api_blueprint
from aio_sdk import env_util, logger_util

logger = logger_util.get_logger("platform")

workdir = env_util.get_wd()
sys.path.insert(0, workdir + "/aio_sdk")
sys.path.insert(0, workdir)

logger.info('flask app start...')
logger.info('workdir: %s' % workdir)

app = Flask(__name__)
app.register_blueprint(api_blueprint)


@app.route('/aio/faas/health/check')
def status():
    return 'success'


@app.before_request
def before_request():
    request
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
