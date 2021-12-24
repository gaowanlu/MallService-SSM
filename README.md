<div align="center">  

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

</div> 


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <h2 align="center">MallService-SSM</h2>

  <p align="center">
    在线商城后端服务 
    <br />
    <a href="https://md.yuuza.net/8db-waSeSdW1NnyYmYZeYw"><strong>DOC »</strong></a>
    <br />
    <br />
  </p>
</div> 

## 项目简介  

项目开始是为完成大学课程作业，项目也定会存在许多的问题。  

我们欢迎大家能够指正。 欢迎Fork、 PullRequest🎉


## 项目目录结构

```
├─src
    ├─main
    │  ├─java
    │  │  └─site
    │  │      └─linkway
    │  │          ├─core
    │  │          │  ├─controller（控制器）
    │  │          │  ├─entity  （实体类）
    │  │          │  │  ├─bo  (business object 业务对象) 
    │  │          │  │  ├─po  （persistant object 持久对象）  
    │  │          │  │  └─vo  （value object值对象）
    │  │          │  ├─filter  （拦截器）
    │  │          │  ├─mapper  （mapper类）
    │  │          │  └─service （服务类）
    │  │          └─utils  （常用工具类）
    │  ├─resources
    │  │  ├─mapper（mapper 相关xml文件） 
    │  │  ├─mybean  （自定义bean）   
    │  │  ├─applicationContext.xml  （Spring bean 入口）  
    │  │  ├─database.properties  （数据库信息配置）    
    │  │  ├─log4j.properties  （Log4j配置）    
    │  │  ├─mybatis-config.xml  （mybatis配置）   
    │  │  ├─spring-mvc.xml  （控制层配置）  
    │  │  ├─spring-mybatis.xml  （spring mybatis整合配置）  
    │  │  ├─spring-service.xml  （服务层配置）    
    │  │  └─sql   (数据库初始化SQL)  
    │  └─webapp  （前端项目） 
    │      └─WEB-INF 
```

## 相关技术
Spring、SpringMVC、Mybatis 、MySQL


## API 文档
[查看详情](https://md.yuuza.net/8db-waSeSdW1NnyYmYZeYw)

## Docker 部署

[![Docker][docker-shield]][docker-url]

```bash
docker run -it -p 1234:8080 \
  -e CATALINA_OPTS="-Djdbc.url=jdbc:mysql://__DB_SERVER__:3306/__DB_NAME__ -Djdbc.username=__USERNAME__ -Djdbc.password=__PASSWORD__" \
  --name mallservice \
  gaowanlu/mallservice
```

其中，`1234` 为监听端口号，`__DB_SERVER__` / `__DB_NAME__` / `__USERNAME__` / `__PASSWORD__` 为数据库连接配置。

## 版本

|  version   |  date  | changed content |  
|  ----  | ----  |----|  
|    |   |   |  

## 联系我们  
Email heizuboriyo@gmail.com   
Address 桂林电子科技大学 

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/gaowanlu/MallService-SSM.svg?style=for-the-badge
[contributors-url]: https://github.com/gaowanlu/MallService-SSM/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/gaowanlu/MallService-SSM.svg?style=for-the-badge
[forks-url]: https://github.com/gaowanlu/MallService-SSM/network/members
[stars-shield]: https://img.shields.io/github/stars/gaowanlu/MallService-SSM.svg?style=for-the-badge
[stars-url]: https://github.com/gaowanlu/MallService-SSM/stargazers
[issues-shield]: https://img.shields.io/github/issues/gaowanlu/MallService-SSM.svg?style=for-the-badge
[issues-url]: https://github.com/gaowanlu/MallService-SSM/issues
[license-shield]: https://img.shields.io/github/license/gaowanlu/MallService-SSM.svg?style=for-the-badge
[license-url]: https://github.com/gaowanlu/MallService-SSM/blob/master/LICENSE.txt
[docker-shield]: https://img.shields.io/docker/image-size/gaowanlu/mallservice/latest?style=for-the-badge
[docker-url]: https://hub.docker.com/r/gaowanlu/mallservice
