<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/18
  Time: 8:53
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
  <link rel="stylesheet" type="text/css" href="/css/collegeList.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title>社团列表</title>
  <script type="text/javascript">
   var curPage= 1,pageSize=3;
    var totalNum=0,totalPage=0;
    $(document).ready(function() {
      var t = $("#type").text();
      if (t == 0) {
        $("#title").text('南音社团');
      } else if (t == 1) {
        $("#title").text('荣誉社团');
      } else if (t == 2) {
        $("#title").text('国内社团');
      } else if (t == 3) {
        $("#title").text('港澳台社团');
      } else if (t == 4) {
        $("#title").text('东南亚社团');
      } else if (t == 5) {
        $("#title").text('传习培训');
      }

      $.ajax({
        type:'POST',
        url:'pagination.do?method=showCollegeList',
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
            var b=result[i].vip;
            if(b==true){
              var level='<p style="color: red">高级</p>';
            }else{
              var level='<p>普通</p>';
            }
            var text='<div>'+
                        '<table cellspacing="0" cellpadding="0">'+
                            '<tr><td style="font-size: 14px;font-weight: bold" colspan="2" height="25px">'+result[i].name+'</td></tr>'+
                            '<tr><td width="30%" height="25px">联系人:</td><td width="70%" style="text-align: left;padding-left: 2px">'+result[i].contact+'</td></tr>'+
                            '<tr><td height="25px">电&nbsp&nbsp话:</td><td  style="text-align: left;padding-left: 2px">'+result[i].tel+'</td></tr>'+
                            '<tr><td height="25px">级&nbsp&nbsp别:</td><td  style="text-align: left;padding-left: 2px">'+level+'</td></tr>'+
                            '<tr><td height="25px">社团信息:</td><td  style="text-align: left;padding-left: 2px"><a href="index.do?method=collegeLink&id='+result[i].id+'">点击查看</a></td></tr>'+
                            '<tr><td height="50px">地&nbsp&nbsp址:</td><td  style="text-align: left;padding-left: 2px">'+result[i].address+'</td></tr>'+
                          '</table>'+
                        '</div>';
            $("#main #college").append(text);
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
        url:'pagination.do?method=collegeTurnPage',
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
            var b=result[i].vip;
            if(b==true){
              var level='<p style="color: red">高级</p>';
            }else{
              var level='<p>普通</p>';
            }
            var text='<div>'+
                    '<table cellspacing="0" cellpadding="0">'+
                    '<tr><td style="font-size: 14px;font-weight: bold" colspan="2" height="25px">'+result[i].name+'</td></tr>'+
                    '<tr><td width="30%" height="25px">联系人:</td><td width="70%" style="text-align: left;padding-left: 2px">'+result[i].contact+'</td></tr>'+
                    '<tr><td height="25px">电&nbsp&nbsp话:</td><td  style="text-align: left;padding-left: 2px">'+result[i].tel+'</td></tr>'+
                    '<tr><td height="25px">级&nbsp&nbsp别:</td><td  style="text-align: left;padding-left: 2px">'+level+'</td></tr>'+
                    '<tr><td height="25px">社团信息:</td><td  style="text-align: left;padding-left: 2px"><a href="index.do?method=collegeLink&id='+result[i].id+'">点击查看</a></td></tr>'+
                    '<tr><td height="50px">地&nbsp&nbsp址:</td><td  style="text-align: left;padding-left: 2px">'+result[i].address+'</td></tr>'+
                    '</table>'+
                    '</div>';
            $("#main #college").append(text);
          }
          show();
        }
      });
    }

    function firstPage(){
      if(curPage!=1){
        curPage=1;
        $("#main #college").empty();
        getDate();
      }
    }
    function lastPage(){
      if(curPage!=totalPage){
        curPage=totalPage;
        $("#main #college").empty();
        getDate();
      }
    }
    function prePage(){
      if(curPage!=1){
        curPage=curPage-1;
        $("#main #college").empty();
        getDate();
      }
    }
    function nextPage(){
      if(curPage!=totalPage){
        curPage=curPage+1;
        $("#main #college").empty();
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
      $("#main #college").empty();
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
        <div id="college">

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
