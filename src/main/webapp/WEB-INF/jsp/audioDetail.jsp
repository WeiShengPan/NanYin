<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/12
  Time: 12:50
  Page:音频详情页面
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
  <script type="text/javascript" language="javascript" src="/Js/niftyplayer.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/audioDetail.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title>南音网--音频列表</title>
  <script type="text/javascript">
    var size=0;
    var commentsDate="";
    $(document).ready(function(){

      if(${audio.flag==0}){
        $("#musicPlayer").css("display","none");
        $("#hideCont").css("display","block");
      }

      $.ajax({
        type:'POST',
        url:'comments.do?method=listAudioComments',
        data:{
          audioId:$("#audioId").text(),
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
            url:'comments.do?method=sendAudioComments',
            data:{
              audioId:$("#audioId").text(),
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
  <label id="userName" style="display: none"></label>
  <label id="audioId" style="display: none">${audio.id}</label>

  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <!--主体内容-->
      <div id="main">
        <div id="audio">
          <div id="audioTitle">
            <p style="font-size: 16px;font-weight: bolder">${audio.title}</p>
            <p>演唱者:${audio.singer}&nbsp&nbsp演奏者:${audio.player}&nbsp&nbsp
              制作:${audio.producer}&nbsp&nbsp观看:${audio.hits}次 &nbsp&nbsp上传时间:${audio.date} </p>
          </div>
          <div id="hideCont" style="width:98%;display: none; margin: 80px auto; text-align: center; line-height: 30px; font-size: 15px; color: red"><p>本音频只允许会员观看，请先登录会员，再次刷新本页面观看，谢谢！</p></div>
          <div id="audioContent">
            <div id="musicPlayer">
              <object width="164" height="37" id="niftyPlayer1" align="left">
                <param name=movie value="/Js/niftyplayer.swf?file=${audio.path}&as=0">
                <param name=quality value=high>
                <embed src="/Js/niftyplayer.swf?file=${audio.path}&as=0"  quality=high  width="164px" height="37px" name="niftyPlayer1" align="" type="application/x-shockwave-flash" swLiveConnect="true" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>
              </object>
            </div>
            <div id="tab">
              <!--代码部分begin-->
              <div id="menu2">
                <!--tag标题-->
                <ul id="nav">
                  <li><a href="javascript:void(0)" class="selected">说明</a></li>
                  <li><a href="javascript:void(0)" class="">工尺谱</a></li>
                  <li><a href="javascript:void(0)" class="">简谱</a></li>
                </ul>
                <!--二级菜单-->
                <div id="menu_con">
                  <div class="tag" style="display:block">
                    ${audio.content}
                  </div>
                  <div class="tag" style="display:none">
                    ${audio.gcp}
                  </div>
                  <div class="tag"  style="display:none">
                    ${audio.jp}
                  </div>
                </div>
              </div>
              <script>
                var tabs=function(){
                  function tag(name,elem){
                    return (elem||document).getElementsByTagName(name);
                  }
                  //获得相应ID的元素
                  function id(name){
                    return document.getElementById(name);
                  }
                  function first(elem){
                    elem=elem.firstChild;
                    return elem&&elem.nodeType==1? elem:next(elem);
                  }
                  function next(elem){
                    do{
                      elem=elem.nextSibling;
                    }while(
                    elem&&elem.nodeType!=1
                            )
                    return elem;
                  }
                  return {
                    set:function(elemId,tabId){
                      var elem=tag("li",id(elemId));
                      var tabs=tag("div",id(tabId));
                      var listNum=elem.length;
                      var tabNum=tabs.length;
                      for(var i=0;i<listNum;i++){
                        elem[i].onclick=(function(i){
                          return function(){
                            for(var j=0;j<tabNum;j++){
                              if(i==j){
                                tabs[j].style.display="block";
                                //alert(elem[j].firstChild);
                                elem[j].firstChild.className="selected";
                              }
                              else{
                                tabs[j].style.display="none";
                                elem[j].firstChild.className="";
                              }
                            }
                          }
                        })(i)
                      }
                    }
                  }
                }();
                tabs.set("nav","menu_con");//执行
              </script>
              <!--代码部分end-->
            </div>

          </div>
        </div>
        <div id="commentsList">
          <p class="c3">网友评论</p>
          <div id="comments">
            <div style="height: 50px"></div>
          </div>
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
