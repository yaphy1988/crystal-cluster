### 包说明
* ```constant```包： 接口定义和DTO中会用到的常量定义
* ```dto```包： DataTransfer Object数据传输对象包，用于定义接口调用所需的对象
* ```interfaces```包： 接口包用于定义开放给其他模块或子系统调用的接口服务

如果当前模块或者子系统需要对外提供服务，则需要```*-api```模块将接口定义独立出来。无论对外提供的是dubbo服务还是rest服务，都要定义一个api模块供外部系统面向接口进行对接，当前模块也要依据接口定义实现具体业务逻辑。

如果当前系统是独立提供一个web服务（页面采用服务端模板，包含html|js|css|image等界面元素），则可不提供```*-api```模块，直接在```*-service```模块实现业务逻辑即可。
