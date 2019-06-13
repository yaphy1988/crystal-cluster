```
[
    {
			Id:””,//案件id（*）
            caseUuid:””,//案件唯一标识（*）
			caseName:””,//案件名称（*）
			caseNumber:””,//案件编号（*）
            createTime:””,//创建时间（*）
            modifyTime:””,//修改时间
			areaId:””,
			areaName:””,
            caseStatus:””,//案件状态（案件状态）（*）
            records:[
            {
	            recordId:””,//笔录id（*）
	            uuid:””,//笔录唯一标识（*）
	            templateId:””,//笔录模板Id（*）
	            asker:””,//讯问人（*）
	            persionId:””,//嫌疑人ID（*）
	            startTime:””,//笔录开始时间（*）
                endTime:””,//笔录结束时间
                status:””,//笔录状态（*）0:未开始 1  审讯中 2已结束
                recordType:””,//笔录类型（*）
                applyId:””,//预约id（*）
                remoteRoom:””,
                localRoom:””,
	
            }
            ……
            ]
    }
……
]
```

参数说明：

|序号	|参数名|	中文名称|	必选|	数据类型|	长度|	备注|
|-------|-----|--------|-------|-----------|--------|-------|
|1	|Id	|案件id	|是	|String	|16 |	
|2	|caseName	|案件名称	|是	|String	|100|	
|3	|caseUuid	|案件唯一标识	|是	|String	|100|	
|4	|caseNumber	|案件编号	|是	|String	|100|	
|5	|createTime	|创建时间	|是	|String	|100|	
|6	|modifyTime	|修改时间	|是	|String	|100|	
|7	|areaId	|区域Id	|是	|String	|100|	
|8	|recordId	|笔录Id	|是	|String	|100|	
|9	|uuid	|笔录唯一标识	|是	|String	|100|	
|10	|templateId	|笔录模板Id	|是	|String	|16	|
|11	|asker	|讯问人	|是	|String	|100	|
|12	|persionId	|嫌疑人id	|是	|String	|16	|
|13	|startTime	|笔录开始时间称	|是	|String	|100	|
|14	|endTime	|笔录结束时间	|是	|String	|100	|
|15	|status	|笔录状态	|是	|String	|16	|
|16	|recordType	|笔录类型	|是	|String	|16	|
|17	|applyId	|预约ID	|是	|String	|16	|
|18	|remoteRoom	|远程提讯室	|是	|String	|100	|
|19	|localRoom	|本地提讯室	|是	|String	|100	|

```mysql

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

```

文件下载后重命名存储路径：`/yjcloud/001/20190421/案号_询问室202_1_审讯人.wav`