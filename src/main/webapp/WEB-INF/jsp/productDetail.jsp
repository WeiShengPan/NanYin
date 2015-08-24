<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/17
  Time: 8:56
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
  <link rel="stylesheet" type="text/css" href="/css/productDetail.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title>南音商城</title>
</head>
<body>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <!--主体内容-->
      <div id="main">
        <div id="product">
          <div id="img">
            <img width="100%" height="200px" src="${product.path}" border="0">
          </div>
          <div id="title">
            <p>商品名称:&nbsp&nbsp&nbsp ${product.name}</p>
            <p>商品价格:&nbsp&nbsp&nbsp ￥${product.price}</p>
            <p>推荐指数:&nbsp&nbsp&nbsp ${product.recommendation}颗星</p>
            <p>上架日期:&nbsp&nbsp&nbsp ${product.date}</p>
          </div>
          <div id="productCont">
            <div style="margin-top: 15px;margin-left: 5px;margin-right: 5px">
              ${product.content}
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
