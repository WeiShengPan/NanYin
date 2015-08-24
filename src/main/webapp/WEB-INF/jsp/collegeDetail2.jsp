<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/20
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="/Js/jquery-1.4.4.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/collegeDetail2.css"/>
  <%@include file="loginTemplate.jsp"%>
  <title>南音社团</title>
</head>
<body>
<div id="container">
  <!--引入网页头部-->
  <%@include file="header.jsp"%>
  <!--网页内容-->
  <div id="content">
    <!--主体内容-->
    <div id="main">
      <div id="college">
        <p class="title">基本信息</p>
        <p>社团名称：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.name}</p>
        <p>成立时间：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.formDate}</p>
        <p>历届领导：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.exLeader}</p>
        <p>现任领导：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.leader}</p>
        <p>主要成员：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.members}</p>
        <p>会员人数：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.memberNum}</p>
        <p>活动时间：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.activeDate}</p>
        <p class="title">联系方式</p>
        <p>联络人：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.contact}</p>
        <p>联系电话：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.tel}</p>
        <p>联络地址：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.address}</p>
        <p>电子邮箱：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.email}</p>
        <p>社团网址：</p>
        <p class="title">其他信息</p>
        <p>资料提供：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${college.provider}</p>
        <p>备 注：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp联系时请说是在【福建南音网】上看到的，谢谢！</p>
        <p class="title">社团简介</p>
        <div id="introduction">
          ${college.introduction}
        </div>
      </div>
    </div>
    <!--引入侧栏-->
    <%@include file="sidebar.jsp"%>
  </div>
  <!--引入网页底部-->
  <%@include file="footer.jsp"%>
</div>
</body>
</html>
