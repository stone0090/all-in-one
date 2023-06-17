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
`config_value` varchar(4000) NOT NULL COMMENT '配置值',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_config_key` (`config_key`, `is_deleted`)
); -- ENGINE=InnoDB DEFAULT CHARSET=utf8