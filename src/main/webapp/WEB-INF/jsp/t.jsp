
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>ckplayer简单调用演示</title>

</head>

<body>

<%--##    ckplayer实现播放--%>
<div id="video" style="position:relative;z-index: 100;width:600px;height:400px;float: left;"><div id="a1"></div></div>

<script type="text/javascript" src="ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
  var flashvars={
    f:'upload/media/video/2015/08/05/77cdba54-9325-4893-9ba6-facf592d2af6.mp4',
    c:0,
    b:1
  };
  var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always'};
  CKobject.embedSWF('ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);
  /*
   CKobject.embedSWF(播放器路径,容器id,播放器id/name,播放器宽,播放器高,flashvars的值,其它定义也可省略);
   下面三行是调用html5播放器用到的
   */
  var video=['http://movie.ks.js.cn/flv/other/1_0.mp4->video/mp4','http://www.ckplayer.com/webm/0.webm->video/webm','http://www.ckplayer.com/webm/0.ogv->video/ogg'];
  var support=['iPad','iPhone','ios','android+false','msie10+false'];
  CKobject.embedHTML5('video','ckplayer_a1',600,400,video,flashvars,support);
</script>
<br>


<%--##    html5标签实现播放，有些版本低的浏览器不支持--%>
<video controls="controls">
  <source src="upload/media/video/2015/08/05/77cdba54-9325-4893-9ba6-facf592d2af6.mp4">
</video>

<audio controls="controls">
  <source src="upload/media/video/2015/08/05/77cdba54-9325-4893-9ba6-facf592d2af6.mp4">
</audio>

</body>
</html>