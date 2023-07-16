import json
import traceback
from flask import request, Blueprint
from inject_code import main
from aio_sdk import logger_util

logger = logger_util.get_logger("platform")

api_blueprint = Blueprint('api', __name__, url_prefix='/aio/faas')


@api_blueprint.route('/invoke', methods=['POST'])
def invoke():
    response = ''
    try:
        logger.info('before invoke...')
        input_data = request.json
        logger.info('input_data: ' + json.dumps(input_data))
        # dynamic inject code start #
        result = main(input_data)
        # dynamic inject code end #
        response = {'success': True, 'data': result}
        logger.info('output_data: ' + json.dumps(response))
        logger.info('after invoke...')
    except Exception as e:
        msg = traceback.format_exc()
        logger.error(msg)
        if str(e):
            response = {'success': False, 'message': str(e)}
        else:
            response = {'success': False, 'message': msg}
    finally:
        logger.info('teardown invoke...')
        return json.dumps(response, ensure_ascii=False)
