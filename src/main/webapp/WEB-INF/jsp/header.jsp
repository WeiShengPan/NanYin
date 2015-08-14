<%--
  Created by IntelliJ IDEA.
  User: 张一平
  Date: 2015/7/31
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="/css/header.css">

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


</div>
