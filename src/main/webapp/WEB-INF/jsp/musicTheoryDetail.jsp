<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/21
  Time: 12:12
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
  <link rel="stylesheet" type="text/css" href="/css/musicTheoryDetail.css"/>
  <%@include file="loginTemplate.jsp"%>
  <title>南音文库</title>
</head>
<body>
<div id="container">
  <!--引入网页头部-->
  <%@include file="header.jsp"%>
  <!--网页内容-->
  <div id="content">
    <!--主体内容-->
    <div id="main">
      <div id="musicTheory">
        <div id="title">
          <p style="font-size: 16px;font-weight:bolder">${musicTheory.title}</p>
          <p>作者:${musicTheory.author}&nbsp&nbsp来源:${musicTheory.source}&nbsp&nbsp
            阅读:${musicTheory.hits}次 &nbsp&nbsp上传时间:${musicTheory.date} </p>
        </div>
        <div id="musicTheoryContent">
          <div id="cc" style="margin-left: 15px; margin-right: 15px; height: auto">
            ${musicTheory.content}
          </div>
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

