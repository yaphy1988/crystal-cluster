### 包说明
* ```common```包：common通用包，用于当前模块的一些常量定义，业务相关工具类等通用服务；
* ```config```包：java config类，用于当前模块一些bean和服务配置；
* ```web```包：提供web服务，Controller都写在这个包里，用于提供基于SpringMVC的web服务，可选；
* ```rest```包：提供restful风格的api接口包，这里建议使用RestController，也可使用Jersey，可选；
* ```dubbo```包：提供dubbo服务，实现xxx-api模块中的接口，可选；
* ```service```包：原子服务层，主要的业务实现写在这里，事务控制在这一层实现；
* ```dao```包：数据访问dao层，对数据库对象增删改查操作，这一层通常使用mybatis框架实现。

包的分层关系如下：
1. 最上层：web | rest | dubbo
2. 服务层：service
3. DAO层：dao
4. 通用层：config | common，不属于某一层，可以是贯穿所有层的配置类