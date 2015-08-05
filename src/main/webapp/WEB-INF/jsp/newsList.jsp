<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/7/31
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="/Js/jquery-1.4.4.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/newsList.css"/>
    <title>南音新闻</title>
  <script type="text/javascript">
    var curPage= 1,pageSize=2;
    var totalNum,totalPage;
    var jsonData;
    $(document).ready(function(){
      $.ajax({
        type:'POST',
        url:'index.do?method=showNewsList',
        error:function(data){
          alert("error:"+data);
        },
        success:function(data) {
          jsonData = $.parseJSON(data);
          var result = $.parseJSON(data);
          var len;
          if(!(result.length>pageSize)){
            len=result.length;
          }else{
            len=pageSize;
          }
          for(var i=0;!(i >= len);i++){
             var tr='<tr><td><a href="index.do?method=newsLink&id='+result[i].id+'">'+result[i].title+'</a></td><td>'+result[i].author+'</td>' +
                     '<td>'+result[i].date+'</td><td>'+result[i].hits+'</td></tr>';
             $("#main table tbody").append(tr);
            }
          compute();
          show();
          }
        });

    });

    function compute(){
        totalNum=jsonData.length;
        totalPage=Math.ceil(totalNum/pageSize);
    }

    function show(){
      $("#totalNum").text("总记录数："+totalNum);
      $("#pageNum").text("当前第"+curPage+""+"/"+""+totalPage+""+"页" );
    }

    function firstPage(){
      if(curPage==1){
        return;
      }
      curPage=1;
      var len;
      if(!(jsonData.length>pageSize)){
        len=jsonData.length;
      }else{
        len=pageSize;
      }
      $("#main table tbody").empty();
      for(var i=0;!(i >= len);i++){
        var tr='<tr><td><a href="index.do?method=newsLink&id='+jsonData[i].id+'">'+jsonData[i].title+'</a></td><td>'+jsonData[i].author+'</td>' +
                '<td>'+jsonData[i].date+'</td><td>'+jsonData[i].hits+'</td></tr>';
        $("#main table tbody").append(tr);
      }
      show();
    }

    function nextPage(){
      if(curPage==totalPage){
        return;
      }
      curPage=curPage+1;
      var residue=totalNum-((curPage-1)*pageSize);
      var start=(curPage-1)*pageSize;
      var len;
      if(!(residue>pageSize)){
        len=start+residue;
      }else{
        len=start+pageSize;
      }
      $("#main table tbody").empty();
      for(var i=start;!(i>=len);i++){
        var tr='<tr><td><a href="index.do?method=newsLink&id='+jsonData[i].id+'">'+jsonData[i].title+'</a></td><td>'+jsonData[i].author+'</td>' +
                '<td>'+jsonData[i].date+'</td><td>'+jsonData[i].hits+'</td></tr>';
        $("#main table tbody").append(tr);
      }
      show();
    }

    function prePage(){
      if(curPage==1){
        return;
      }
      curPage=curPage-1;
      var start=(curPage-1)*pageSize;
      var len=start+pageSize;
      $("#main table tbody").empty();
      for(var i=start;!(i>=len);i++){
        var tr='<tr><td><a href="index.do?method=newsLink&id='+jsonData[i].id+'">'+jsonData[i].title+'</a></td><td>'+jsonData[i].author+'</td>' +
                '<td>'+jsonData[i].date+'</td><td>'+jsonData[i].hits+'</td></tr>';
        $("#main table tbody").append(tr);
      }
      show();
    }

    function lastPage(){
      if(curPage==totalPage){
        return;
      }
      curPage=totalPage;
      var residue=totalNum-((curPage-1)*pageSize);
      var start=(curPage-1)*pageSize;
      var len=start+residue;
      $("#main table tbody").empty();
      for(var i=start;!(i>=len);i++){
        var tr='<tr><td><a href="index.do?method=newsLink&id='+jsonData[i].id+'">'+jsonData[i].title+'</a></td><td>'+jsonData[i].author+'</td>' +
                '<td>'+jsonData[i].date+'</td><td>'+jsonData[i].hits+'</td></tr>';
        $("#main table tbody").append(tr);
      }
      show();
    }

    function gotoPage(){
      var n=parseInt($("#pagination #goto").val());
      if(!(n>=1)||n>totalPage){
        alert("页码无效！");
        $("#pagination #goto").val("");
        return;
      }
      curPage=n;
      var residue=totalNum-((curPage-1)*pageSize);
      var start=(curPage-1)*pageSize;
      var len;
      if(!(residue>pageSize)){
        len=start+residue;
      }else{
        len=start+pageSize;
      }
      $("#main table tbody").empty();
      for(var i=start;!(i>=len);i++){
        var tr='<tr><td><a href="index.do?method=newsLink&id='+jsonData[i].id+'">'+jsonData[i].title+'</a></td><td>'+jsonData[i].author+'</td>' +
                '<td>'+jsonData[i].date+'</td><td>'+jsonData[i].hits+'</td></tr>';
        $("#main table tbody").append(tr);
      }
      show();
      $("#pagination #goto").val("");
    }
  </script>

</head>
<body>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <div id="main">
        <table align="center">
          <thead>
            <tr class="title">
              <td style="width: 40%">新闻标题</td>
              <td style="width: 25%">作者</td>
              <td style="width: 25%">日期</td>
              <td style="width: 20%">点击量</td>
            </tr>
          </thead>
          <tbody>

          </tbody>
          <tfoot>
            <tr>
              <td colspan="4">
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
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
      <!--引入侧栏-->
      <%@include file="sidebar.jsp"%>
    </div>
    <!--引入网页底部-->
    <%@include file="footer.jsp"%>
  </div>

</body>
</html>
