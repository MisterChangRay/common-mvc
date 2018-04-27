/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/4/27 10:59:50                           */
/*==============================================================*/


drop table if exists constant;

drop table if exists operation_log;

drop table if exists permission;

drop table if exists role;

drop table if exists role_permission_map;

drop table if exists user;

drop table if exists user_role_map;

/*==============================================================*/
/* Table: constant                                              */
/*==============================================================*/
create table constant
(
   id                   int unsigned not null,
   name                 varchar(100) comment '常量名称',
   shortcut             varchar(100) comment '常量简称,用于快速定位',
   pid                  int unsigned comment '父ID,null为根节点',
   level                int unsigned comment '当前层级;pid=null时为1级',
   has_child            int unsigned comment '是否有子节点0false,1true',
   enabled              int unsigned comment '是否启用0false,1true',
   deleted              int unsigned comment '是否删除0false, 1true',
   extra                int unsigned comment '附加数据;推荐存JSON',
   primary key (id)
);

/*==============================================================*/
/* Table: operation_log                                         */
/*==============================================================*/
create table operation_log
(
   id                   int unsigned not null,
   method               varchar(100) comment '调用方法的限定名',
   business_name        varchar(100) comment '方法的业务名称',
   type                 varchar(100) comment 'insert/update/delete',
   user_id              int unsigned comment '操作人用户表ID',
   user_name            varchar(100) comment '操作人名称',
   create_date          timestamp,
   old_data             varchar(1000),
   new_data             varchar(1000),
   primary key (id)
);

/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission
(
   id                   int unsigned not null,
   name                 int unsigned,
   deleted              int unsigned comment '是否删除0false, 1true',
   primary key (id)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int unsigned not null,
   name                 varchar(100),
   enabled              int unsigned comment '是否启用0false, 1true',
   deleted              int unsigned comment '是否删除0false, 1true',
   primary key (id)
);

/*==============================================================*/
/* Table: role_permission_map                                   */
/*==============================================================*/
create table role_permission_map
(
   id                   int unsigned not null,
   role_id              int unsigned,
   permission_id        int unsigned,
   deleted              int unsigned comment '是否删除0false, 1true',
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int unsigend not null,
   username             varchar(100) comment '用户名',
   password             varchar(100) comment '密码',
   idcard               varchar(100) comment '身份证',
   email                varchar(100) comment '邮箱',
   name                 varchar(100) comment '姓名',
   sex                  int unsigned comment '性别',
   phone                varchar(100) comment '手机号',
   enabled              int unsigned comment '是否启用0false，1true',
   deleted              int unsigned comment '是否删除0false，1true',
   primary key (id)
);

/*==============================================================*/
/* Table: user_role_map                                         */
/*==============================================================*/
create table user_role_map
(
   id                   int unsigned not null,
   user_id              int unsigned,
   role_id              int unsigned,
   deleted              int unsigned comment '是否删除0false, 1true',
   primary key (id)
);

