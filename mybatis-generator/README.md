## mybatis-generate使用说明
### 1.复制`src/main/resources/generatorConfig.sample.xml`并重命名为`generatorConfig.xml`
定义数据库连接方式。

### 2.复制`src/main/resources/init.sample.properties`并重命名为`init.properties`
定义生成的bean所在包路径和磁盘存储地址

### 3.修改`src/main/resources/generatorConfig.xml`添加需要生成的表的定义
需要生成的表名和对应bean名称定义