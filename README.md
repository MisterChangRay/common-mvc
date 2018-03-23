# common-core
基于maven的spring4.3+mybatis3.4的后台整合，方便快速进行后台接口开发。

### 新功能
- 增加权限统一拦截注解`@Authentication`
- 实现了简单的权限系统

### 安装步骤
- clone本项目，创建下面的数据库和表
- 使用IDE导入本项目，使用maven方式导入项目
- 配置`jdbc.properties`下面的数据库相关信息（如果你需要使用mybitis逆向插件，也需要配置`generatorConfig.xml`这个文件中的数据库信息）
- 使用maven编译后，配置tomcat并部署
- 启动tomcat,访问以下链接测试接口；
- `get http://localhost:8080/v1/user/list?limit=1` 查询用户列表
- `post http://localhost:8080/v1/user/add` 新增用户

### 建表语句
```sql
 create database playground;
 
 create table user (
   id int unsigned  primary key AUTO_INCREMENT,
   username varchar(100),
   password varchar(100),
   idcard varchar(100),
   email varchar(100),
   name varchar(100),
   sex tinyint, --性别0女2男
   phone varchar(100),
   enable tinyint, --是否启用0false，1true
   isdel tinyint --是否删除0false, 1true
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 create table role (
   id int unsigned  primary key AUTO_INCREMENT,
   name varchar(100),
   enable tinyint,
   isdel tinyint
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  create table permission (
   id int unsigned  primary key AUTO_INCREMENT,
   name varchar(100),
   isdeal tinyint
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 create table role_permission_map (
   id int unsigned  primary key AUTO_INCREMENT,
   role_id int unsigned,
   permission_id int unsigned,
   isdel tinyint
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 create table user_role_map (
   id int unsigned  primary key AUTO_INCREMENT,
   user_id int unsigned,
   role_id int unsigned,
   isdel tinyint
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### 相关环境
- OS Microsoft Windows 10 Pro
- mysql 5.5.50
- spring 4.3
- mybitis 3.4
