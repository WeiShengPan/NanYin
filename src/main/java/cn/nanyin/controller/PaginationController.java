package cn.nanyin.controller;

import cn.nanyin.dao.*;
import cn.nanyin.model.Announcement;
import cn.nanyin.model.Audio;
import cn.nanyin.model.News;
import cn.nanyin.model.Video;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张一平 on 2015/8/15.
 */
@Controller
@RequestMapping("/pagination.do")
public class PaginationController {
    @Autowired
    private NewsDao newsDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private AudioDao audioDao;

    @Autowired
    private AnnouncementDao announcementDao;
    /***********************************新闻列表************************************************************/
    @RequestMapping(params = "method=showNewsList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showNewsList(int type,int curPage,int pageSize)throws Exception{
        List<News> newsList=new ArrayList<News>();
        int totalNum=0;
        int totalPage=0;
        if(type==0){
            totalNum=newsDao.getNum();
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            newsList=newsDao.getNewsList(start,pageSize);
        }else if(type==1){
            String typeName="南音快讯";
            totalNum=newsDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }else if(type==2){
            String typeName="海外南音";
            totalNum=newsDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }else if(type==3){
            String typeName="南音专题";
            totalNum=newsDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }else if(type==4){
            String typeName="南音人物";
            totalNum=newsDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }else if(type==5){
            String typeName="南音转载";
            totalNum=newsDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }

        List<Map> list=new ArrayList<Map>();

        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<newsList.size();i++){
            News tempNew=newsList.get(i);
            String date=tempNew.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",tempNew.getId());
            map.put("title", tempNew.getTitle());
            map.put("author", tempNew.getAuthor());
            map.put("date",date);
            map.put("hits",tempNew.getHits());
            map.put("type",tempNew.getNewsSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }
    /***********************************新闻列表翻页************************************************************/
    @RequestMapping(params = "method=newsTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String newsTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<News> newsList=new ArrayList<News>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            newsList=newsDao.getNewsList(start,pageSize);
        }else if(type==1){
            String typeName="南音快讯";
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }else if(type==2){
            String typeName="海外南音";
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }else if(type==3){
            String typeName="南音专题";
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }else if(type==4){
            String typeName="南音人物";
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }else if(type==5){
            String typeName="南音转载";
            newsList=newsDao.getNewsList(typeName,start,pageSize);
        }

        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<newsList.size();i++){
            News tempNew=newsList.get(i);
            String date=tempNew.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",tempNew.getId());
            map.put("title", tempNew.getTitle());
            map.put("author", tempNew.getAuthor());
            map.put("date",date);
            map.put("hits",tempNew.getHits());
            map.put("type",tempNew.getNewsSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }
    /***********************************音频列表************************************************************/
    @RequestMapping(params = "method=showAudioList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showAudioList(int type,int curPage,int pageSize)throws Exception{

        int totalNum=0;
        int totalPage=0;

        List<Audio> audioList=new ArrayList<Audio>();
        if(type==0){
            totalNum=audioDao.getNum();
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            audioList=audioDao.getAudioList(start,pageSize);
        }else if(type==1){
            String typeName="南音.谱";
            totalNum=audioDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            audioList=audioDao.getAudioList(typeName,start,pageSize);
        }else if(type==2){
            String typeName="南音.指";
            totalNum=audioDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            audioList=audioDao.getAudioList(typeName,start,pageSize);
        }else if(type==3){
            String typeName="南音.曲";
            totalNum=audioDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            audioList=audioDao.getAudioList(typeName,start,pageSize);
        }

        List<Map> list=new ArrayList<Map>();
        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<audioList.size();i++){
            Audio temp=audioList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("singer",temp.getSinger());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("hits",temp.getHits());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }
    /***********************************音频列表翻页************************************************************/
    @RequestMapping(params = "method=audioTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String audioTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Audio> audioList=new ArrayList<Audio>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            audioList=audioDao.getAudioList(start,pageSize);
        }else if(type==1){
            String typeName="南音.谱";
            audioList=audioDao.getAudioList(typeName,start,pageSize);
        }else if(type==2){
            String typeName="南音.指";
            audioList=audioDao.getAudioList(typeName,start,pageSize);
        }else if(type==3){
            String typeName="南音.曲";
            audioList=audioDao.getAudioList(typeName,start,pageSize);
        }

        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<audioList.size();i++){
            Audio temp=audioList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("singer",temp.getSinger());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("hits",temp.getHits());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    /***********************************视频列表************************************************************/
    @RequestMapping(params = "method=showVideoList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showVideoList(int type,int curPage,int pageSize)throws Exception{

        int totalNum=0;
        int totalPage=0;

        List<Video> videoList=new ArrayList<Video>();
        if(type==0){
            totalNum=videoDao.getNum();
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            videoList=videoDao.getVideoList(start,pageSize);
        }else if(type==1){
            String typeName="经典视频";
            totalNum=videoDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            videoList=videoDao.getAudioList(typeName,start,pageSize);
        }else if(type==2){
            String typeName="南音专辑";
            totalNum=videoDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==3){
            String typeName="社团视频";
            totalNum=videoDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==4){
            String typeName="南音会唱";
            totalNum=videoDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==5){
            String typeName="南音比赛";
            totalNum=videoDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==6){
            String typeName="南音网庆";
            totalNum=videoDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==7){
            String typeName="其他视频";
            totalNum=videoDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }

        List<Map> list=new ArrayList<Map>();
        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<videoList.size();i++){
            Video temp=videoList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("singer",temp.getSinger());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("hits",temp.getHits());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }
    /***********************************视频列表翻页************************************************************/
    @RequestMapping(params = "method=videoTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String videoTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Video> videoList=new ArrayList<Video>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            videoList=videoDao.getVideoList(start,pageSize);
        }else if(type==1){
            String typeName="经典视频";
            videoList=videoDao.getAudioList(typeName,start,pageSize);
        }else if(type==2){
            String typeName="南音专辑";
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==3){
            String typeName="社团视频";
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==4){
            String typeName="南音会唱";
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==5){
            String typeName="南音比赛";
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==6){
            String typeName="南音网庆";
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }else if(type==7){
            String typeName="其他视频";
            videoList=videoDao.getAudioList(typeName, start, pageSize);
        }

        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<videoList.size();i++){
            Video temp=videoList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("singer",temp.getSinger());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("hits",temp.getHits());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }
    /***********************************公告列表************************************************************/
    @RequestMapping(params = "method=showAnnouncementList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showAnnouncementList(int curPage,int pageSize)throws Exception{
        int totalNum=0;
        int totalPage=0;
        totalNum=announcementDao.getNum();
        double re=(double)totalNum/pageSize;
        totalPage=(int)Math.ceil(re);
        int start=(curPage-1)*pageSize;
        List<Announcement>  announcementList=announcementDao.getAnnouncementList(start, pageSize);

        List<Map> list=new ArrayList<Map>();
        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<announcementList.size();i++){
            Announcement announcement=announcementList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",announcement.getId());
            map.put("title",announcement.getTitle());
            String date=announcement.getAddDate().toString();
            map.put("date",date);
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }
    /***********************************公告列表翻页************************************************************/
    @RequestMapping(params = "method=announcementTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String announcementTurnPage(int curPage,int pageSize)throws Exception{
        int start=(curPage-1)*pageSize;
        List<Announcement>  announcementList=announcementDao.getAnnouncementList(start, pageSize);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<announcementList.size();i++){
            Announcement announcement=announcementList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",announcement.getId());
            map.put("title",announcement.getTitle());
            String date=announcement.getAddDate().toString();
            map.put("date",date);
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }


}
