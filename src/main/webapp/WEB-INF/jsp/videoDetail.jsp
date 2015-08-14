<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/11
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="/Js/jquery-1.4.4.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/videoDetail.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title>南音视频</title>
  <script type="text/javascript">
    var size=0;
    var commentsDate="";
    $(document).ready(function(){
      $.ajax({
        type:'POST',
        url:'comments.do?method=listVideoComments',
        data:{
          videoId:$("#videoId").text(),
        },
        success:function(data){
          var result= $.parseJSON(data);
          if(result==""){
            $("#comments").empty();
            $("#comments").append("<p style='text-align: center; margin-top: 15px; margin-bottom: 15px;'>暂无评论</p>");
            return;
          }
          commentsDate=result;
          size=result.length;
          $("#comments").empty();
          if(!(size>5)){
            for(var i=0;!(i>=result.length);i++){
              var text='<p class="c3">'+result[i].userName+'&nbsp('+result[i].date+')</p>' +
                      '<p>'+result[i].content+'</p>';
              $("#comments").append(text);
            }
          }else{
            for(var i=0;!(i>=5);i++){
              var text='<p class="c3">'+result[i].userName+'&nbsp('+result[i].date+')</p>' +
                      '<p>'+result[i].content+'</p>';
              $("#comments").append(text);
            }
            var link='<hr></hr><p align="center"><a href="javascript:void(0)" onclick="moreComm()">更多评论,点击查看>></a></p><hr></hr>';
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
            url:'comments.do?method=sendVideoComments',
            data:{
              videoId:$("#videoId").text(),
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
        var text='<p class="c3">'+commentsDate[i].userName+'&nbsp('+commentsDate[i].date+')</p>' +
                '<p>'+commentsDate[i].content+'</p>';
        $("#comments").append(text);
      }
    }

  </script>
</head>
<body>
  <label id="userName" style="display: none"></label>
  <label id="videoId" style="display: none">${video.id}</label>

  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <!--主体内容-->
      <div id="main">
        <div id="video">
          <div id="videoTitle">
            <p><h2>${video.title}</h2></p>
            <p>演唱者:${video.singer}&nbsp&nbsp演奏者:${video.player}&nbsp&nbsp
              摄影:${video.cameraman}&nbsp&nbsp 制作:${video.producer}&nbsp&nbsp
              观看:${video.hits}次 &nbsp&nbsp上传时间:${video.date} </p>
          </div>
          <div id="videoContent">
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
              so.addVariable("file","${video.path}");//视频地址
              so.addVariable("img","/images/logo.jpg");//视频图片
              so.addVariable("autoplay","false");//是否自动播放
              so.write("CuPlayer");
            </script>
            <!-- 我爱播放器(52player.com)/代码结束 -->
          </div>
        </div>

        <div id="commentsList">
            <h2>网友评论</h2>
            <div id="comments">
              <div style="height: 50px"></div>
            </div>
        </div>

        <div id="sendComments" align="center">
          <h2>发表评论</h2>
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
