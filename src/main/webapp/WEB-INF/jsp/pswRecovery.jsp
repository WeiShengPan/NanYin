<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/9
  Time: 18:00
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
  <link rel="stylesheet" type="text/css" href="/css/pswRecovery.css"/>
    <title>南音网--找回密码</title>
  <script type="text/javascript">
    function find(){
      var userName=$("#userName").val();
      if(userName==""){
        alert("请输入用户名!");
        return;
      }

      $.ajax({
        type:'POST',
        url:'findPsw.do?method=findPsw',
        data:{
          userName:userName,
        },
        error:function(){
          alert("抱歉，查找失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          if(result==null){
            alert("对不起，没有该用户!");
            return;
          }
          if(result.question!=null){
            $("#detail").empty();
            var text='<table align="center">'+
                    '<tr align="left"><td>您注册会员时设置的问题为:'+result.question+'</td></tr>'+
                    '<tr align="left"><td>请问答该问题(注意:你有6次回答的机会)</td></tr>'+
                    '<tr><td><textarea id="answer" rows="3" cols="43"></textarea></td></tr>'+
                    '<tr><td><br><input type="button" value="提&nbsp&nbsp交" onclick="send()"/>&nbsp&nbsp&nbsp&nbsp<input type="button" value="重&nbsp&nbsp置" onclick="reset()"/></td></tr>'+
                    '<tr><td id="reply" style="color: red">  </td></tr>'
                    '</table>';
            $("#detail").append(text);
          }else{
            if(result.flag==1){
              $("#detail").empty();
              var text='<br><br><p>您回答的次数太多了，我们将尽快把密码以短信或邮件的方式发送给您，请注意查收！</p>';
              $("#detail").append(text);
              return;
            }
            $("#detail").empty();
            var text='<br><br><p>由于您注册会员时没有设置密保问题，我们将尽快把密码发到您的邮箱或者手机上，请注意查收！</p>';
            $("#detail").append(text);
          }
        }
      });
    }

    function reset(){
      $("#answer").val("");
    }


    function send(){
      var ans=$.trim( $("#answer").val());
      if(ans==""){
        alert("请输入答案！");
        return;
      }
      $.ajax({
        type:'POST',
        url:'findPsw.do?method=sendAns',
        data:{
          answer: ans,
        },
        error:function(){
          alert("发送失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          var flag=result.flag;
          if(flag=="get"){
            $("#reply").empty();
            var text='<br><br><p>您的密码为:'+result.psw+'</p>';
            $("#reply").append(text);
            $("#answer").val("");
          }else if(flag=="miss"){
            $("#reply").empty();
            var text='<br><br><p>答案错误，请重新回答！</p>';
            $("#reply").append(text);
            $("#answer").val("");
          }else{
            $("#detail").empty();
            var text='<br><br><p>您回答的次数太多了，我们将尽快把密码以短信或邮件的方式发送给您，请注意查收！</p>';
            $("#detail").append(text);
          }
        }
      });
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
         <diva id="userInfo">
           <label>请输入用户名:</label>&nbsp<input id="userName" type="text" name="userName"/>&nbsp&nbsp<input type="button" value="查&nbsp&nbsp找" onclick="find()"/>
         </diva>
        <div id="detail" align="center">

        </div>
      </div>
    </div>
    <!--引入网页底部-->
    <%@include file="footer.jsp"%>
  </div>

</body>
</html>
