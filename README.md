# common-core
基于maven的spring4.3+mybatis3.4+swagger2.6的后台整合，方便快速进行后台接口开发。


### 已完成功能
- 增加权限统一拦截注解`@Authentication`
- 增加方法性能统计注解`@printRunTime`
- 实现了简单的权限系统

### 安装步骤
- clone本项目，创建下面的数据库和表
- 使用IDE导入本项目，使用maven方式导入项目
- 配置`jdbc.properties`下面的数据库相关信息（如果你需要使用mybitis逆向插件，也需要配置`generatorConfig.xml`这个文件中的数据库信息）
- 使用maven编译后，配置tomcat并部署
- 启动tomcat,访问以下链接测试接口；
- 访问`http://localhost:8080/docs/index.html` 查看swagger生成的Api文档信息

### 建表语句
```sql
create database playground;

use playground;

create table user (
   id int unsigned  primary key AUTO_INCREMENT comment '用户表',
   username varchar(100),
   password varchar(100),
   idcard varchar(100) comment '身份证',
   email varchar(100),
   name varchar(100),
   sex int comment '性别0女2男',
   phone varchar(100),
   enable int comment '是否启用0false，1true',
   isdel int comment '是否删除0false, 1true'
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 create table role (
   id int unsigned  primary key AUTO_INCREMENT comment '角色表',
   name varchar(100),
   enable int,
   isdel int
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create table permission (
   id int unsigned  primary key AUTO_INCREMENT comment '权限表',
   name varchar(100),
   isdel int
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 create table role_permission_map (
   id int unsigned  primary key AUTO_INCREMENT comment '角色-权限映射表',
   role_id int unsigned,
   permission_id int unsigned,
   isdel int
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 create table user_role_map (
   id int unsigned  primary key AUTO_INCREMENT comment '用户-角色映射表',
   user_id int unsigned,
   role_id int unsigned,
   isdel int
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```


### 相关环境
- OS Microsoft Windows 10 Pro
- Java 8
- springMVC 4.3
- Mybitis 3.4
- Mysql 5.5.50
- Restful interface
- Maven 3.5.3
- Git 2.14.1
- Swagger 2.6.1


### 注意事项
- 下载后请修改`pom.xml`里面的,这里需要引入你的tomcat下的servlet.api.jar
```xml 
<!-- 添加tomcat/bin下servlet-api.jar 注意你tomcat下这个jar包版本-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>2.5</version>
      <scope>provided</scope>
    </dependency>
```
- 使用mybaitis-generator插件生成dao层时请先删除原来的文件,不然生的的内容会追加到源文件中,出现代码重复
- 下载后可能需要修改/webapp/swagger-ui/index.html文件中的url地址.
