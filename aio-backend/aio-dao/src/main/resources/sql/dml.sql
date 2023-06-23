MERGE INTO `aio_user`(id, username, password, nickname) VALUES ('1', 'stone', '123456', '小方方'); -- REPLACE INTO
MERGE INTO `aio_user`(id, username, password, nickname) VALUES ('2', 'sophie', '123456', '小黄黄'); -- REPLACE INTO
MERGE INTO `aio_user`(id, username, password, nickname) VALUES ('3', 'tommy', '123456', '小黑黑'); -- REPLACE INTO
MERGE INTO `aio_role`(id, role_code, role_name) VALUES ('1', 'admin', '管理员'); -- REPLACE INTO
MERGE INTO `aio_role`(id, role_code, role_name) VALUES ('2', 'visitor', '游客'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('1', 'insert_user', '新增用户', '/user/add'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('2', 'delete_user', '删除用户', '/user/remove'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('3', 'update_user', '更新用户', '/user/edit'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('4', 'select_one_user', '查询单个用户', '/user/get'); -- REPLACE INTO
MERGE INTO `aio_permission`(id, permission_code, permission_name, permission_url) VALUES ('5', 'select_all_user', '查询全部用户', '/user/list'); -- REPLACE INTO
MERGE INTO `aio_user_role_relation`(id, username, role_code) VALUES ('1', 'stone', 'admin'); -- REPLACE INTO
MERGE INTO `aio_user_role_relation`(id, username, role_code) VALUES ('2', 'sophie', 'admin'); -- REPLACE INTO
MERGE INTO `aio_user_role_relation`(id, username, role_code) VALUES ('3', 'tommy', 'visitor'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('1', 'admin', 'insert_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('2', 'admin', 'delete_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('3', 'admin', 'update_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('4', 'admin', 'select_one_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('5', 'admin', 'select_all_user'); -- REPLACE INTO
MERGE INTO `aio_role_permission_relation`(id, role_code, permission_code) VALUES ('6', 'visitor', 'select_all_user'); -- REPLACE INTO
MERGE INTO `aio_system_config`(id, config_key, config_value) VALUES ('1', 'core_operator_algo_code_template', '	#import start

#customer code start
def main(input_data, context)
    a = input_data["a"]
    b = input_data["b"]
    return {
        "sum": a + b
    }
#customer code end'); -- REPLACE INTO
MERGE INTO `aio_system_config`(id, config_key, config_value) VALUES ('2', 'core_operator_input_param_template', '{
    "a": {
        "name": "参数a",
        "type": "int/double/string/boolean",
        "required": true
    },
    "b": {
        "name": "参数b",
        "type": "int/double/string/boolean",
        "required": true
    },
}'); -- REPLACE INTO
MERGE INTO `aio_system_config`(id, config_key, config_value) VALUES ('3', 'core_operator_output_param_template', '{
    "sum": {
        "name": "两数之和",
        "type": "int/double/string/boolean"
    }
}'); -- REPLACE INTO