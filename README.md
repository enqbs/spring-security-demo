# spring-security-demo

------

## 项目介绍

Spring Boot + Spring Security + JWT 的 RBAC 权限系统 DEMO，前后端分离项目，纯后端没有前端代码只有 API，把 doc 文件夹中的 json 文件导入你的 postman 就能得到项目的所有 API。

无聊的时候写的但是做的也挺认真的。项目的初衷是提供参考、学习，能达到上线标准，但是前台不建议使用 Spring Security，比较适合后台管理系统。

编码格式严格按照《Google Java 编程风格指南》、《阿里巴巴 Java 开发手册》要求编写。

------

## 项目说明

RBAC 权限模型采用经典的“五表”结构设计，外加一张“测试数据表”提供系统测试。测试用户：

```shell
用户名		密码		角色
root		root		超级管理员
admin		admin		管理员
user		123456		普通用户
```

------

## 目录结构

```shell
spring-security-demo
├─zh-app	--	Application、Controller、classpath 配置文件
├─zh-common	--	封装的常用工具类、常量、异常处理
├─zh-system	--	业务代码、CRUD
└─zh-work	--	Spring Security 相关配置、处理
```

------

