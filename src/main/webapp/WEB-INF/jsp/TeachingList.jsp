<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/20
  Time: 15:30
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
  <link rel="stylesheet" type="text/css" href="/css/teachingList.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title>南音教学</title>
  <script type="text/javascript">
    var curPage= 1,pageSize=2;
    var totalNum=0,totalPage=0;
    $(document).ready(function(){
      var t= ${type};
      if (t == 0) {
        $("#title").text('南音教学');
      } else if (t == 1) {
        $("#title").text('工乂谱识谱');
      } else if (t == 2) {
        $("#title").text('乐器技法');
      } else if (t == 3) {
        $("#title").text('唱腔指导曲');
      } else if (t == 4) {
        $("#title").text('名师访谈唱');
      }



      $.ajax({
        type:'POST',
        url:'pagination.do?method=showTeachingList',
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
            var tr='<tr><td><a href="index.do?method=teachingLink&id='+result[i].id+'">'+result[i].title+'</a></td><td>'+result[i].teacher+'</td>' +
                    '<td>'+result[i].source+'</td><td>'+result[i].date+'</td><td>'+result[i].hits+'</td></tr>';
            $("#teachingList table tbody").append(tr);
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
        url:'pagination.do?method=teachingTurnPage',
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
            var tr='<tr><td><a href="index.do?method=teachingLink&id='+result[i].id+'">'+result[i].title+'</a></td><td>'+result[i].teacher+'</td>' +
                    '<td>'+result[i].source+'</td><td>'+result[i].date+'</td><td>'+result[i].hits+'</td></tr>';
            $("#teachingList table tbody").append(tr);
          }
          show();
        }
      });
    }

    function firstPage(){
      if(curPage!=1){
        curPage=1;
        $("#teachingList table tbody").empty();
        getDate();
      }
    }
    function lastPage(){
      if(curPage!=totalPage){
        curPage=totalPage;
        $("#teachingList table tbody").empty();
        getDate();
      }
    }
    function prePage(){
      if(curPage!=1){
        curPage=curPage-1;
        $("#teachingList table tbody").empty();
        getDate();
      }
    }
    function nextPage(){
      if(curPage!=totalPage){
        curPage=curPage+1;
        $("#teachingList table tbody").empty();
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
      $("#teachingList table tbody").empty();
      getDate();
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
        <div id="title"></div>
        <div id="teachingList">
          <table align="center">
            <thead>
            <tr class="title">
              <td style="width: 30%">标题</td>
              <td style="width: 20%">指导老师</td>
              <td style="width: 20%">来源</td>
              <td style="width: 20%">上传日期</td>
              <td style="width: 10%">点击量</td>
            </tr>
            </thead>
            <tbody></tbody>
          </table>
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
