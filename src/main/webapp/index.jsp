<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>



<html lang="en"><head>
    <meta charset="UTF-8">
    <title></title>
    <style id="system" type="text/css">h1,h2,h3,h4,h5,h6,p,blockquote {    margin: 0;    padding: 0;}body {    font-family: "Helvetica Neue", Helvetica, "Hiragino Sans GB", Arial, sans-serif;    font-size: 13px;    line-height: 18px;    color: #737373;    margin: 10px 13px 10px 13px;}a {    color: #0069d6;}a:hover {    color: #0050a3;    text-decoration: none;}a img {    border: none;}p {    margin-bottom: 9px;}h1,h2,h3,h4,h5,h6 {    color: #404040;    line-height: 36px;}h1 {    margin-bottom: 18px;    font-size: 30px;}h2 {    font-size: 24px;}h3 {    font-size: 18px;}h4 {    font-size: 16px;}h5 {    font-size: 14px;}h6 {    font-size: 13px;}hr {    margin: 0 0 19px;    border: 0;    border-bottom: 1px solid #ccc;}blockquote {    padding: 13px 13px 21px 15px;    margin-bottom: 18px;    font-family:georgia,serif;    font-style: italic;}blockquote:before {    content:"C";    font-size:40px;    margin-left:-10px;    font-family:georgia,serif;    color:#eee;}blockquote p {    font-size: 14px;    font-weight: 300;    line-height: 18px;    margin-bottom: 0;    font-style: italic;}code, pre {    font-family: Monaco, Andale Mono, Courier New, monospace;}code {    background-color: #fee9cc;    color: rgba(0, 0, 0, 0.75);    padding: 1px 3px;    font-size: 12px;    -webkit-border-radius: 3px;    -moz-border-radius: 3px;    border-radius: 3px;}pre {    display: block;    padding: 14px;    margin: 0 0 18px;    line-height: 16px;    font-size: 11px;    border: 1px solid #d9d9d9;    white-space: pre-wrap;    word-wrap: break-word;}pre code {    background-color: #fff;    color:#737373;    font-size: 11px;    padding: 0;}@media screen and (min-width: 768px) {    body {        width: 748px;        margin:10px auto;    }}</style><style id="custom" type="text/css"></style></head>
<body marginheight="0"><h1>欢迎使用 Common-Core</h1>
<p>基于maven的spring4.3+mybatis3.4+swagger2.6的后台整合，用于快速构建中小型API、RESTful API项目，该项目简单、快速、易扩展；使我们摆脱那些重复劳动，专注于业务代码的编写。


</p>
<h3>已完成功能</h3>
<ul>
    <li>增加权限统一拦截注解<code>@Authentication</code></li>
    <li>增加方法性能统计注解<code>@printRunTime</code></li>
    <li>增加简单的操作日志<code>@OperationLog</code></li>
    <li>实现了用户登录日志</li>
    <li>实现了简单的权限系统</li>
    <li>生成entity类时自动提取数据库注释生成swagger文档</li>
    <li>实现了更漂亮的swagger-ui</li>
</ul>
<h3>快速开始</h3>
<ul>
    <li>clone本项目，创建下面的数据库和表</li>
    <li>使用IDE导入本项目，使用maven方式导入项目</li>
    <li>配置<code>jdbc.properties</code>下面的数据库相关信息（如果你需要使用mybitis逆向插件，也需要配置<code>generatorConfig.xml</code>这个文件中的数据库信息）</li>
    <li>使用maven编译后，配置tomcat并部署</li>
    <li>启动tomcat,访问以下链接测试接口；</li>
    <li>访问<code>http://localhost:8080/docs/index.html</code> 查看swagger2生成的Api文档信息</li>
    <li>根据需求进行快速迭代开发</li>
</ul>
<h3>目前表单,详细信息在"/resources/archives"目录下</h3>
<pre><code class="lang-sql">drop database common_core;

    create database common_core;

    use common_core;

    create table user;

    create table role;

    create table permission;

    create table role_permission_map;

    create table user_role_map;

    create table constant;

    create table operation_log;</code></pre>
<h3>开发者建议</h3>
<ul>
    <li>表名，建议使用小写，多个单词使用下划线拼接</li>
    <li>entity内成员变量建议与表字段数量对应</li>
    <li>前端统一使用<code>Content-Type=application/json</code>传参;<code>controller</code>层统一使用<code>@RequestBody</code>入参,参数可以使用Map接收,也可考虑封装成VO对象(推荐)</li>
    <li>需要工具类的话建议先从<code>common/utils</code>中找，实在没有再造轮子或引入类库，尽量精简项目</li>
    <li>开发规范建议遵循阿里巴巴Java开发手册（<a href="https://github.com/lihengming/java-codes/blob/master/shared-resources/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8CV1.2.0.pdf">最新版下载</a>)</li>
    <li>建议在公司内部使用ShowDoc、Swagger2 、RAP等开源项目来编写、管理API文档</li>
    <li>页面常量信息建议放在<code>constants</code>表;如民族/地址/证件类型/性别等;</li>
    <li>所有项目文档放置在<code>/resources/archives</code>目录下</li>
    <li>建议所有DTO/BO放在相应service目录下;VO放在相应controller目录下</li>
    <li>修改已有表结构时,不建议修改以下字段(id,enabled,deleted);因为这些字段已在开发中用到</li>
    <li>增删改方法命名分别以<code>insert/delete/update</code>打头</li>
</ul>
<h3>相关环境(推荐使用环境)</h3>
<ul>
    <li>OS Microsoft Windows 10 Pro</li>
    <li>Editor IntelliJ IDEA</li>
    <li>Java 8</li>
    <li>SpringMVC 4.3</li>
    <li>Mybitis 3.4</li>
    <li>Mysql 5.5.50</li>
    <li>Maven 3.5.3</li>
    <li>Git 2.14.1</li>
    <li>Tomcat 7.0.85</li>
    <li>Swagger 2.6.1</li>
    <li>Restful interface</li>
</ul>
<h3>注意事项</h3>
<ul>
    <li>使用mybaitis-generator插件生成dao层时请先删除原来的文件,不然生的的内容会追加到源文件中,出现代码重复</li>
    <li>下载后如打不开swagger2文档，可能需要修改<code>webapp/common-core-swagger-ui/config.js</code>文件中得地址</li>
</ul>
<p>Edit By <a href="http://mahua.jser.me">MaHua</a></p>
</body></html>