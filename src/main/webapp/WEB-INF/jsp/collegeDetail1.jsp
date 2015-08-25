<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/20
  Time: 9:47
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
  <link rel="stylesheet" type="text/css" href="/css/collegeDetail1.css"/>
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
          <p class="title">社团简介</p>
          <div id="introduction">${college.introduction}</div>
          <p class="title">组织结构</p>
          <div id="organization">${college.organization}</div>
          <p class="title">活动剪影</p>
          <div id="activeImage">${college.activeImage}</div>
          <p class="title">社团视频</p>
          <div id="video">
            <!-- 播放器(52player.com)/代码开始 -->
            <script type="text/javascript" src="/Js/swfobject.js"></script>
            <div id="CuPlayer" > <strong>抱歉，网页出现错误，给您带来的不便，敬请原谅！</strong> </div>
            <script type="text/javascript">
              var so = new SWFObject("/Js/JZminiPlayer.swf","ply","600","400","9","#000000");
              so.addParam("allowfullscreen","true");
              so.addParam("allowscriptaccess","always");
              so.addParam("wmode","opaque");
              so.addParam("quality","high");
              so.addParam("salign","lt");
              so.addVariable("file","${college.video}");//视频地址
              so.addVariable("img","/images/logo.jpg");//视频图片
              so.addVariable("autoplay","false");//是否自动播放
              so.write("CuPlayer");
            </script>
            <!-- 我爱播放器(52player.com)/代码结束 -->
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
