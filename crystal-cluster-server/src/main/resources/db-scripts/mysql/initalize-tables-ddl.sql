CREATE TABLE `demo` (
  `id` int(11) NOT NULL,
  `name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `memo` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*==============================================================*/
/* Table: persistent_logins                                     */
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


/*==============================================================*/
/* Table: t_keda_caseinfo                                       */
/*==============================================================*/
create table t_keda_caseinfo
(
   case_id              varchar(128) not null comment '案件ID',
   case_name            varchar(256) comment '案件名称',
   case_uuid            varchar(128) comment '案件唯一标识',
   case_number          varchar(128) comment '案件编号',
   create_time          datetime comment '创建时间',
   modify_time          datetime comment '修改时间',
   area_id              varchar(128) comment '区域ID',
   area_name            varchar(128) comment '区域名称',
   case_status          varchar(16) comment '案件状态',
   job_status           varchar(16) comment '任务处理状态：pending:待处理，processing:处理中，complete:已完成',
   job_time             datetime comment '任务处理更新时间',
   primary key (case_id)
);

alter table t_keda_caseinfo comment 't_keda_caseinfo案件信息表';


/*==============================================================*/
/* Table: t_keda_record                                         */
/*==============================================================*/
create table t_keda_record
(
   record_id            varchar(128) not null comment '笔录ID',
   record_uuid          varchar(128) comment '笔录唯一标识',
   case_id              varchar(128) not null comment '归属案件ID',
   template_id          varchar(16) comment '笔录模板ID',
   asker                varchar(128) comment '讯问人',
   persion_id           varchar(16) comment '嫌疑人ID',
   start_time           datetime comment '笔录开始时间',
   end_time             datetime comment '笔录结束时间',
   record_status        varchar(16) comment '笔录状态',
   record_type          varchar(16) comment '笔录类型',
   apply_id             varchar(16) comment '预约ID',
   remote_room          varchar(128) comment '远程提讯室',
   local_room           varchar(128) comment '本地提讯室',
   video_url            varchar(128) comment '视频下载地址',
   job_status           varchar(16) comment '任务处理状态：pending:待处理，processing:处理中，complete:已完成',
   job_time             datetime comment '任务处理更新时间',
   primary key (record_id)
);

alter table t_keda_record comment 't_keda_record案件笔录信息表';

create index idx_keda_record_caseid on t_keda_record
(
   case_id
);

/*==============================================================*/
/* Table: t_keda_video_storage                                  */
/*==============================================================*/
create table t_keda_video_storage
(
   video_storage_id     bigint not null comment '视频存储ID',
   storage_path         varchar(256) comment '远程存储路径',
   storage_filename     varchar(128) comment '存储文件名',
   video_url            varchar(128) comment '科达视频下载地址',
   record_id            varchar(128) comment '笔录ID',
   case_id              varchar(128) comment '案件ID',
   case_name            varchar(128) comment '案件名称',
   asker                varchar(128) comment '讯问人',
   room_name            varchar(128) comment '提讯室',
   persion_id           varchar(128) comment '嫌疑人ID',
   create_time          datetime comment '创建时间',
   primary key (video_storage_id)
);

alter table t_keda_video_storage comment 't_keda_video_storage笔录视频存储结果表';

create index idx_keda_video_caseid on t_keda_video_storage
(
   case_id
);

create index idx_keda_video_recordid on t_keda_video_storage
(
   record_id
);
