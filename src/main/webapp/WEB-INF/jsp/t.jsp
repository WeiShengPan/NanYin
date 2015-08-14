<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>ckplayer简单调用演示</title>

</head>

<body>

<%--##    ckplayer实现播放    视频如果格式不对需要转码，视频格式应该是h.264编码。音频编码是AAC  不然会出现黑屏有声音无画面--%>
<div id="video" style="position:relative;z-index: 100;width:600px;height:400px;float: left;"><div id="a1"></div></div>

<script type="text/javascript" src="ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
  var flashvars={
    f:'/upload/1.mp4',
    c:0,
    b:1
  };
  var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always'};
  CKobject.embedSWF('ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);

</script>
<br>



</body>
</html>