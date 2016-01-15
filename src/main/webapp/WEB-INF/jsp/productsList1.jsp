<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/17
  Time: 8:55
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
  <link rel="stylesheet" type="text/css" href="/css/productsList1.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title>南音商城</title>
  <script type="text/javascript">
    $(document).ready(function(){
      $.ajax({
        type:'POST',
        url:'index.do?method=showProductList',
        data:{
          type:1,
        },
        error:function( ){
          alert("南音书籍加载失败！");
        },
        success:function(data) {
          var result= $.parseJSON(data);
          for(var i=0;!(i >=result.length);i++){
            $("#books #book"+i).find("a").attr("href","index.do?method=productLink&id="+result[i].id+"");
            $("#books #book"+i).find("a").attr("title",""+result[i].name+"");
            $("#books #book"+i).find("img").attr("src",""+result[i].path+"");
            $("#books #book"+i+" .name").text(''+result[i].name+'');
            $("#books #book"+i+" .price").text('价格:'+result[i].price+'');
          }
        }
      });
      $.ajax({
        type:'POST',
        url:'index.do?method=showProductList',
        data:{
          type:2,
        },
        error:function( ){
          alert("南音音像加载失败！");
        },
        success:function(data) {
          var result= $.parseJSON(data);
          for(var i=0;!(i >=result.length);i++){
            $("#media #media"+i).find("a").attr("href","index.do?method=productLink&id="+result[i].id+"");
            $("#media #media"+i).find("a").attr("title",""+result[i].name+"");
            $("#media #media"+i).find("img").attr("src",""+result[i].path+"");
            $("#media #media"+i+" .name").text(''+result[i].name+'');
            $("#media #media"+i+" .price").text('价格:'+result[i].price+'');
          }
        }
      });
      $.ajax({
        type:'POST',
        url:'index.do?method=showProductList',
        data:{
          type:3,
        },
        error:function( ){
          alert("南音乐器加载失败！");
        },
        success:function(data) {
          var result= $.parseJSON(data);
          for(var i=0;!(i >=result.length);i++){
            $("#instruments #instrument"+i).find("a").attr("href","index.do?method=productLink&id="+result[i].id+"");
            $("#instruments #instrument"+i).find("a").attr("title",""+result[i].name+"");
            $("#instruments #instrument"+i).find("img").attr("src",""+result[i].path+"");
            $("#instruments #instrument"+i+" .name").text(''+result[i].name+'');
            $("#instruments #instrument"+i+" .price").text('价格:'+result[i].price+'');
          }
        }
      });
      $.ajax({
        type:'POST',
        url:'index.do?method=showProductList',
        data:{
          type:4,
        },
        error:function( ){
          alert("南音伴奏加载失败！");
        },
        success:function(data) {
          var result= $.parseJSON(data);
          for(var i=0;!(i >=result.length);i++){
            $("#accompaniment #accompaniment"+i).find("a").attr("href","index.do?method=productLink&id="+result[i].id+"");
            $("#accompaniment #accompaniment"+i).find("a").attr("title",""+result[i].name+"");
            $("#accompaniment #accompaniment"+i).find("img").attr("src",""+result[i].path+"");
            $("#accompaniment #accompaniment"+i+" .name").text(''+result[i].name+'');
            $("#accompaniment #accompaniment"+i+" .price").text('价格:'+result[i].price+'');
          }
        }
      });

      $.ajax({
        type:'POST',
        url:'index.do?method=showProductList',
        data:{
          type:5,
        },
        error:function( ){
          alert("专辑录制加载失败！");
        },
        success:function(data) {
          var result= $.parseJSON(data);
          for(var i=0;!(i >=result.length);i++){
            $("#album #album"+i).find("a").attr("href","index.do?method=productLink&id="+result[i].id+"");
            $("#album #album"+i).find("a").attr("title",""+result[i].name+"");
            $("#album #album"+i).find("img").attr("src",""+result[i].path+"");
            $("#album #album"+i+" .name").text(''+result[i].name+'');
            $("#album #album"+i+" .price").text('价格:'+result[i].price+'');
          }
        }
      });

      $.ajax({
        type:'POST',
        url:'index.do?method=showProductList',
        data:{
          type:6,
        },
        error:function( ){
          alert("文创艺品加载失败！");
        },
        success:function(data) {
          var result= $.parseJSON(data);
          for(var i=0;!(i >=result.length);i++){
            $("#literary #literary"+i).find("a").attr("href","index.do?method=productLink&id="+result[i].id+"");
            $("#literary #literary"+i).find("a").attr("title",""+result[i].name+"");
            $("#literary #literary"+i).find("img").attr("src",""+result[i].path+"");
            $("#literary #literary"+i+" .name").text(''+result[i].name+'');
            $("#literary #literary"+i+" .price").text('价格:'+result[i].price+'');
          }
        }
      });
      $.ajax({
        type:'POST',
        url:'index.do?method=showProductList',
        data:{
          type:7,
        },
        error:function( ){
          alert("其他配件加载失败！");
        },
        success:function(data) {
          var result= $.parseJSON(data);
          for(var i=0;!(i >=result.length);i++){
            $("#others #others"+i).find("a").attr("href","index.do?method=productLink&id="+result[i].id+"");
            $("#others #others"+i).find("a").attr("title",""+result[i].name+"");
            $("#others #others"+i).find("img").attr("src",""+result[i].path+"");
            $("#others #others"+i+" .name").text(''+result[i].name+'');
            $("#others #others"+i+" .price").text('价格:'+result[i].price+'');
          }
        }
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
      <div id="main">
        <div class="title">
          <label>南音书籍</label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="index.do?method=dispatcher&page=productsList2&type=1">更多>></a>
        </div>
        <div id="books" class="wrapper">
          <div id="book0">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="book1">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="book2">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="book3">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="book4">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
        </div>

        <div class="title">
          <label>南音音像</label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="index.do?method=dispatcher&page=productsList2&type=2">更多>></a>
          </div>
        <div id="media" class="wrapper">
          <div id="media0">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="media1">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="media2">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="media3">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="media4">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
        </div>

        <div class="title">
          <label>南音乐器</label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="index.do?method=dispatcher&page=productsList2&type=3">更多>></a>
        </div>
        <div id="instruments" class="wrapper">
          <div id="instrument0">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="instrument1">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="instrument2">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="instrument3">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="instrument4">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
        </div>

        <div class="title">
          <label>南音伴奏</label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="index.do?method=dispatcher&page=productsList2&type=4">更多>></a>
        </div>
        <div id="accompaniment" class="wrapper">
          <div id="accompaniment0">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="accompaniment1">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="accompaniment2">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="accompaniment3">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="accompaniment4">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
        </div>

        <div class="title">
          <label>专辑录制</label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="index.do?method=dispatcher&page=productsList2&type=5">更多>></a>
        </div>
        <div id="album" class="wrapper">
          <div id="albun0">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="albun1">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="albun2">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="albun3">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="albun4">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
        </div>

        <div class="title">
          <label>文创艺品</label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="index.do?method=dispatcher&page=productsList2&type=6">更多>></a>
        </div>
        <div id="literary" class="wrapper">
          <div id="literary0">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="literary1">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="literary2">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="literary3">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="literary4">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
        </div>

        <div class="title">
          <label>其他配件</label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="index.do?method=dispatcher&page=productsList2&type=7">更多>></a>
        </div>
        <div id="others" class="wrapper">
          <div id="others0">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="others1">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="others2">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="others3">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
          </div>
          <div id="others4">
            <a target="_blank" href=""><img width="120px" height="160px" src="" border="0"></a><br>
            <p class="name"></p>
            <p class="price"></p>
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
