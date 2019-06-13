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

```