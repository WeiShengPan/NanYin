<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/24
  Time: 12:13
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
  <link rel="stylesheet" type="text/css" href="/css/briefIntroduction.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title>福建南音网</title>
</head>
<body>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <div id="content">
      <div id="main">
        <div id="introduction">
          <p style="text-align: center; font-size: 16px; font-weight: bold; margin-bottom: 20px;">南音简介</p>
          <p>南音也称“弦管”、“泉州南音”，是中国现存最古老的乐种之一。两汉、晋、唐、两宋等朝代的中原汉族移民把音乐文化带入以泉州为中心的闽南地区，并与当地民间音乐融合，形成了具有中原古乐遗韵的文化表现形式--南音。</p>
          <p>有“中国音乐史上的活化石”之称[2]  ，发源于福建泉州，用泉州闽南语演唱，是中国现存历史最悠久的汉族古乐。南音的唱法保留了唐以前汉族古老的民族唱法，其唱、奏者的二度创作极富随意性。</p>
          <p>“南乐”乃就流传地域而言，“弦管”指南管音乐以丝竹箫弦为主要演奏乐器，古代大多称“弦管”；“郎君乐”、“郎君唱”指的是南管乐者祀奉孟府郎君为乐神。还有称“锦曲”、“五音”等。</p>
          <p>南音起源于前秦，兴於唐，形成在宋，是全中国最古朴的乐种之一，南管的演奏上保持在唐宋时期的特色，例如南管中主导乐器—琵琶，未随时代演进，仍保持唐时的大腹短颈，弹奏上还是用横抱拨弹。其音乐主要由「指」、「谱」、「曲」三大类组成，是中国古代音乐比较丰富、完整的一个大乐种，是「人类口头及非物质遗产代表作」。</p>
          <p>2006年5月20日，经国务院批准列入第一批国家级非物质文化遗产名录。</p>
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
