<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/7
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="/Js/jquery-1.4.4.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/register1.css"/>
    <title>注册会员</title>
</head>
<body>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <div id="title"><p>会员注册</p></div>
      <div id="Info">
        <div id="left">
          <p align="center" style="font-size: 16px; font-weight: bold">普通会员</p>
          <p>1、普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务</p>
          <p>2、普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务</p>
          <p>3、普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务</p>
          <p>4、普通会员的享受的服务普通会员的享受的服务</p>
          <p>5、普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务普通会员的享受的服务</p><br><br><br><br><br><br><br><br>
          <p align="center"><a href="index.do?method=register&type=1">普通会员注册</a></p>
        </div>
        <div id="right">
          <p align="center" style="font-size: 16px; font-weight: bold">高级会员</p>
            <p>1、高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务</p>
            <p>2、高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务</p>
            <p>3、高级会员的享受的服务高级会员的享受的服务</p>
            <p>4、高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务</p>
            <p>5、高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务</p>
            <p>6、高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务</p>
            <p>7、高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务高级会员的享受的服务</p><br><br><br>
          <p align="center"><a href="index.do?method=register&type=2">高级会员注册</a></p>
        </div>
      </div>
    </div>
    <!--引入网页底部-->
    <%@include file="footer.jsp"%>
  </div>
</body>
</html>
