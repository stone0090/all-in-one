import json
import traceback
from flask import request, Blueprint
from inject_code import main
from aio_sdk import logger_util

logger = logger_util.get_logger("platform")

api_blueprint = Blueprint('api', __name__, url_prefix='/aio/faas')


@api_blueprint.route('/invoke', methods=['POST'])
def invoke():
    response = {'success': False, 'message': 'unknown error'}
    try:
        response = {'success': True, 'data': main(request.json)}
    except Exception as e:
        msg = traceback.format_exc()
        logger.error(msg)
        if str(e):
            response = {'success': False, 'message': str(e)}
        else:
            response = {'success': False, 'message': msg}
    finally:
        return json.dumps(response, ensure_ascii=False)
