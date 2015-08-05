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
  <div id="login" style=" width:98%; height:160px; margin:0 auto; margin-top:10px ">
    <form>
      <fieldset>
        <legend> <h2 class="c2">会员登录</h2></legend>
        <table class="c2"  style=" width:100%; height:120px; margin-top:15px">
          <tr><td align="right">用户名:</td><td><input type="text" name="user" width="20px" size="10" maxlength="10"/></td></tr>
          <tr><td align="right">密 码:</td><td><input type="password" name="password" size="10" maxlength="10"/></td></tr>
          <tr><td></td><td><a href="#">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">重置</a></td></tr>
          <tr><td></td><td><a href="#">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">忘记密码</a></td></tr>
        </table>
      </fieldset>
    </form>
  </div>
  <!--公告-->
  <div id="announcement">
    <center> <h2>公告</h2></center>
    <div id="scrollDiv" align="center">
      <ul class="c2">
        <li><a href="#">这是公告标题的第一行</a></li>
        <li><a href="#">这是公告标题的第二行</a></li>
        <li><a href="#">这是公告标题的第三行</a></li>
        <li><a href="#">这是公告标题的第四行</a></li>
        <li><a href="#">这是公告标题的第五行</a></li>
        <li><a href="#">这是公告标题的第六行</a></li>
        <li><a href="#">这是公告标题的第七行</a></li>
        <li><a href="#">这是公告标题的第八行</a></li>
      </ul>
    </div><br />
    <p class="c1" align="right" style=" margin-right:10px"><a href="#"> >> 更 多 </a></p>
  </div>
  <!--投稿-->
  <div id="submission">
    <h2>欢迎投稿</h2>
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
    <h2>扫一扫，关注南音</h2>
    <p>微信二维码:</p>
    <img src="images/weixin.jpg" alt="微信" /><br /><br /><br />
    <p>微博二维码:</p>
    <img src="images/weibo.jpg" alt="微博"/>
  </div>
</div>