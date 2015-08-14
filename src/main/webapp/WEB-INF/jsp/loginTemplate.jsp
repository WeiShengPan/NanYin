
<%@ page import="cn.nanyin.model.User1" %>
<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/3
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%
  User1 loginUser=(User1)session.getAttribute("loginUser");
  String userName="";
  boolean level=false;
  if(loginUser!=null){
    userName+=loginUser.getUserName();
    level=loginUser.getLevel();
  }
%>
<script type="text/javascript" src="/Js/login.js"></script>

<label id="flag" style="display: none"><%=userName%></label>
<label id="level" style="display: none"><%=level%></label>
