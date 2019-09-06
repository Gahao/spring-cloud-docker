# spring-cloud-docker
spring-cloud的docker版本

## 项目架构

| 模块名称 | 端口 | 服务名 |
|:-----|:---|:----|
|Eureka注册中心|8761|eureka-server|
|GateWay网关|6604|gateway-server|
|统一配置中心|9999|config-server|

## 注意事项
1. common模块在其他模块调用打包前需要install
2. docker环境需要开始远程API

## 系统完成情况，第一阶段
备注：因为暂无认证中心，所以用户验证沿用springboot版的session处理，存放在redis中实现共享
1. github 代码仓库----完成
2. mysql数据库----完成
3. redis缓存数据库----完成
4. eureka注册中心----完成
5. gateway网关----完成
6. 后台管理----未开始
7. 课程管理----未完成
8. 订单管理----未开始
9. 支付模块----未开始

    
## 系统架构 2019-09-05
![系统架构 2019-09-05](pic/System.jpg)
    
## 参考资料
[SpringCloud参考资源](https://blog.csdn.net/forezp/article/details/70148833)

[配置中心和自动刷新参考](https://artisan.blog.csdn.net/article/details/89117473#RefreshScope__actuatorbusrefresh_24)

[kafka数据总线](https://github.com/wurstmeister/kafka-docker)

[github项目](https://github.com/paascloud/paascloud-master)