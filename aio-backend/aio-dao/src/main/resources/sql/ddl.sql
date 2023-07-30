CREATE TABLE IF NOT EXISTS `aio_user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`username` varchar(50) NOT NULL COMMENT '用户名称',
`password` varchar(50) NOT NULL COMMENT '登陆密码',
`salt` varchar(50) DEFAULT NULL COMMENT '密码加盐',
`nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
`avatar` varchar(255) DEFAULT NULL COMMENT '头像',
`resume` varchar(255) DEFAULT NULL COMMENT '简介',
`phone` varchar(50) DEFAULT NULL COMMENT '电话',
`email` varchar(50) DEFAULT NULL COMMENT '邮件',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_username` (`username`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE IF NOT EXISTS `aio_role` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`role_code` varchar(50) NOT NULL COMMENT '角色标识',
`role_name` varchar(50) NOT NULL COMMENT '角色名称',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_role` (`role_code`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE IF NOT EXISTS `aio_permission` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`permission_code` varchar(50) NOT NULL COMMENT '权限标识',
`permission_name` varchar(50) NOT NULL COMMENT '权限名称',
`permission_url` varchar(50) NOT NULL COMMENT '权限路径',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_permission` (`permission_code`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE IF NOT EXISTS `aio_user_role_relation` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`username` varchar(50) NOT NULL COMMENT '用户名称',
`role_code` varchar(50) NOT NULL COMMENT '角色标识',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_username_role` (`username`, `role_code`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE IF NOT EXISTS `aio_role_permission_relation` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`role_code` varchar(50) NOT NULL COMMENT '角色标识',
`permission_code` varchar(50) NOT NULL COMMENT '权限名称',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_role_permission` (`role_code`, `permission_code`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE IF NOT EXISTS `aio_system_config` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`config_key` varchar(50) NOT NULL COMMENT '配置项',
`config_value` varchar(20000) NOT NULL COMMENT '配置值',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_system_config` (`config_key`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

-- 算子
CREATE TABLE IF NOT EXISTS `aio_operator` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`op_code` varchar(50) NOT NULL COMMENT '算子标识',
`op_name` varchar(50) NOT NULL COMMENT '算子名称',
`op_status` varchar(50) NOT NULL COMMENT '算子状态',
`algo_language` varchar(50) NOT NULL COMMENT '编程语言',
`algo_code` varchar(20000) NOT NULL COMMENT '算子代码',
`algo_path` varchar(1000) NOT NULL COMMENT '算子地址',
`input_param` varchar(4000) NOT NULL COMMENT '输入参数',
`output_param` varchar(4000) NOT NULL COMMENT '输出参数',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_operator` (`op_code`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

-- 画布（算子编排）
CREATE TABLE IF NOT EXISTS `aio_experiment` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`ex_name` varchar(50) NOT NULL COMMENT '画布名称',
`ex_desc` varchar(200) NOT NULL COMMENT '画布描述',
`ex_status` varchar(50) NOT NULL COMMENT '画布状态',
`node_dag` varchar(20000) NOT NULL COMMENT '节点dag',
`publish_type` varchar(50) NOT NULL COMMENT '部署类型',
`publish_config` varchar(1000) NOT NULL COMMENT '部署配置',
PRIMARY KEY (`id`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

-- 算子实例
CREATE TABLE IF NOT EXISTS `aio_node_instance` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`ex_id` int(11) NOT NULL COMMENT '画布ID',
`op_id` int(11) NOT NULL COMMENT '算子ID',
`op_code` varchar(50) NOT NULL COMMENT '算子标识',
`node_name` varchar(50) NOT NULL COMMENT '节点名称',
`input_mapping` varchar(4000) NOT NULL COMMENT '入参映射',
`node_config` varchar(1000) NOT NULL COMMENT '节点配置',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_node_instance` (`ex_id`,`op_code`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

-- api服务
CREATE TABLE IF NOT EXISTS `aio_api` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间', -- ON UPDATE CURRENT_TIMESTAMP
`is_deleted` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记',
`api_uuid` varchar(50) NOT NULL COMMENT 'api通用唯一标识',
`api_name` varchar(50) NOT NULL COMMENT 'api名称',
`api_type` varchar(50) NOT NULL COMMENT 'api类型',
`api_status` varchar(50) NOT NULL COMMENT 'api状态',
`type_id` int(11) NOT NULL COMMENT '类型id',
`api_url` varchar(1000) NOT NULL COMMENT 'api地址',
`input_param` varchar(4000) NOT NULL COMMENT '输入参数',
`output_param` varchar(4000) NOT NULL COMMENT '输出参数',
`invoke_type` varchar(20) NOT NULL COMMENT '调用类型',
`callback_url` varchar(1000) NOT NULL COMMENT '回调地址',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_api` (`api_uuid`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8

