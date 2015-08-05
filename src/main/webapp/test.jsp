<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/7/29
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="Js/jquery-1.4.4.min.js"></script>
  <link rel="stylesheet" type="text/css" href="css/test.css"/>
  <title>测试</title>

  <script type="text/javascript">
    $(document).ready(function(){
      $.ajax({
        type:'POST',
        url:'index.do',
        error:function(){
          alert("sorry,the web application have some errors!");
        },
        success:function(data){
          alert(data);
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#ny"+(i+1)).find("a").text("["+result[i].type+"]"+" "+result[i].title);
            $("#ny"+(i+1)).find("a").attr("href","news.do?id="+result[i].id+"");
          }
        }
      });
    });
  </script>
</head>
<body>
<div id="container">
<ul>
    <li id="ny1"><a href=""></a></li>
    <li id="ny2"><a href=""></a></li>
    <li id="ny3"><a href=""></a></li>
    <li id="ny4"><a href=""></a></li>
    <li id="ny5"><a href=""></a></li>
  </ul>
  </div>
</body>
</html>

