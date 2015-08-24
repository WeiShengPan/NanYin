<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/24
  Time: 16:55
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
  <link rel="stylesheet" type="text/css" href="/css/submission.css"/>
  <%@include file="loginTemplate.jsp"%>
  <title>福建南音网</title>
</head>
<body>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <div id="content">
      <div id="main">
        <div id="submissionCont">
          <p style="text-align: center; font-size: 16px; font-weight: bold; margin-bottom: 20px;">欢迎投稿</p>
          <p>如果您有好的关于南音的新闻材料，请发送到一下邮箱：123456789@163.com</p>
          <p>谢谢</p>
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
