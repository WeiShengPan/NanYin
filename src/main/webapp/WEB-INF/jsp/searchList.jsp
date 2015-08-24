<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/8/21
  Time: 16:55
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
  <link rel="stylesheet" type="text/css" href="/css/searchList.css"/>
  <%@include file="loginTemplate.jsp"%>
    <title>福建南音网</title>
  <script type="text/javascript">
    $().ready(function (){
      var dataList=$("#data").text();
      var result= $.parseJSON(dataList);
      if(result==""){
        $("#main").css("display","none");
        alert("抱歉，没有您找的内容！");
        return;
      }
      $("#main").css("display","block");
      var type=$("#type").text();
      var link="";
      if(type==1){
        link+="libraryLink";
      }else if(type==2){
        link+="videoLink";
      }else{
        link+="audioLink";
      }
      for(var i=0;!(i >=result.length);i++){
        var tr='<tr><td><a href="index.do?method='+link+'&id='+result[i].id+'">'+result[i].title+'</a></td> '+
                '<td>'+result[i].date+'</td><td>'+result[i].hits+'</td></tr>';
        $("#main table tbody").append(tr);
      }
    });
  </script>
</head>
<body>
  <label id="data" style="display: none">${data.dataList}</label>
  <label id="type" style="display: none">${data.type}</label>
  <div id="container">
    <!--引入网页头部-->
    <%@include file="header.jsp"%>
    <div id="content">
      <div id="main" align="center">
        <table align="center">
          <thead>
          <tr class="title">
            <td style="width: 40%">标题</td>
            <td style="width: 30%">上传日期</td>
            <td style="width: 30%">点击量</td>
          </tr>
          </thead>
          <tbody></tbody>
        </table>
        </div>
      </div>
    <!--引入网页底部-->
    <%@include file="footer.jsp"%>
  </div>
</body>
</html>
