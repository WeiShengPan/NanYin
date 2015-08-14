<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/7
  Time: 18:25
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
  <link rel="stylesheet" type="text/css" href="/css/register2.css"/>
    <title>会员注册</title>
  <script type="text/javascript">
    var level;
    $().ready(function(){
      level=${type};
      if(level==1){
        $("#title h2").text("普通会员注册");
      }else{
        $("#title h2").text("高级会员注册");
        var text='<tr><td colspan="3" style="color: red;text-align: left"> 付费方式：(1)支付宝账号：12348668932.(2)微信账号：nanyinwang.(3)银行卡账号：7832489239038423432.'+
                '请您在一天之内完成付费，以便享受高级会员的服务。否则此注册的账号将自动降级为普通会员。</td></tr>';
        $("#add").before(text);
      }
    });

    function register() {
      var flag=1;

      if ($("#userName").val() == "") {
        alert("用户名不能为空！");
        return;
      }
      if ($("#psw").val() == "") {
        alert("密码不能为空！");
        return;
      }
      if (!($("#psw").val().length >= 6)) {
        alert("密码至少为6个字符！");
        return;
      }
      if ($("#psw").val() != $("#repsw").val()) {
        alert("密码和确认密码不相符!");
        return;
      }
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
        url:'register.do?method=verification',
        async:false,
        data:{
          userName:$("#userForm #userName").val(),
        },
        success:function(data){
          if(data=="fail"){
            alert("抱歉，该用户名已经存在!");
            flag=0;
          }
        }
      });

    if(flag==1){
      $.ajax({
        type:'POST',
        url:'register.do?method=register&type='+level+'',
        data:$("#userForm").serialize(),
        error:function(){
          alert("注册失败！");
        },
        success:function(){
          if(level==1){
            alert("注册成功,请登录会员用户！");
          }else{
            alert("注册成功，请在24小时内完成付费，否则将自动降级为普通用户！");
          }
          window.location="index.jsp";
        }
      });
    }

    }

    function reset(){
      $("#userForm").form('clear');
    }


  </script>
</head>
<body>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <!--网页内容-->
    <div id="content">
      <div id="title"><h2></h2></div><br>
      <div id="userInfo" align="center">
        <form id="userForm">
          <table align="center">
            <tr>
              <td style="width: 20%">用户名:</td><td style="width: 40%"><input id="userName" type="text" name="userName" /></td><td class="remarks" style="width:40% ">必须填写</td>
            </tr>
            <tr>
              <td>密&nbsp&nbsp码:</td><td><input id="psw" type="password" name="psw" maxlength="20"/></td><td class="remarks">必须填写，至少6位字符</td>
            </tr>
            <tr>
              <td>密码确认:</td><td><input id="repsw" type="password" name="repsw"  maxlength="20"/></td><td class="remarks">必须填写</td>
            </tr>
            <tr>
              <td>姓&nbsp&nbsp名:</td><td><input id="name" type="text" name="name"/></td><td></td>
            </tr>
            <tr>
              <td>性&nbsp&nbsp别:</td><td><input type="radio" name="gender" value="1" checked/><label>男</label>&nbsp&nbsp&nbsp&nbsp<input type="radio" name="gender" value="0"/><label>女</label></td><td class="remarks">必须填写</td>
            </tr>
            <tr>
              <td>联系电话:</td><td><input id="tel" type="text" name="tel"/></td><td class="remarks">至少填写电话或者邮箱中的一个</td>
            </tr>
            <tr>
              <td>邮箱地址:</td><td><input id="email" name="email" type="text"/></td><td class="remarks">至少填写电话或者邮箱中的一个</td>
            </tr>
            <tr>
              <td>密保问题:</td><td><input id="question" type="text" name="question"/></td><td class="remarks">建议填写，用于找回密码</td>
            </tr>
            <tr>
              <td>密保答案:</td><td><input id="answer" type="text" name="answer"/></td><td></td>
            </tr>
            <tr id="add"><td>&nbsp</td><td>&nbsp</td><td>&nbsp</td></tr>
            <tr>
              <td></td><td><input type="button" value="提&nbsp&nbsp交" onclick="register()"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="button" value="重&nbsp&nbsp置" onclick="reset()"/></td><td></td>
            </tr>
          </table>
        </form>
      </div>
    </div>
    <!--引入网页底部-->
    <%@include file="footer.jsp"%>
  </div>

</body>
</html>
