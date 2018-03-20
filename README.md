# common-core
基于maven的spring4.3+mybatis3.4的后台整合，方便快速进行后台接口开发。

### 安装步骤
- clone本项目，创建下面的数据库和表
- 使用IDE导入本项目，使用maven方式导入项目
- 配置`jdbc.properties`下面的数据库相关信息（如果你需要使用mybitis逆向插件，也需要配置`generatorConfig.xml`这个文件中的数据库信息）
- 配置tomcat， 使用maven编译后
- 启动项目,访问http://10.1.1.175:8080/v1/user/list?limit=1测试接口

### 建表语句
```sql
 create database playground;
 
 create table user (
   id int unsigned  primary key AUTO_INCREMENT,
   username varchar(100),
   password varchar(100),
   name varchar(100),
   address varchar(500),
   sex tinyint,
   phone varchar(100)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
