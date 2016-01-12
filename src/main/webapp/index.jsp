<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/7/29
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="cn.nanyin.model.User1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript" src="Js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" language="JavaScript" src="Js/bigpicroll.js"></script>
  <script type="text/javascript" language="JavaScript" src="Js/announcement.js"></script>
  <link rel="stylesheet" type="text/css" href="css/index.css" />
  <%@include file="WEB-INF/jsp/loginTemplate.jsp"%>
  <title>福建南音网</title>

  <script type="text/javascript">

    $(document).ready(function(){
      /******************南音新闻*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=news',
        error:function(){
          alert("新闻栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#news"+i).find("a").text("["+result[i].type+"] "+result[i].title+"");
            $("#news"+i).find("a").attr("href","index.do?method=newsLink&id="+result[i].id+"");
            $("#news"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });
      /******************新闻图片*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getNewsImages',
        error:function(){
          alert("图片加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#newsImage"+i).find("a").attr("href","index.do?method=newsLink&id="+result[i].id+"");
            $("#newsImage"+i).find("img").attr("src",""+result[i].path+"");
          }
        }
      });

      /******************南音文库*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getContent',
        data:{
          type:1,
        },
        error:function(){
          alert("文库栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#library"+i).find("a").text("["+result[i].type+"] "+result[i].title+"");
            $("#library"+i).find("a").attr("href","index.do?method=libraryLink&id="+result[i].id+"");
            $("#library"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });

      /******************南音图谱*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getContent',
        data:{
          type:2,
        },
        error:function(){
          alert("曲谱栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#gallery"+i).find("a").text("["+result[i].type+"] "+result[i].title+"");
            $("#gallery"+i).find("a").attr("href","index.do?method=galleryLink&id="+result[i].id+"");
            $("#gallery"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });

      /******************图谱滚动图片*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getGalleryImages',
        error:function(){
          alert("图片加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            var text='<td align=center><a href="index.do?method=galleryLink&id='+result[i].id+'"><img src="'+result[i].path+'" width=250 height=150 border="0"></a></td>'+
                    '<td align=center>&nbsp&nbsp</td>';
            $("#main_16 .addTr").append(text);
          }
        }
      });

      /******************商城滚动图片*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getProductImages',
        error:function(){
          alert("图片加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            var text='<td align=center><a href="index.do?method=productLink&id='+result[i].id+'"><img src="'+result[i].path+'" width=140 height=180 border="0"></a><br><p>'+result[i].name+'</p></td>'+
                    '<td align=center>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp </td>';
            $("#main_9 .addTr").append(text);
          }
        }
      });


      /******************南音乐理*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getContent',
        data:{
          type:3,
        },
        error:function(){
          alert("乐理栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#musicTheory"+i).find("a").text("["+result[i].type+"] "+result[i].title+"");
            $("#musicTheory"+i).find("a").attr("href","index.do?method=musicTheoryLink&id="+result[i].id+"");
            $("#musicTheory"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });

      /******************公告*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=announcement',
        error:function(){
          alert("公告栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#announcement"+i).find("a").text(""+(i+1)+""+"."+result[i].title);
            $("#announcement"+i).find("a").attr("href","index.do?method=announcementLink&id="+result[i].id+"");
          }
        }
      });
      /******************南音视频*****************/
      $.ajax({
        type:'POST',
         url:'index.do?method=getVideos',
        error:function(){
          alert("视频栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#video"+i).find("a").text("["+result[i].type+"]"+" "+result[i].title);
            $("#video"+i).find("a").attr("href","index.do?method=videoLink&id="+result[i].id+"");
            $("#video"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });

      /******************南音音频*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getAudios',
        error:function(){
          alert("音频栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#audio"+i).find("a").text("["+result[i].type+"]"+" "+result[i].title);
            $("#audio"+i).find("a").attr("href","index.do?method=audioLink&id="+result[i].id+"");
            $("#audio"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });

      /******************南音名录*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getContent',
        data:{
          type:4,
        },
        error:function(){
          alert("名录栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#directory"+i).find("a").text("["+result[i].type+"]"+" "+result[i].title);
            $("#directory"+i).find("a").attr("href","index.do?method=directoryLink&id="+result[i].id+"");
            $("#directory"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });
      /******************南音商城*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getProducts',
        error:function(){
          alert("商城栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#product"+i).find("a").text("["+result[i].type+"] "+result[i].title+"");
            $("#product"+i).find("a").attr("href","index.do?method=productLink&id="+result[i].id+"");
            $("#product"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });

      /******************南音社团*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getColleges',
        error:function(){
          alert("社团栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#college"+i).find("a").text("["+result[i].type+"] "+result[i].title+"");
            $("#college"+i).find("a").attr("href","index.do?method=collegeLink&id="+result[i].id+"");
            $("#college"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });

      /******************南音教学*****************/
      $.ajax({
        type:'POST',
        url:'index.do?method=getTeaching',
        error:function(){
          alert("教学栏加载失败！");
        },
        success:function(data){
          var result= $.parseJSON(data);
          for(var i=0;!(i >= result.length);i++){
            $("#teaching"+i).find("a").text("["+result[i].type+"] "+result[i].title+"");
            $("#teaching"+i).find("a").attr("href","index.do?method=teachingLink&id="+result[i].id+"");
            $("#teaching"+i).find("a").attr("title",""+result[i].title+"");
          }
        }
      });
    });

    function validForm(){
      var text=$("#searchContent").val();
      if(text==""){
        alert("请填写关键字");
        return false;
      }
      return true;
    }


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
        <li><a href="index.jsp">首&nbsp&nbsp页</a></li>
        <li>
          <a href="index.do?method=dispatcher&page=newsList&type=0">南音新闻</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=newsList&type=1">南音快讯</a></li>
            <li><a href="index.do?method=dispatcher&page=newsList&type=2">海外南音</a></li>
            <li><a href="index.do?method=dispatcher&page=newsList&type=3">南音专题</a></li>
            <li><a href="index.do?method=dispatcher&page=newsList&type=4">南音人物</a></li>
            <li><a href="index.do?method=dispatcher&page=newsList&type=5">南音转载</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=musicTheoryList&type=0">南音乐理</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=musicTheoryList&type=1">南音常识</a></li>
            <li><a href="index.do?method=dispatcher&page=musicTheoryList&type=2">工乂谱简介</a></li>
            <li><a href="index.do?method=dispatcher&page=musicTheoryList&type=3">南音乐器</a></li>
            <li><a href="index.do?method=dispatcher&page=musicTheoryList&type=4">南音唱腔</a></li>
            <li><a href="index.do?method=dispatcher&page=musicTheoryList&type=5">滚门曲牌</a></li>
            <li><a href="index.do?method=dispatcher&page=musicTheoryList&type=6">南音本事</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=libraryList&type=0">南音文库</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=libraryList&type=1">南音曲词</a></li>
            <li><a href="index.do?method=dispatcher&page=libraryList&type=2">南音论文</a></li>
            <li><a href="index.do?method=dispatcher&page=libraryList&type=3">南音钩沉</a></li>
            <li><a href="index.do?method=dispatcher&page=libraryList&type=4">南音文萃</a></li>
            <li><a href="index.do?method=dispatcher&page=libraryList&type=5">南音轶事</a></li>
            <li><a href="index.do?method=dispatcher&page=libraryList&type=6">诗词楹联</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=galleryList&type=0">南音图谱</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=galleryList&type=1">南音工乂谱</a></li>
            <li><a href="index.do?method=dispatcher&page=galleryList&type=2">南音简谱</a></li>
            <li><a href="index.do?method=dispatcher&page=galleryList&type=3">电子相册</a></li>
            <li><a href="index.do?method=dispatcher&page=galleryList&type=4">专题剪影</a></li>
            <li><a href="index.do?method=dispatcher&page=galleryList&type=5">珍贵图录</a></li>
            <li><a href="index.do?method=dispatcher&page=galleryList&type=6">名家题词</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=audioList&type=0">南音曲库</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=audioList&type=1">南音.谱</a></li>
            <li><a href="index.do?method=dispatcher&page=audioList&type=2">南音.指</a></li>
            <li><a href="index.do?method=dispatcher&page=audioList&type=3">南音.曲</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=videoList&type=0">南音视频</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=videoList&type=1">经典视频</a></li>
            <li><a href="index.do?method=dispatcher&page=videoList&type=2">南音专辑</a></li>
            <li><a href="index.do?method=dispatcher&page=videoList&type=3">社团视频</a></li>
            <li><a href="index.do?method=dispatcher&page=videoList&type=4">南音会唱</a></li>
            <li><a href="index.do?method=dispatcher&page=videoList&type=5">南音比赛</a></li>
            <li><a href="index.do?method=dispatcher&page=videoList&type=6">南音网庆</a></li>
            <li><a href="index.do?method=dispatcher&page=videoList&type=7">其他视频</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=poetryList&type=0">诗词音画</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=poetryList&type=1"><p>唐诗宋词</p><p>南管唱</p></a></li>
            <li><a href="index.do?method=dispatcher&page=poetryList&type=2"><p>丁马成</p><p>南音作品</p></a></li>
            <li><a href="index.do?method=dispatcher&page=poetryList&type=3"><p>郑梦集</p><p>茶乡清曲</p></a></li>
            <li><a href="index.do?method=dispatcher&page=poetryList&type=4"><p>陈丽华</p><p>台湾风情</p></a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=teachingList&type=0">南音教学</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=teachingList&type=1">工乂谱识谱</a></li>
            <li><a href="index.do?method=dispatcher&page=teachingList&type=2">乐器技法</a></li>
            <li><a href="index.do?method=dispatcher&page=teachingList&type=3">唱腔指导</a></li>
            <li><a href="index.do?method=dispatcher&page=teachingList&type=4">名师访谈</a></li>
            <li><a href="index.do?method=dispatcher&page=teachingList&type=5">学习申请</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=directoryList&type=0">南音名录</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=directoryList&type=1">南音人物志</a></li>
            <li><a href="index.do?method=dispatcher&page=directoryList&type=2">南音传承人</a></li>
            <li><a href="index.do?method=dispatcher&page=directoryList&type=3">南音新秀榜</a></li>
            <li><a href="index.do?method=dispatcher&page=directoryList&type=4">学术界名录</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=collegeList&type=0">南音社团</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=collegeList&type=1">荣誉社团</a></li>
            <li><a href="index.do?method=dispatcher&page=collegeList&type=2">国内社团</a></li>
            <li><a href="index.do?method=dispatcher&page=collegeList&type=3">港澳台社团</a></li>
            <li><a href="index.do?method=dispatcher&page=collegeList&type=4">东南亚社团</a></li>
            <li><a href="index.do?method=dispatcher&page=collegeList&type=5">传习&培训</a></li>
          </ul>
        </li>
        <li>
          <a href="index.do?method=dispatcher&page=productsList1&type=0">南音商城</a>
          <ul>
            <li><a href="index.do?method=dispatcher&page=productsList2&type=1">南音书籍</a></li>
            <li><a href="index.do?method=dispatcher&page=productsList2&type=2">南音音像</a></li>
            <li><a href="index.do?method=dispatcher&page=productsList2&type=3">南音乐器 </a></li>
            <li><a href="index.do?method=dispatcher&page=productsList2&type=4">南音伴奏</a></li>
            <li><a href="index.do?method=dispatcher&page=productsList2&type=5">专辑录制</a></li>
            <li><a href="index.do?method=dispatcher&page=productsList2&type=6">其他配件</a></li>
            <li><a href="index.do?method=dispatcher&page=productsList2&type=7">文创艺品 </a></li>
          </ul>
        </li>
      </ul>
    </div>
    <!--搜索栏-->
    <div id="search">
      <form action="index.do?method=searchContent" method="post">
        <table align="right" style="margin-top:5px">
          <tr>
            <td>
              <select id="selectItem" name="selectItem">
                <option value="1">文库</option>
                <option value="2">影音</option>
                <option value="3">曲库</option>
              </select>
            </td>
            <td><input id="searchContent" type="text" name="searchContent" style="width:200px"/></td>
            <td><input type="submit" value="搜索" style="width:50px" onclick="return validForm()"/></td>
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
          <p class="c3">南音简介</p>
          <div id="introduction">
            <p>南音又称南乐、南曲、南管、弦管、郎君乐等，是我国四大古乐之一，至今有一千多年的历史。清康熙年间，万寿祝典，闽五少芳贤入京御前献奏，赐予"御前清曲"。故南音素有"华夏瑰宝"、"音乐活化石"之美誉，又有"御前清曲"之雅颂。2009年9月，南音被联合国科教文组织列入《人类非物质文化遗产代表作名录》。</p>
            <p>南音的音乐由"指"、"谱"、"曲"三大部分组成，蕴含了晋清商乐、唐大曲、法曲、燕乐和佛教音乐及宋元明以来的词曲音乐、戏曲音乐，保留了唐宋古典曲牌，有着浓厚的中原古乐遗风。</p>
          </div>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=briefIntroduction&type=0"> >> 更 多 </a></p>
        </div>
        <!--图片切换-->
        <div id="main_2">
          <div class="showpage"  style="margin-top:2px">
            <div  class="flashbox f_list" style="margin:0 auto">
              <div align="center" class="focusNew_out flashlist">
                <div id="newsImage0" style="display:block;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/1.jpg" border="0"></a>
                </div>
                <div id="newsImage1" style="display:none;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/2.jpg" border="0"></a>
                </div>
                <div id="newsImage2" style="display:none;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/3.jpg" border="0"></a>
                </div>
                <div id="newsImage3" style="display:none;" class="f_out">
                  <a target="_blank" href="#"><img width="99%" height="270" src="images/4.jpg" border="0"></a>
                </div>
                <div id="newsImage4" style="display:none;" class="f_out">
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
          <p class="c3">南音新闻</p>
          <ul>
            <li id="news0"><a href="" title="">[南音常识]福建南音简介</a></li>
            <li id="news1"><a href="" title="">[南音常识]南音套曲刍议（六）</a></li>
            <li id="news2"><a href="" title="">[工尺谱]南音套曲刍议（五）</a></li>
            <li id="news3"><a href="" title="">[南音乐器]南音套曲刍议（四）</a></li>
            <li id="news4"><a href="" title="">[南音唱腔]南音套曲刍议（三）</a></li>
            <li id="news5"><a href="" title="">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=newsList&type=0"> >> 更 多 </a></p>
        </div>
        <!--南音乐理-->
        <div id="main_4">
          <p class="c3">南音乐理</p>
          <ul>
            <li id="musicTheory0"><a href="" title="">[南音常识]福建南音简介</a></li>
            <li id="musicTheory1"><a href="" title="">[南音常识]南音套曲刍议（六）</a></li>
            <li id="musicTheory2"><a href="" title="">[工尺谱]南音套曲刍议（五）</a></li>
            <li id="musicTheory3"><a href="" title="">[南音乐器]南音套曲刍议（四）</a></li>
            <li id="musicTheory4"><a href="" title="">[南音唱腔]南音套曲刍议（三）</a></li>
            <li id="musicTheory5"><a href="" title="">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=musicTheoryList&type=0"> >> 更 多 </a></p>
        </div>
        <!--南音文库-->
        <div id="main_5">
          <p class="c3">南音文库</p>
          <ul>
            <li id="library0"><a href="" title="">[南音常识]福建南音简介</a></li>
            <li id="library1"><a href="" title="">[南音常识]南音套曲刍议（六）</a></li>
            <li id="library2"><a href="" title="">[工尺谱]南音套曲刍议（五）</a></li>
            <li id="library3"><a href="" title="">[南音乐器]南音套曲刍议（四）</a></li>
            <li id="library4"><a href="" title="">[南音唱腔]南音套曲刍议（三）</a></li>
            <li id="library5"><a href="" title="">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=libraryList&type=0"> >> 更 多 </a></p>
        </div>
        <!--分隔线-->
        <div class="line"></div>
        <!--南音教学-->
        <div id="main_6">
          <p class="c3">南音教学</p>
          <ul>
            <li id="teaching0"><a href="" title="">[南音常识]福建南音简介</a></li>
            <li id="teaching1"><a href="" title="">[南音常识]南音套曲刍议（六）</a></li>
            <li id="teaching2"><a href="" title="">[工尺谱]南音套曲刍议（五）</a></li>
            <li id="teaching3"><a href="" title="">[南音乐器]南音套曲刍议（四）</a></li>
            <li id="teaching4"><a href="" title="">[南音唱腔]南音套曲刍议（三）</a></li>
            <li id="teaching5"><a href="" title="">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=TeachingList&type=0"> >> 更 多 </a></p>
        </div>
        <!--南音曲库-->
        <div id="main_7">
          <p class="c3">南音曲库</p>
          <ul>
            <li  id="audio0"><a href="" title="">[南音常识]福建南音简介</a></li>
            <li  id="audio1"><a href="" title="">[南音常识]南音套曲刍议（六）</a></li>
            <li  id="audio2"><a href="" title="">[工尺谱]南音套曲刍议（五）</a></li>
            <li  id="audio3"><a href="" title="">[南音乐器]南音套曲刍议（四）</a></li>
            <li  id="audio4"><a href="" title="">[南音唱腔]南音套曲刍议（三）</a></li>
            <li  id="audio5"><a href="" title="">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=audioList&type=0"> >> 更 多 </a></p>
        </div>
        <!--南音视频-->
        <div id="main_8">
          <p class="c3">南音视频</p>
          <ul>
            <li  id="video0"><a href="" title="" >[南音常识]福建南音简介</a></li>
            <li  id="video1"><a href="" title="" >[南音常识]南音套曲刍议（六）</a></li>
            <li  id="video2"><a href="" title="" >[工尺谱]南音套曲刍议（五）</a></li>
            <li  id="video3"><a href="" title="" >[南音乐器]南音套曲刍议（四）</a></li>
            <li  id="video4"><a href="" title="" >[南音唱腔]南音套曲刍议（三）</a></li>
            <li  id="video5"><a href="" title="" >[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=videoList&type=0"> >> 更 多 </a></p>
        </div>

        <!--滚动图片-->
        <div id="main_9">
          <div id="demo" style="overflow:hidden; width:100%;" align=center>
            <table align="center">
              <tr>
                <td valign=top  id=marquePic1>
                  <table width='100%' cellspacing='0'>
                    <tr class="addTr">

                    </tr>
                  </table>
                </td>
                <td id=marquePic2 valign=top></td>
              </tr>
            </table>
          </div>
          <script type="text/javascript">
            var speed1=50;
            marquePic2.innerHTML=marquePic1.innerHTML;
            function Marquee1(){
              if(demo.scrollLeft>=marquePic1.scrollWidth){
                demo.scrollLeft=0;
              }else{
                demo.scrollLeft++;
              }
            }
            var MyMar1=setInterval(Marquee1,speed1);
            demo.onmouseover=function() {clearInterval(MyMar1)};
            demo.onmouseout=function() {MyMar1=setInterval(Marquee1,speed1)};
          </script>
        </div>
        <!--南音商城-->
        <div id="main_10">
          <p class="c3">南音商城</p>
          <ul>
            <li id="product0"><a href="" title="" >[南音常识]福建南音简介</a></li>
            <li id="product1"><a href="" title="" >[南音常识]南音套曲刍议（六）</a></li>
            <li id="product2"><a href="" title="" >[工尺谱]南音套曲刍议（五）</a></li>
            <li id="product3"><a href="" title="" >[南音乐器]南音套曲刍议（四）</a></li>
            <li id="product4"><a href="" title="" >[南音唱腔]南音套曲刍议（三）</a></li>
            <li id="product5"><a href="" title="" >[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=productsList1&type=0"> >> 更 多 </a></p>
        </div>
        <!--南音名录-->
        <div id="main_11">
          <p class="c3">南音名录</p>
          <ul>
            <li id="directory0"><a href="" title="">[南音常识]福建南音简介</a></li>
            <li id="directory1"><a href="" title="">[南音常识]南音套曲刍议（六）</a></li>
            <li id="directory2"><a href="" title="">[工尺谱]南音套曲刍议（五）</a></li>
            <li id="directory3"><a href="" title="">[南音乐器]南音套曲刍议（四）</a></li>
            <li id="directory4"><a href="" title="">[南音唱腔]南音套曲刍议（三）</a></li>
            <li id="directory5"><a href="" title="">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="index.do?method=dispatcher&page=directoryList&type=0"> >> 更 多 </a></p>
        </div>
        <!--南音社团-->
        <div id="main_12">
          <p class="c3">南音社团</p>
          <ul>
            <li  id="college0"><a href="" title="">[南音常识]福建南音简介</a></li>
            <li  id="college1"><a href="" title="">[南音常识]南音套曲刍议（六）</a></li>
            <li  id="college2"><a href="" title="">[工尺谱]南音套曲刍议（五）</a></li>
            <li  id="college3"><a href="" title="">[南音乐器]南音套曲刍议（四）</a></li>
            <li  id="college4"><a href="" title="">[南音唱腔]南音套曲刍议（三）</a></li>
            <li  id="college5"><a href="" title="">[滚门曲牌]南音套曲刍议（二）</a></li>
          </ul>
          <p class="c1" align="right"><a href="#"> >> 更 多 </a></p>
        </div>
        <!--分隔线-->
        <div id="main_16">
          <div id="demo1" style="overflow:hidden; width:100%;" align=center>
            <table align="center">
              <tr>
                <td valign=top  id=marquePic3>
                  <table width='100%' cellspacing='1'>
                    <tr class="addTr">

                    </tr>
                  </table>
                </td>
                <td id=marquePic4 valign=top></td>
              </tr>
            </table>
          </div>
          <script type="text/javascript">
            var speed=50;
            marquePic4.innerHTML=marquePic3.innerHTML;
            function Marquee(){
              if(demo1.scrollLeft>=marquePic3.scrollWidth){
                demo1.scrollLeft=0;
              }else{
                demo1.scrollLeft++;
              }
            }
            var MyMar=setInterval(Marquee,speed);
            demo1.onmouseover=function() {clearInterval(MyMar)};
            demo1.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
          </script>
        </div>
      </div>
      <!--右边的侧栏-->
      <div id="sidebar">
        <!--登录-->
        <div id="login" style=" width:98%; height:160px; margin:10px auto; border-bottom: #CCC 1px dashed ">
          <p class="c3" align="center">会员登陆</p>
          <form id="loginForm">
            <div id="loginField">
              <table id="loginTab" class="c2"  style=" width:100%; height:120px; margin-top:10px">
                <tr><td align="right">用户名:</td><td><input id="user" type="text" name="user" style="width: 100px" size="20" maxlength="20"/></td></tr>
                <tr><td align="right">密&nbsp&nbsp码:</td><td><input id="psw" type="password" style="width: 100px"  name="password" size="20" maxlength="20"/></td></tr>
                <tr><td></td><td><a href="javascript:void(0)" onclick="Login()">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="Reset()">重置</a></td></tr>
                <tr><td></td><td><a  href="index.do?method=register&type=0">注册</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.do?method=pswRecovery">忘记密码</a></td></tr>
              </table>
            </div>
          </form>
        </div>
        <!--公告-->
        <div id="announcement">
          <center> <p class="c3">公告</p></center>
          <div id="scrollDiv" align="center">
            <ul class="c2">
              <li id="announcement0"><p><a href="">这是公告标题的第一行</a></p></li>
              <li id="announcement1"><p><a href="">这是公告标题的第二行</a></p></li>
              <li id="announcement2"><p><a href="">这是公告标题的第三行</a></p></li>
              <li id="announcement3"><p><a href="">这是公告标题的第四行</a></p></li>
              <li id="announcement4"><p><a href="">这是公告标题的第五行</a></p></li>
            </ul>
          </div><br />
          <p class="c1" align="right" style=" margin-right:10px"><a href="index.do?method=dispatcher&page=announcementList&type=0"> >> 更 多 </a></p>
        </div>
        <!--投稿-->
        <div id="submission">
          <p class="c3">欢迎投稿</p>
          <p class="c2"><a href="index.do?method=dispatcher&page=submission&type=0">点击进入</a></p>
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
    </div>
    <!--网页底部-->
    <div id="footer">
      <center>
        <p>| <a href="#">本网简介</a> | <a href="#">本网申明</a> | <a href="#">本网服务</a> | <a href="#">本网机构</a> | <a href="#">本网地图</a> | <a href="#">本网收支</a> | <a href="#">友情链接</a> | <a href="#">帮助中心</a> | <a href="#">最近访客</a> | <a href="#">会员中心</a> | <a href="#">联络我们</a> |</p>
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
