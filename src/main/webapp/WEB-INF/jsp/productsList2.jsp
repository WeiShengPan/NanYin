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
  <link rel="stylesheet" type="text/css" href="/css/productsList2.css"/>
  <%@include file="loginTemplate.jsp"%>
  <title>南音商城</title>
  <script type="text/javascript">
    var curPage= 1,pageSize=5;
    var totalNum=0,totalPage=0;
    $(document).ready(function(){

      var t=$("#type").text();
      if(t==1){
        $("#title").text('南音书籍');
      }else if(t==2){
        $("#title").text('南音音像');
      }else if(t==3){
        $("#title").text('南音乐器');
      }else if(t==4){
        $("#title").text('南音伴奏');
      }else if(t==5){
        $("#title").text('文创艺品');
      }else if(t==6){
        $("#title").text('其他配件');
      }

      $.ajax({
      type:'POST',
      url:'pagination.do?method=showProductList',
      data:{
        type:${type},
        curPage:curPage,
        pageSize:pageSize,
      },
      error:function( ){
        alert("加载失败！");
      },
      success:function(data) {
        var result = $.parseJSON(data);
        totalNum=result[0].totalNum;
        totalPage=result[0].totalPage;
        for(var i=1;!(i >=result.length);i++){
          var text=' <div>'+
                  '<a target="_blank" href="index.do?method=productLink&id='+result[i].id+'" title="'+result[i].name+'">' +
                  '<img width="120px" height="160px" src="'+result[i].path+'" border="0"></a><br>'+
                  '<p class="name">《'+result[i].name+'》</p>'+
                  '<p class="price">价格: '+result[i].price+'</p>'+
                  '</div>';
          $("#main #products").append(text);
        }
        show();
      }
    });
    });

    function show(){
      $("#totalNum").text("总记录数："+totalNum);
      $("#pageNum").text("当前第"+curPage+""+"/"+""+totalPage+""+"页" );
    }

    function getDate(){
      $.ajax({
        type:'POST',
        url:'pagination.do?method=productTurnPage',
        data:{
          type:${type},
          curPage:curPage,
          pageSize:pageSize,
        },
        error:function( ){
          alert("加载失败！");
        },
        success:function(data) {
          var result = $.parseJSON(data);
          for(var i=0;!(i >=result.length);i++){
            var text=' <div>'+
                    '<a target="_blank" href="index.do?method=productLink&id='+result[i].id+'" title="'+result[i].name+'">' +
                    '<img width="120px" height="160px" src="'+result[i].path+'" border="0"></a><br>'+
                    '<p class="name">《'+result[i].name+'》</p>'+
                    '<p class="price">价格: '+result[i].price+'</p>'+
                    '</div>';
            $("#main #products").append(text);
          }
          show();
        }
      });
    }

    function firstPage(){
      if(curPage!=1){
        curPage=1;
        $("#main #products").empty();
        getDate();
      }
    }
    function lastPage(){
      if(curPage!=totalPage){
        curPage=totalPage;
        $("#main #products").empty();
        getDate();
      }
    }
    function prePage(){
      if(curPage!=1){
        curPage=curPage-1;
        $("#main #products").empty();
        getDate();
      }
    }
    function nextPage(){
      if(curPage!=totalPage){
        curPage=curPage+1;
        $("#main #products").empty();
        getDate();
      }
    }
    function gotoPage(){
      var n=parseInt($("#pagination #goto").val());
      if(!(n>=1)||n>totalPage){
        alert("页码无效！");
        $("#pagination #goto").val("");
        return;
      }
      curPage=n;
      $("#main #products").empty();
      getDate();
    }
  </script>
</head>
<body>
<label id="type" style="display: none">${type}</label>
<div id="container">
  <!--引入网页头部-->
  <%@include file="header.jsp"%>
  <!--网页内容-->
  <div id="content">
    <div id="main">
      <div id="title"></div>
      <div id="products">
        <div>

        </div>
      </div>
      <div id="pagination">
        <label>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp每页显示条数：2 &nbsp</label>
        <label id="totalNum"></label>
        <label><a href="javascript:void(0)" onclick="firstPage()"> &nbsp[第一页]</a></label>
        <label><a href="javascript:void(0)" onclick="prePage()">[上一页]</a></label>
        <label><a href="javascript:void(0)" onclick="nextPage()">[下一页]</a></label>
        <label><a href="javascript:void(0)" onclick="lastPage()">[最后一页]</a></label>
        <label>&nbsp&nbsp跳转至:</label>
        <label><input id="goto" type="text"  style="width: 20px"/></label>
        <label><input type="submit" value="go" onclick="gotoPage()"/>&nbsp&nbsp</label>
        <label id="pageNum"></label>
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
