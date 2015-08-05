<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/7/29
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="Js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" language="JavaScript" src="Js/bigpicroll.js"></script>
  <script type="text/javascript" language="JavaScript" src="Js/announcement.js"></script>
  <link rel="stylesheet" type="text/css" href="css/index.css" />
  <title>福建南音网</title>

  <script type="text/javascript">
    $(document).ready(function(){
      /******************南音新闻*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=news',
        error:function(){
          alert("sorry,the web application have some errors!");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#news"+i).find("a").text("["+result[i].type+"]"+" "+result[i].title);
            $("#news"+i).find("a").attr("href","index.do?method=newsLink&id="+result[i].id+"");
          }
        }
      });
    });
  </script>

</head>
<body>
  <div id="container">
    <!--头部-->
    <div id="header">
      <embed src="images/2.swf" width="1024px" height="176px"></embed>
    </div>
    <!--导航菜单-->
    <div id="menu">
      <ul>
        <li><a href="index.jsp">首  页</a></li>
        <li>
          <a href="index.do?method=dispatcher&page=newsList">南音新闻</a>
          <ul>
            <li><a href="#">南音快讯</a></li>
            <li><a href="#">海外南音</a></li>
            <li><a href="#">南音专题</a></li>
            <li><a href="#">南音人物</a></li>
            <li><a href="#">南音转载</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音乐理</a>
          <ul>
            <li><a href="#">南音常识</a></li>
            <li><a href="#">工乂谱简介</a></li>
            <li><a href="#">南音乐器</a></li>
            <li><a href="#">南音唱腔</a></li>
            <li><a href="#">滚门曲牌</a></li>
            <li><a href="#">南音本事</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音文库</a>
          <ul>
            <li><a href="#">南音曲词</a></li>
            <li><a href="#">南音论文</a></li>
            <li><a href="#">南音钩沉</a></li>
            <li><a href="#">南音文萃</a></li>
            <li><a href="#">南音轶事</a></li>
            <li><a href="#">诗词楹联</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音图谱</a>
          <ul>
            <li><a href="#">南音工乂谱</a></li>
            <li><a href="#">南音简谱影</a></li>
            <li><a href="#">电子相册</a></li>
            <li><a href="#">专题剪影</a></li>
            <li><a href="#">珍贵图录</a></li>
            <li><a href="#">名家题词</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音曲库</a>
          <ul>
            <li><a href="#">南音.谱</a></li>
            <li><a href="#">南音.指</a></li>
            <li><a href="#">南音.曲</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音视频</a>
          <ul>
            <li><a href="#">经典视频</a></li>
            <li><a href="#">南音专辑</a></li>
            <li><a href="#">社团视频</a></li>
            <li><a href="#">南音会唱</a></li>
            <li><a href="#">南音比赛</a></li>
            <li><a href="#">南音网庆</a></li>
            <li><a href="#">其他视频</a></li>
          </ul>
        </li>
        <li>
          <a href="#">诗词音画</a>
          <ul>
            <li><a href="#">唐诗宋词南管唱</a></li>
            <li><a href="#">丁马成南音作品</a></li>
            <li><a href="#">郑梦集茶乡清曲</a></li>
            <li><a href="#">乡土风情南管唱</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音教学</a>
          <ul>
            <li><a href="#">工乂谱识谱</a></li>
            <li><a href="#">乐器技法</a></li>
            <li><a href="#">唱腔指导曲</a></li>
            <li><a href="#">名师访谈唱</a></li>
            <li><a href="#">学习申请</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音名录</a>
          <ul>
            <li><a href="#">南音人物志</a></li>
            <li><a href="#">南音传承人</a></li>
            <li><a href="#">南音新秀榜</a></li>
            <li><a href="#">学术界名录</a></li>
            <li><a href="#">本网机构</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音社团</a>
          <ul>
            <li><a href="#">荣誉社团</a></li>
            <li><a href="#">国内社团</a></li>
            <li><a href="#">港澳台社团</a></li>
            <li><a href="#">东南亚社团</a></li>
            <li><a href="#">传习&培训</a></li>
          </ul>
        </li>
        <li>
          <a href="#">南音商城</a>
          <ul>
            <li><a href="#">南音乐器</a></li>
            <li><a href="#">南音书籍</a></li>
            <li><a href="#">南音音箱 </a></li>
            <li><a href="#">南音伴奏</a></li>
            <li><a href="#">其他配件</a></li>
            <li><a href="#">文创艺品 </a></li>
          </ul>
        </li>
      </ul>
    </div>
    <!--搜索栏-->
    <div id="search">
      <form>
        <table align="right" style="margin-top:5px">
          <tr>
            <td>
              <select>
                <option value="1">影音</option>
                <option value="2">文库</option>
                <option value="3">曲库</option>
              </select>
            </td>
            <td><input type="text" name="text" style="width:200px"/></td>
            <td><input type="submit" name="搜索" value="搜索" style="width:50px" /></td>
          </tr>
        </table>
      </form>
    </div>
  <!--网页主体-->
    <div id="content">
      <!--主体内容-->
      <div id="main">
        <!--南音介绍-->
        <div id="main_1">
          <h2>南音简介</h2>
          <p>南音又称南乐、南曲、南管、弦管、郎君乐等，是我国四大古乐之一，至今有一千多年的历史。清康熙年间，万寿祝典，闽五少芳贤入京御前献奏，赐予"御前清曲"。故南音素有"华夏瑰宝"、"音乐活化石"之美誉，又有"御前清曲"之雅颂。2009年9月，南音被联合国科教文组织列入《人类非物质文化遗产代表作名录》。</p>
          <p>南音的音乐由"指"、"谱"、"曲"三大部分组成，蕴含了晋清商乐、唐大曲、法曲、燕乐和佛教音乐及宋元明以来的词曲音乐、戏曲音乐，保留了唐宋古典曲牌，有着浓厚的中原古乐遗风。</p>
        </div>
        <!--图片切换-->
        <div id="main_2">
          <div class="showpage"  style="margin-top:2px">
            <div  class="flashbox f_list" style="margin:0 auto">
              <div align="center" class="focusNew_out flashlist">
                <div style="display:block;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/1.jpg" border="0"></a>
                </div>
                <div style="display:none;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/2.jpg" border="0"></a>
                </div>
                <div style="display:none;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/3.jpg" border="0"></a>
                </div>
                <div style="display:none;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/4.jpg" border="0"></a>
                </div>
                <div style="display:none;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/5.jpg" border="0"></a>
                </div>
              </div>
              <div class="f_tabs">
                <span class="f_tab hover">1</span>
                <span class="f_tab ">2</span>
                <span class="f_tab ">3</span>
                <span class="f_tab ">4</span>
                <span class="f_tab ">5</span>
              </div>
            </div>
          </div>
          <script type="text/javascript">
            FeatureList(".f_list",{"onclass":"hover","offclass":"","pause_on_act":"click","interval":5000,"speed":5});
          </script>
        </div>
        <!--分隔线-->
        <div class="line"></div>
        <!--南音新闻-->
        <div id="main_3">
          <h2>南音新闻</h2>
          <ul>
            <li id="news0"><a href="">[南音常识]福建南音简介</a></li>
            <li id="news1"><a href="">[南音常识]南音套曲刍议（六）</a></li>
            <li id="news2"><a href="">[工尺谱]南音套曲刍议（五）</a></li>
            <li id="news3"><a href="">[南音乐器]南音套曲刍议（四）</a></li>
            <li id="news4"><a href="">[南音唱腔]南音套曲刍议（三）</a></li>
            <li id="news5"><a href="">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--南音乐理-->
        <div id="main_4">
          <h2>南音乐理</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--南音文库-->
        <div id="main_5">
          <h2>南音文库</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--分隔线-->
        <div class="line"></div>
        <!--南音曲库-->
        <div id="main_6">
          <h2>南音曲库</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--南音视频-->
        <div id="main_7">
          <h2>南音视频</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--南音名录-->
        <div id="main_8">
          <h2>南音名录</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--滚动图片-->
        <div id="main_9">
          <div id="demo" style="overflow:hidden; width:100%; height:150px" align=center>
            <table align="center">
              <tr>
                <td valign=top  id=marquePic1>
                  <table width='100%' cellspacing='1'>
                    <tr>
                      <td align=center><a href='#'><img src="images/01.jpg" width=250 height=150 border="0"></a></td>
                      <td align=center><a href='#'><img src="images/02.jpg" width=250 height=150 border="0"></a></td>
                      <td align=center><a href='#'><img src="images/03.jpg" width=250 height=150 border="0"></a></td>
                      <td align=center><a href='#'><img src="images/04.jpg" width=250 height=150 border="0"></a></td>
                      <td align=center><a href='#'><img src="images/05.jpg" width=250 height=150 border="0"></a></td>
                      <td align=center><a href='#'><img src="images/06.jpg" width=250 height=150 border="0"></a></td>
                      <td align=center><a href='#'><img src="images/07.jpg" width=250 height=150 border="0"></a></td>
                      <td align=center><a href='#'><img src="images/08.jpg" width=250 height=150 border="0"></a></td>
                      <td align=center><a href='#'><img src="images/09.jpg" width=250 height=150 border="0"></a></td>
                    </tr>
                  </table>
                </td>
                <td id=marquePic2 valign=top></td>
              </tr>
            </table>
          </div>
          <script type="text/javascript">
            var speed=50;
            marquePic2.innerHTML=marquePic1.innerHTML;
            function Marquee(){
              if(demo.scrollLeft>=marquePic1.scrollWidth){
                demo.scrollLeft=0;
              }else{
                demo.scrollLeft++;
              }
            }
            var MyMar=setInterval(Marquee,speed);
            demo.onmouseover=function() {clearInterval(MyMar)};
            demo.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
          </script>
        </div>
        <!--南音商城-->
        <div id="main_10">
          <h2>南音商城</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--南音教学-->
        <div id="main_11">
          <h2>南音教学</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--南音社团-->
        <div id="main_12">
          <h2>南音社团</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--分隔线-->
        <div class="line"></div>
        <!--南音文库-->
        <div id="main_13">
          <h2>南音文库</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--南音文库-->
        <div id="main_14">
          <h2>南音文库</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--南音文库-->
        <div id="main_15">
          <h2>南音文库</h2>
          <ul>
            <li><a href="#">[南音常识]福建南音简介</a></li>
            <li><a href="#">[南音常识]南音套曲刍议（六）</a></li>
            <li><a href="#">[工尺谱]南音套曲刍议（五）</a></li>
            <li><a href="#">[南音乐器]南音套曲刍议（四）</a></li>
            <li><a href="#">[南音唱腔]南音套曲刍议（三）</a></li>
            <li><a href="#">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
      </div>
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
    </div>
    <!--网页底部-->
    <div id="footer">
      <center>
        <p>| <a href="#">本网简介</a> | <a href="#">本网申明</a> | <a href="#">本网服务</a> | <a href="#">本网机构</a> | <a href="#">本网收支</a> | <a href="#">友情链接</a> | <a href="#">帮助中心</a> | <a href="#">最近访客</a> | <a href="#">会员中心</a> | <a href="#">联络我们</a> |</p>
        <p>声明：本网评论仅代表网友观点，与本网立场无关，本网不会就评论内容负任何法律责任！本网作品版权所有，未经允许不得转载！</p>
        <p>"福建南音网"由台北市两岸南管音乐推广协会理事长--林素梅老师，联合两岸南音弦友于2006年发起创办。</p>
        <p>本着传播南音、服务弦友的宗旨，欢迎文化关联网站申请友情链接。但近来出现未经允许转载本网原创作品，盗用本网之名的网站，已严重影响本网声誉。</p>
        <p>凡无本网LOGO的网站均与本网无关，请认准本网唯一网址www.fjnanyin.com</p>
        <p>CopyRight © 2007~2015 www.fjnanyin.com All Right Reserved 闽ICP备07025655  法律顾问：陈志强博士</p>
        <p>技术支持：厦门大学软件学院    平面设计：厦门司图艺术平面设计工作室</p>
        <p>赞助：（澳门） 世界南音联谊会   合办：台北市两岸南管音乐推广协会</p>
      </center>
    </div>
  </div>
</body>
</html>
