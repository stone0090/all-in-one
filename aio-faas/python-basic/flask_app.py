
import json

from flask import Flask, request

from aio_sdk import env_util, logger_util
from api import api_blueprint

logger = logger_util.get_logger("platform")
logger.info(f'work path is [{env_util.get_work_path()}]')

app = Flask(__name__)
app.register_blueprint(api_blueprint)
app.logger = logger


@app.route('/aio/faas/health/check')
def status():
    return 'success'


@app.before_request
def before_request():
    logger.info('input_data: ' + json.dumps(request.json))


@app.after_request
def after_request(response):
    logger.info('output_data: ' + response.get_data(as_text=True))
    return response


@app.teardown_request
def teardown_request(exception):
    logger.info('exception: ' + str(exception))


@app.errorhandler(404)
def page_not_found(error):
    logger.info('url_not_found: ' + request.url)
    return 'This url does not exist', 404


@app.errorhandler(500)
def internal_server_error(error):
    logger.info('internal_server_error, error: ' + str(error))
    return 'Internal server error', 500


if __name__ == '__main__':
    logger.info('flask app starting...')
    app.run(host='0.0.0.0', port=env_util.get_service_port(), debug=True, threaded=True)
