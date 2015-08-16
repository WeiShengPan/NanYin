<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/8/15
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title></title>
</head>
<body>
  <div id="container" style="width:800px; height: auto ">
    <div id="title" style="width: 100%; height: 50px;line-height: 25px">
      <p><h2>${news.title}</h2></p>
      <p>作者:${news.singer}&nbsp&nbsp阅读:${news.hits}次 &nbsp&nbsp上传时间:${news.date} </p>
    </div>
    <div id="content" style="width: 100%;margin-top: 20px;line-height: 25px;">
      ${news.content}
    </div>
  </div>
</body>
</html>
