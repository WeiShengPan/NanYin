<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/10
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="/Js/jquery-1.4.4.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/css/userCenter.css"/>
    <title>南音网--会员中心</title>
  <script type="text/javascript">

    var dataSet;


    function alterUser(){
      $("#userInfo").empty();
      var text='<form id="userForm">'+
                '<table align="center">'+
                '<tr>'+
                   '<td style="width: 20%">用户名:</td><td style="width: 40%"><input id="userName" type="text" name="userName" disabled="true" readOnly="true" value="${loginUser.userName}" /></td><td class="remarks" style="width:40% "></td>'+
                '</tr>'+
                '<tr>'+
                   '<td>姓&nbsp&nbsp名:</td><td><input id="name" type="text" name="name" value="${loginUser.name}"/></td><td></td>'+
                '</tr>'+
                '<tr>'+
                  '<td>联系电话:</td><td><input id="tel" type="text" name="tel" value="${loginUser.tel}"/></td><td class="remarks">至少填写电话或者邮箱中的一个</td>'+
                '</tr>'+
                '<tr>'+
                  '<td>邮箱地址:</td><td><input id="email" name="email" type="text" value="${loginUser.email}"/></td><td class="remarks">至少填写电话或者邮箱中的一个</td>'+
                '</tr>'+
                '<tr>'+
                   '<td>密保问题:</td><td><input id="question" type="text" name="question" value="${loginUser.question}"/></td><td  class="remarks">建议设置密保问题，用于找回密码</td>'+
                '</tr>'+
                '<tr>'+
                   '<td>密保答案:</td><td><input id="answer" type="text" name="answer" value="${loginUser.answer}"/></td><td></td>'+
                 '</tr>'+
                '<tr id="add"><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td></tr>'+
                '<tr>'+
                   '<td></td><td><input type="button" value="修&nbsp&nbsp改" onclick="alterUserInfo()"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="button" value="重&nbsp&nbsp置" onclick="resetUser()"/></td><td></td>'+
                 '</tr>'+
                 '</table>'+
                 '</form>';
      $("#userInfo").append(text);
    }

    function alterPsw(){
      $("#userInfo").empty();
      var text='<form>'+
                '<table>'+
                '<tr>'+
                  '<td style="width: 20%">旧密码:</td><td style="width: 40%"><input id="oldPsw" type="text" name="oldPsw" /></td><td class="remarks" style="width:40% "></td>'+
                '</tr>'+
                '<tr>'+
                  '<td>新密码:</td><td style="width: 40%"><input id="newPsw" type="text" name="newPsw" /></td><td class="remarks">密码至少6位字符</td>'+
                '</tr>'+
                '<tr>'+
                   '<td>确认密码:</td><td style="width: 40%"><input id="reNewPsw" type="text" name="reNewPsw" /></td><td class="remarks"></td>'+
                '</tr>'+
                    '<tr id="add"><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td>' +
                '</tr>'+
                '<tr>'+
                  '<td></td><td><input type="button" value="修&nbsp&nbsp改" onclick="alterPswInfo()"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="button" value="重&nbsp&nbsp置" onclick="resetPsw()"/></td><td></td>'+
                '</tr>'+
                '</table>'+
                '</form>';
      $("#userInfo").append(text);

    }

    function upgrade(){
      $("#userInfo").empty();
      var text="";
      if(${loginUser.level==true}&&${loginUser.state==true}){
        text+='<br><br><br><p style="color: red;font-size: large">您已经是高级会员了，不需要再升级了！</p>';
      } else if(${loginUser.level==true}&&${loginUser.state==false} ){
        text+='<br><br><br><p style="color: red;font-size: large">您已经是高级会员了，但还没完成付费，请及时缴费，否则将自动降级为普通会员。</p>';
      }else{
        text+='<br><br><br><p style="color: red;font-size: large; text-align: left; margin-left: 20px; margin-right: 20px; line-height: 30px">升级为高级会员，必须在24小时内，完成付费！付费方式如下：支付宝账号：1234567876，微信账号：98423443，银行卡账号：73263248932423234234。完成付费之后' +
                '请把自己的用户名发送到号码为：18805928081的手机上，我们将尽快为您升级。谢谢！</p>';
      }
      $("#userInfo").append(text);

    }

    function resetUser(){
      $("#userInfo form input[name=name]").val("");
      $("#userInfo form input[name=tel]").val("");
      $("#userInfo form input[name=email]").val("");
      $("#userInfo form input[name=question]").val("");
      $("#userInfo form input[name=answer]").val("");
    }

    function resetPsw(){
      $("#userInfo form input[name=oldPsw]").val("");
      $("#userInfo form input[name=newPsw]").val("");
      $("#userInfo form input[name=reNewPsw]").val("");
    }

    function alterUserInfo(){

      if ($("#tel").val() == "" && $("#email").val() == "") {
        alert("至少要填写电话或者邮箱中的一个信息！");
        return;
      }
      if ($("#tel").val() != "") {
        var tel = $("#tel").val();
        var re = /^1\d{10}$/;
        if (!re.test(tel)) {
          alert("手机号码错误！");
          return;
        }
      }
      if ($("#email").val() != "") {
        var email = $("#email").val();
        var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
        if (!re.test(email)) {
          alert("邮箱格式错误！");
          return;
        }
      };
      if($("#question").val()!=""&&$("#answer").val()==""){
        alert("请输入密保答案！");
        return;
      }
      if($("#question").val()==""&&$("#answer").val()!=""){
        alert("请输入密保问题！");
        return;
      }
      $.ajax({
        type:'POST',
        url:'register.do?method=alterUserInfo',
        data:$("#userForm").serialize(),
        error:function(){
          alert("修改失败！");
        },
        success:function(){
          alert("修改成功！");
          window.location.reload();
        }
      });
    }

    function alterPswInfo(){

      if($("#oldPsw").val()!=$("#psw").text()){
        alert("输入的旧密码有误，请重新输入！");
        return;
      }

      if($("#oldPsw").val()==""){
        alert("请输入旧密码！");
        return;
      }
      if($("#newPsw").val()==""){
        alert("请输入新密码！");
        return;
      }
      if (!($("#newPsw").val().length >= 6)) {
        alert("密码至少为6个字符！");
        return;
      }
      if($("#reNewPsw").val()==""){
        alert("请输入确认密码！");
        return;
      }
      if($("#newPsw").val()!=$("#reNewPsw").val()){
        alert("新密码和确认密码不相符，请重新输入！");
        return;
      }

      $.ajax({
        type:'POST',
        url:'register.do?method=alterPsw',
        data:{
          newPsw:$("#newPsw").val(),
        },
        error:function(){
          alert("更改密码失败！");
        },
        success:function(){
          alert("更改密码成功");
          window.location.reload();
        }
      });
    }

    function myMessage(){
      $("#userInfo").empty();
      $.ajax({
        type:'POST',
        url:'register.do?method=getMessage',
        data:{
          userId:${loginUser.id},
        },
        error:function(){
          alert("获取数据失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          if(result==""){
            var text='<br><br><br><p><h2>到目前为止，您还没有信息!</h2></p>';
            $("#userInfo").append(text);
            return;
          }

          dataSet=result;

          var add1='<p style="margin-bottom: 30px; font-size: large">总共有（'+result.length+'）条信息，其中有(<label id="num"></label>)条未读</p>';
          var add2='<table id="messageList" align="center"></table>';
          $("#userInfo").append(add1);
          $("#userInfo").append(add2);

          var num=0;
          for(var i=0;!(i >= result.length);i++){
            if(result[i].state){
              var text='<tr><td><p style="line-height: 30px; text-align: left"><a id="a'+result[i].id+'"  href="javascript:void(0)"  onclick="clickLink('+result[i].id+','+i+')">'+(i+1)+'.'+result[i].title+'</a></p></td><td>'+result[i].date+'</td> <td> <a style="color: black" href="javascript:void(0)" onclick="deleteMessage('+result[i].id+')"> 删除</a></td></tr>';
            }else{
              var text='<tr><td><p style="line-height: 30px;text-align: left"><a id="a'+result[i].id+'" style="color:red"  href="javascript:void(0)" onclick="clickLink('+result[i].id+','+i+')">'+(i+1)+'.'+result[i].title+'<label id="p'+result[i].id+'">(未读)</label></a></p></td><td>'+result[i].date+'</td><td><a style="color: black" href="javascript:void(0)" onclick="deleteMessage('+result[i].id+')">删除</a></td></tr>';
              num=num+1;
            }
            $("#num").text(num);
            $("#userInfo #messageList").append(text);
          }
        }
      });
    }

    function clickLink( id,c ){

      var content='<br><br><br><p style="font-size: large; margin-left: 20px; margin-right: 20px; text-align: left">'+dataSet[c].content+'</p>';
      $("#userInfo").empty();
      $("#userInfo").append(content);

      if(!dataSet[c].state){
        $("#a"+id).css("color" ,"black")
        $("#p"+id).empty();

        $.ajax({
          type:'POST',
          url:'register.do?method=updateMessage',
          data:{
            id:id,
          }
        });
      }
    }

    function deleteMessage(id){

      $.ajax({
        type:'POST',
        url:'register.do?method=deleteMessage',
        data:{
          id:id,
        },
        success:function(){
          myMessage();
        }
      });
    }

  </script>
</head>
<body>
  <label id="psw" style="display: none">${loginUser.password}</label>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <div id="main">
        <div id="title"><h2>尊敬的${loginUser.level==false?"普通会员:":"高级会员:"}${loginUser.userName},您好，欢迎来到南音网！</h2></div>
        <div id="left">
          <ul>
            <li><a href="javascript:void(0)" onclick="alterUser()">修改个人资料</a> </li>
            <li><a href="javascript:void(0)" onclick="alterPsw()">修改密码</a></li>
            <li><a href="javascript:void(0)" onclick="myMessage()">我的信息</a></li>
            <li><a href="javascript:void(0)" onclick="upgrade()">升级为高级会员</a></li>
          </ul>
        </div>
        <div id="right">
          <div id="userInfo">

          </div>
        </div>
      </div>
    </div>
    <!--引入网页底部-->
    <%@include file="footer.jsp"%>
  </div>
</body>
</html>
