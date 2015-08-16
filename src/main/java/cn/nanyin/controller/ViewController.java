package cn.nanyin.controller;

import cn.nanyin.dao.*;
import cn.nanyin.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by 张一平 on 2015/7/29.
 * 首页中的所有操作
 */
@Controller
@RequestMapping("/index.do")
public class ViewController {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private User1Dao userDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private AudioDao audioDao;

    @Autowired
    private AnnouncementDao announcementDao;

    /*************一个页面分发器******************/
    @RequestMapping(params = "method=dispatcher")
    public  ModelAndView dispatcher(@RequestParam("page") String page,@RequestParam("type") int type){

        ModelAndView model = new ModelAndView(""+page+"");
        model.addObject("type",type);
        return model;
    }

    /**********************首页中的新闻模块（从数据库中读取6条新闻）*******************************************/
    @RequestMapping(params = "method=news", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getNews( )throws  Exception{
        List<News> newsList=newsDao.getNewsList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<6;i++){
            News tempNew=newsList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",tempNew.getId());
            map.put("title",tempNew.getTitle());
            map.put("type",tempNew.getNewsSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;

        /*
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out=response.getWriter();
        out.print(result);
        out.flush();
        out.close();
        */
    }

    @RequestMapping(params = "method=getNewsImages", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getNewsImages( )throws  Exception{
        List<News> newsList=newsDao.getNewsImages(0,5);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<5;i++){
            News tempNew=newsList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",tempNew.getId());
            map.put("path",tempNew.getFile());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }


    /****************链接到每个新闻的详情页面*************************/
    @RequestMapping(params = "method=newsLink", produces = "plain/text;charset=UTF-8")
    public ModelAndView showNews( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("newsDetail");
        long newsId=Long.parseLong(id);
        News newsDetail=newsDao.getNewsById(newsId);

        //request.setAttribute("news",newsDetail);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("title",newsDetail.getTitle());
        map.put("author",newsDetail.getAuthor());
        String date=newsDetail.getAddDate().toString();
        map.put("date",date);
        map.put("hits", newsDetail.getHits());
        map.put("source", newsDetail.getSource());
        JSONObject jsonObject=JSONObject.fromObject(map);
        String result=jsonObject.toString();

        Map<String,Object> news=new HashMap<String,Object>();
        news.put("newsTitle", result);
        news.put("content", newsDetail.getContent());
        news.put("newsId", newsDetail.getId());

        model.addObject("news", news);

        News updateNews=newsDetail;
        updateNews.setHits(newsDetail.getHits() + 1);
        newsDao.updateNews(updateNews);

        return model;
    }

/******************************音频******************************************************************************/
    /*****首页中的音频模块（从数据库中读取6条音频）***************/
    @RequestMapping(params = "method=getAudios", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getAudios(){
        List<Audio> audioList=audioDao.getAudioList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<6;i++){
            Audio temp=audioList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("type",temp.getAudioSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }
    /*************************链接到音频的详情页面**********************************/
    @RequestMapping(params = "method=audioLink")
    public ModelAndView showAudio( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("audioDetail");
        long audioId=Long.parseLong(id);
        Audio audioDetail=audioDao.getAudioById(audioId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",audioDetail.getId());
        map.put("title", audioDetail.getTitle());
        map.put("singer", audioDetail.getSinger());
        String date=audioDetail.getAddDate().toString();
        map.put("date", date);
        map.put("path", audioDetail.getPath());
        map.put("hits", audioDetail.getHits());
        map.put("player",audioDetail.getPlayer());
        map.put("producer", audioDetail.getProducer());
        map.put("content",audioDetail.getContent());
        map.put("gcp",audioDetail.getGcp());
        map.put("jp",audioDetail.getJp());
        model.addObject("audio", map);

        Audio updateAideo=audioDetail;
        updateAideo.setHits(audioDetail.getHits() + 1);
        audioDao.updateAudio(updateAideo);

        return model;
    }

/**************************************视频***************************************************************************/
    /*****首页中的视频模块（从数据库中读取6条视频）***************/
    @RequestMapping(params = "method=getVideos", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getVideos(){
        List<Video> videoList=videoDao.getVideoList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<6;i++){
            Video temp=videoList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("type",temp.getVideoSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    /*************************链接到视频的详情页面**********************************/
    @RequestMapping(params = "method=videoLink")
    public ModelAndView showVideo( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("videoDetail");
        long videoId=Long.parseLong(id);
        Video videoDetail=videoDao.getVideoById(videoId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",videoDetail.getId());
        map.put("title", videoDetail.getTitle());
        map.put("singer", videoDetail.getSinger());
        String date=videoDetail.getAddDate().toString();
        map.put("date",date);
        map.put("path",videoDetail.getPath());
        map.put("hits", videoDetail.getHits());
        map.put("player",videoDetail.getPlayer());
        map.put("cameraman",videoDetail.getCameraman());
        map.put("producer",videoDetail.getProducer());

        model.addObject("video", map);

        Video updateVideo=videoDetail;
        updateVideo.setHits(videoDetail.getHits() + 1);
        videoDao.updateVideo(updateVideo);

        return model;
    }


    /******************************会员登录*******************************************************/
    @RequestMapping(params = "method=login",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public  String login(String user,String password , HttpSession session)throws Exception{
        User1 loginUser=userDao.findUser(user, password);
        if(loginUser!=null){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("userId",loginUser.getId());
            map.put("userName",loginUser.getUserName());
            map.put("level",loginUser.getLevel());
            map.put("state",loginUser.getState());
            JSONObject jsonObject=JSONObject.fromObject(map);
            String result=jsonObject.toString();
            session.setAttribute("loginUser",loginUser);
            return result;
        }else{
            return "";
        }
    }
    /*****************************会员退出*******************************************************/
    @RequestMapping(params = "method=exit")
    @ResponseBody
    public void loginExit(HttpSession session ){
       session.setAttribute("loginUser",null);
    }

    @RequestMapping(params = "method=refresh")
    @ResponseBody
    public String refreshSession(HttpSession session ){
        User1 user=(User1)session.getAttribute("loginUser");
        if(user!=null){
            String userName=user.getUserName();
            return userName;
        }else{
            return "";
        }
    }

    /*******************************获取公告栏的信息********************************************************/
    @RequestMapping(params = "method=announcement", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getAnnouncement(){
        List<Announcement> announcementList=announcementDao.getAnnouncementList(0,5);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<5;i++){
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

    @RequestMapping(params = "method=announcementLink")
    public ModelAndView showAnnouncement( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("announcementDetail");
        long announcementId=Long.parseLong(id);
        Announcement announcementDetail=announcementDao.getAnnouncementById(announcementId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("title", announcementDetail.getTitle());
        String date=announcementDetail.getAddDate().toString();
        map.put("date", date);
        map.put("content",announcementDetail.getContent());
        model.addObject("announcement",map);

        return model;
    }

    /*********************************链接到注册页面************************************************************/
    @RequestMapping(params = "method=register")
    public  ModelAndView registerPage1(int type ){
        if(type==0){
            ModelAndView  model = new ModelAndView("register1");
            return model;
        }else if(type==1){
            ModelAndView model = new ModelAndView("register2");
            model.addObject("type",1);
            return model;
        }else if(type==2){
            ModelAndView model = new ModelAndView("register2");
            model.addObject("type",2);
            return model;
        }else {
            return null;
        }
    }
    /*******************************链接到找回密码页面**************************************************/
    @RequestMapping(params = "method=pswRecovery")
    public  ModelAndView pswRecovery( ){
        ModelAndView  model = new ModelAndView("pswRecovery");
        return model;
    }
    /************************************链接到会员中心页面*********************************************************/
    @RequestMapping(params = "method=userCenter")
    public  ModelAndView userCenter( ){
        ModelAndView  model = new ModelAndView("userCenter");
        return model;
    }

}
