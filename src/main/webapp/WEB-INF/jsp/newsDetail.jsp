
<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/1
  Time: 8:31
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
    <title>南音新闻</title>
  <script type="text/javascript">
    var size=0;
    var commentsDate="";
    $(document).ready(function(){

      var news= $.parseJSON($("#data").text());
      if(news.source!=""){
        var text='<p style="font-size: 16px;font-weight: bolder">'+news.title+'</p>'+
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
      $("#main #news #title").append(text);

      $.ajax({
        type:'POST',
        url:'comments.do?method=listNewsComments',
        data:{
          newsId:$("#newsId").text(),
        },
        success:function(data){
          var result= $.parseJSON(data);
          if(result==""){
            $("#comments").append("<p align='center' style='text-align: center; margin-top: 15px; margin-bottom: 15px;'>暂无评论</p>");
            return;
          }
          commentsDate=result;
          size=result.length;
          if(!(size>5)){
            for(var i=0;!(i>=result.length);i++){
              var text='<p class="c4">'+result[i].userName+'&nbsp('+result[i].date+')</p>' +
                      '<p>'+result[i].content+'</p>';
              $("#comments").append(text);
            }
          }else{
            for(var i=0;!(i>=5);i++){
              var text='<p class="c4">'+result[i].userName+'&nbsp('+result[i].date+')</p>' +
                      '<p>'+result[i].content+'</p>';
              $("#comments").append(text);
            }
            var link='<p class="c5" align="center"><a href="javascript:void(0)" onclick="moreComm()">更多评论,点击查看>></a></p>';
            $("#comments").append(link);
          }

        }
      });
    });

    function resetComments(){
      $("#sendComments #commentsCont").val("");
    }

    function sendComments(){
      $.ajax({
        type:'POST',
        url:'index.do?method=refresh',
        async:false,
        success:function(data){
          $("#userName").text(data);
        }
      });

      var user= $("#userName").text();
      if(user!=""){
        var cont=$("#sendComments #commentsCont").val();
        if(cont!=""){
          $.ajax({
            type:'POST',
            url:'comments.do?method=sendNewsComments',
            data:{
              newsId:$("#newsId").text(),
              content:cont,
            },
            error:function( ){
              alert("抱歉，发送出错！");
            },
            success:function(){
              alert("发送成功，等待审核！");
            }
          });
          $("#sendComments #commentsCont").val("");
        }else{
          alert("不能发送空的评论！");
        }
      }else{
        alert("请先登录会员账号！");
      }
    }

    function moreComm(){
      $("#comments").empty();
      for(var i=0;!(i>=size);i++){
        var text='<p class="c4">'+commentsDate[i].userName+'&nbsp('+commentsDate[i].date+')</p>' +
                '<p>'+commentsDate[i].content+'</p>';
        $("#comments").append(text);
      }
    }



  </script>
</head>
<body>
  <!--隐藏的数据-->
  <div id="data" style="display: none">${news.newsTitle}</div>
  <label id="userName" style="display: none"></label>
  <label id="newsId" style="display: none">${news.newsId}</label>
  <!--************************-->

  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <!--主体内容-->
      <div id="main">
        <div id="news">
          <div id="title"></div>
          <div id="newsContent">
            <div id="cc" style="margin-left: 15px; margin-right: 15px; height: auto">
              ${news.content}
            </div>
          </div>
        </div>

        <div id="commentsList">
            <p class="c3">网友评论</p>
            <div id="comments" style="text-align: left; line-height: 30px"> </div>
        </div>

        <div id="sendComments" align="center">
          <p class="c3">发表评论</p>
          <p align="left" style="color: red">请注意文明用语，评论内容只代表网友个人观点，与本网立场无关。管理员有权删除非法或者言语不当的评论！</p>
          <form>
            <textarea id="commentsCont" rows="3" style="width: 100%"></textarea><br><br>
            <center><input type="button"  value="提&nbsp交" onclick="sendComments()"/>&nbsp&nbsp<input type="button"  value="重&nbsp置" onclick="resetComments()" /></center>
          </form>
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
