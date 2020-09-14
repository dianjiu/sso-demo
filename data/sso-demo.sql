-- 用户表
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `crate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作';

-- 用户站点表
CREATE TABLE `t_user_web` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `web_name` varchar(255) DEFAULT NULL COMMENT '站点名字',
  `web_desc` varchar(255) DEFAULT NULL COMMENT '站点描述',
  `web_ip` varchar(64) DEFAULT NULL COMMENT '站点IP',
  `web_url` varchar(64) DEFAULT NULL COMMENT '站点域名',
  `web_type` varchar(64) DEFAULT NULL COMMENT '站点类型',
  `web_image` varchar(64) DEFAULT NULL COMMENT '站点头像',
  `crate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点操作';