<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/21
  Time: 12:11
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
  <link rel="stylesheet" type="text/css" href="/css/libraryDetail.css"/>
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
        <div id="library">
          <div id="title">
            <p style="font-size: 16px;font-weight:bolder">${library.title}</p>
            <p>作者:${library.author}&nbsp&nbsp来源:${library.source}&nbsp&nbsp
              阅读:${library.hits}次 &nbsp&nbsp上传时间:${library.date} </p>
          </div>
          <div id="libraryContent">
            <div id="cc" style="margin-left: 15px; margin-right: 15px; height: auto">
              ${library.content}
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
