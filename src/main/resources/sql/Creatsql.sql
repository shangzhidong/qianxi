CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) NOT NULL COMMENT '用户编码',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_pass` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_login_name` varchar(255) DEFAULT NULL COMMENT '用户登录名称',
  `user_alipay_id` varchar(255) DEFAULT NULL COMMENT '支付宝ID',
  `user_wecha_id` varchar(255) DEFAULT NULL COMMENT '用户微信ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creat_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';


