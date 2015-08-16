<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/8/15
  Time: 12:17
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
  <link rel="stylesheet" type="text/css" href="/css/newsDetail.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title></title>
  <script type="text/javascript">
    $(document).ready(function(){
      $.ajax({
        type:'POST',
        url:'index.do?method=test',
        async:false,
        data:{
          page:"testPage",
          newsId:${news.newsId},
        }
      });
      $("#test").load(function(){
        $(this).height(0);
        var height = $(this).contents().height();
        $(this).height(height);
      });
    });
  </script>

</head>
<body>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <!--主体内容-->
      <div id="main">
        <div align="center" style="width: 95%;height:auto;margin: 0 auto;border: #ccc 1px dashed ">
          <iframe id="test" name="test" src="/WEB-INF/testPage.jsp" scrolling="no" frameborder="0" width="100%" height="100%" marginheight="0" marginwidth="0"></iframe>
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
