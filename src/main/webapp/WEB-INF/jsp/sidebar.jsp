<%@ page import="cn.nanyin.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/7/31
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/Js/announcement.js"></script>
<link rel="stylesheet" type="text/css" href="/css/sidebar.css">

<!--右边的侧栏-->
<div id="sidebar">
  <!--登录-->
  <div id="login" style=" width:98%; height:160px; margin:10px auto;border-bottom: #CCC 1px dashed ">
    <p class="c3" align="center">会员登录</p>
    <form id="loginForm">
      <div id="loginField">
        <table id="loginTab" class="c2"  style=" width:100%; height:120px; margin-top:10px">
          <tr><td align="right">用户名:</td><td><input id="user" type="text" name="user"style="width: 100px"  size="20" maxlength="20"/></td></tr>
          <tr><td align="right">密&nbsp&nbsp码:</td><td><input id="psw" type="password"  name="password" style="width: 100px" size="20" maxlength="20"/></td></tr>
          <tr><td></td><td><a href="javascript:void(0)" onclick="Login()">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="Reset()">重置</a></td></tr>
          <tr><td></td><td><a href="index.do?method=register&type=0">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.do?method=pswRecovery">忘记密码</a></td></tr>
        </table>
      </div>
    </form>
  </div>
  <!--公告-->
  <div id="announcement">
    <center><p class="c3">公告</p></center>
    <div id="scrollDiv" align="center">
      <ul class="c2">
        <li id="announcement0"><a href="">这是公告标题的第一行</a></li>
        <li id="announcement1"><a href="">这是公告标题的第二行</a></li>
        <li id="announcement2"><a href="">这是公告标题的第三行</a></li>
        <li id="announcement3"><a href="">这是公告标题的第四行</a></li>
        <li id="announcement4"><a href="">这是公告标题的第五行</a></li>
      </ul>
    </div><br />
    <p class="c1" align="right" style=" margin-right:10px"><a href="#"> >> 更 多 </a></p>
  </div>
  <!--投稿-->
  <div id="submission">
    <p class="c3">欢迎投稿</p>
    <p class="c2"><a href="#">点击进入</a></p>
  </div>
  <!--其他-->
  <div id="other">
    <ul class="c2">
      <li><a href="#">古文朗诵</a></li>
      <li><a href="#">诗词吟唱</a></li>
      <li><a href="#">闽台文化</a></li>
      <li><a href="#">生活智慧</a></li>
      <li><a href="#">健康养生</a></li>
    </ul>
  </div>
  <!--二维码-->
  <div id="QRcode" class="c2">
    <p class="c3">扫一扫，关注南音</p>
    <p>微信二维码:</p>
    <img src="images/weixin.jpg" alt="微信" /><br /><br /><br />
    <p>微博二维码:</p>
    <img src="images/weibo.jpg" alt="微博"/>
  </div>
</div>