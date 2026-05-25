# 尚品甄选 - 企业级B2C电商平台


[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2022.0.4-orange.svg)](https://spring.io/projects/spring-cloud)
[![MySQL](https://img.shields.io/badge/MySQL-8.0.36-blue.svg)](https://www.mysql.com/)
[![Redis](https://img.shields.io/badge/Redis-7.2.4-red.svg)](https://redis.io/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📖 项目概述
**尚品甄选** 是一款基于微服务架构、前后端分离的企业级B2C电商系统，对标主流电商业务模型，覆盖用户端、商家端、平台管理端全场景业务。

项目解决传统单体电商系统并发低、扩展性差、业务耦合严重等问题，整合商品、用户、订单、支付、营销、库存、权限等全套电商核心能力，适合作为毕业设计、企业项目原型、二次开发商用模板。

## ✨ 核心功能模块

### 👥 用户服务模块
- 基于JWT实现多端统一单点登录、注册、权限校验
- 会员等级、积分、成长值、用户权益体系
- 收货地址管理、个人信息、账户安全设置
- 订单记录、商品收藏、浏览记录管理

### 🛒 商品服务模块
- 商品分类、品牌管理、商品规格、SKU管理
- 商品上下架、审核、详情渲染、参数配置
- Elasticsearch 全文检索、条件筛选、热门推荐
- 商品图文、详情、库存实时展示

### 📦 订单与购物车模块
- 购物车增删改查、批量结算、价格实时计算
- 完整订单生命周期：下单、支付、发货、物流、收货、评价
- 订单状态管理、取消订单、退款售后流程
- 防超卖、分布式库存扣减机制

### 🎁 营销活动模块
- 满减、折扣、优惠券发放与核销
- 秒杀、限时活动、积分兑换、活动预热
- 活动规则配置、活动数据统计

### 🛡️ 后台管理模块
- 权限角色、菜单管理、用户管理
- 店铺入驻、资质审核、店铺管理
- 数据大屏、订单统计、用户统计、销量统计
- 系统日志、操作记录、异常监控

## 🛠️ 技术栈

### 后端技术
- JDK 17
- Spring Boot 3.2.5
- Spring Cloud / Spring Cloud Alibaba
- MyBatis-Plus 3.5.3.1
- MySQL 8.0
- Redis 7.2
- Elasticsearch 8.x
- RabbitMQ 消息队列
- MinIO 对象存储
- Sentinel 熔断限流
- Seata 分布式事务
- Nacos 注册配置中心

### 前端技术
- Vue3
- Vite
- Element Plus / Vant
- Pinia 状态管理
- Vue Router
- Axios
- ECharts 数据可视化

## 🏗️ 系统架构
1. **网关层**：Spring Cloud Gateway 统一入口、路由转发、鉴权、限流
2. **注册配置中心**：Nacos 服务注册、发现、动态配置
3. **认证授权中心**：JWT + Spring Security 统一认证授权
4. **微服务业务层**：用户服务、商品服务、订单服务、购物车服务、营销服务、库存服务、支付服务
5. **中间件层**：Redis缓存、MQ异步解耦、ES检索、MinIO文件存储
6. **数据层**：MySQL主从、Redis集群、ES集群

## 🚀 快速启动

### 环境依赖
- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Redis 7.0+
- Nacos 2.2+
- Node.js 16+
