<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/7/31
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="/css/header.css">
<script type="text/javascript">
  function validForm(){
    var text=$("#searchContent").val();
    if(text==""){
      alert("请填写关键字");
      return false;
    }
    return true;
  }
</script>

<div id="header">
  <!--网页logo-->
  <div id="logo">
    <embed src="/images/2.swf" width="1024px" height="176px"></embed>
  </div>
  <!--导航菜单-->
  <div id="menu">
    <ul>
      <li><a href="index.jsp">首  页</a></li>
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
          <li><a href="index.do?method=dispatcher&page=galleryList&type=2">南音简谱影</a></li>
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
          <li><a href="index.do?method=dispatcher&page=teachingList&type=3">唱腔指导曲</a></li>
          <li><a href="index.do?method=dispatcher&page=teachingList&type=4">名师访谈唱</a></li>
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
            <select name="selectItem">
              <option value="1">文库</option>
              <option value="2">影音</option>
              <option value="3">曲库</option>
            </select>
          </td>
          <td><input id="searchContent" type="text" name="searchContent" style="width:200px"/></td>
          <td><input type="submit" value="搜索" style="width:50px" onclick=" return validForm()" /></td>
        </tr>
      </table>
    </form>
  </div>


</div>
