CREATE TABLE `demo` (
  `id` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `memo` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*==============================================================*/
/* Table: persistent_logins                                      */
/*==============================================================*/
create table persistent_logins (
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);

/*==============================================================*/
/* Table: t_user_extension                                      */
/*==============================================================*/
create table t_user_extension
(
   user_id              bigint not null comment '用户ID',
   industry             varchar(256) comment '行业',
   primary key (user_id)
);

alter table t_user_extension comment 't_user_extension用户扩展信息表';
