<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/8/1
  Time: 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="/Js/jquery-1.4.4.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/newsDetail.css"/>
    <title>南音新闻</title>
  <script type="text/javascript">
    $(document).ready(function(){
      var news= $.parseJSON($("#data").text());
      if(news.source!=""){
        var text='<p><label><h2>'+news.title+'</h2></label></p>'+
                '<p><label>作者:</label><label>'+news.author+'</label>'+
                '<label>&nbsp&nbsp来源:</label><label>'+news.source+'</label>'+
                '<label>&nbsp&nbsp阅读:</label><label>'+news.hits+'次</label>'+
                '<label>&nbsp&nbsp日期:</label><label>'+news.date+'</label></p>';
      }else{
        var text='<p><label><h2>'+news.title+'</h2></label></p>'+
                '<p><label>作者:</label><label>'+news.author+'</label>'+
                '<label>&nbsp&nbsp阅读:</label><label>'+news.hits+'次</label>'+
                '<label>&nbsp&nbsp日期:</label><label>'+news.date+'</label></p>';
      }
      var newsCont=news.content;
      $("#main #news #title").append(text);
      $("#main #news #newsContent").append(newsCont);
    });

  </script>
</head>
<body>
  <!--隐藏的数据-->
  <div id="data" style="display: none">${news}</div>
  <!--************************-->

  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <!--主体内容-->
      <div id="main">
        <div id="news">
          <div id="title">

          </div>
          <div id="newsContent">

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
