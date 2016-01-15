package cn.nanyin.controller;

import cn.nanyin.dao.*;
import cn.nanyin.model.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by 张一平 on 2015/8/12.
 */
@Controller
@RequestMapping("/comments.do")
public class CommentsController {
    @Autowired
    CommentsDao commentsDao;

    @Autowired
    NewsDao newsDao;

    @Autowired
    VideoDao videoDao;

    @Autowired
    AudioDao audioDao;

    @Autowired
    TeachingDao teachingDao;

    /***************************新闻评论*****************************************************************************/
    /*************************发送新闻评论**********************/
    @RequestMapping(params = "method=sendNewsComments",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public  void sendNewsComments(long newsId,String content, HttpSession session )throws Exception{
        User1 user=(User1)session.getAttribute("loginUser");
        News news=newsDao.getNewsById(newsId);
        Date date=new Date();
        NewsComments newsComments=new NewsComments();
        newsComments.setAddDate(date);
        newsComments.setContent(content);
        newsComments.setNews(news);
        newsComments.setUser(user);
        commentsDao.addNewsComments(newsComments);
    }
    /**************列出某个新闻页面中的所有评论***************/
    @RequestMapping(params = "method=listNewsComments",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String listNewsComments(long newsId){
        List<NewsComments> newsComments=commentsDao.getNewsCommentsList(newsId);
        if(newsComments==null){
            return "";
        }
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<newsComments.size();i++){
            Map<String,Object> map =new HashMap<String,Object>();
            NewsComments temp=newsComments.get(i);
            map.put("userName",temp.getUser().getUserName());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("content",temp.getContent());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();

        return result;
    }

    /***************************视频评论*****************************************************************************/
    /*************************发送视频评论**********************/
    @RequestMapping(params = "method=sendVideoComments",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public void sendVideoComments(long videoId,String content, HttpSession session )throws Exception{
        User1 user=(User1)session.getAttribute("loginUser");
        Video video=videoDao.getVideoById(videoId);
        Date date=new Date();
        VideoComments videoComments=new VideoComments();
        videoComments.setAddDate(date);
        videoComments.setContent(content);
        videoComments.setVideo(video);
        videoComments.setUser(user);
        commentsDao.addVideoComments(videoComments);
    }
    /**************列出某个视频页面中的所有评论***************/
    @RequestMapping(params = "method=listVideoComments",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String listVideoComments(long videoId){
        List<VideoComments> videoComments=commentsDao.getVideoCommentsList(videoId);
        if(videoComments==null){
            return "";
        }
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<videoComments.size();i++){
            Map<String,Object> map =new HashMap<String,Object>();
            VideoComments temp=videoComments.get(i);
            map.put("userName",temp.getUser().getUserName());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("content",temp.getContent());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();

        return result;
    }
    /***************************音频评论*****************************************************************************/
    /*************************发送音频评论**********************/
    @RequestMapping(params = "method=sendAudioComments",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public  void sendAudioComments(long audioId,String content, HttpSession session )throws Exception{
        User1 user=(User1)session.getAttribute("loginUser");
        Audio audio=audioDao.getAudioById(audioId);
        Date date=new Date();
        AudioComments audioComments=new AudioComments();
        audioComments.setAddDate(date);
        audioComments.setContent(content);
        audioComments.setAudio(audio);
        audioComments.setUser(user);
        commentsDao.addAudioComments(audioComments);
    }
    /**************列出某个音频页面中的所有评论***************/
    @RequestMapping(params = "method=listAudioComments",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String listAudioComments(long audioId){
        List<AudioComments> audioCommentses=commentsDao.getAudioCommentsList(audioId);
        if(audioCommentses==null){
            return "";
        }
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<audioCommentses.size();i++){
            Map<String,Object> map =new HashMap<String,Object>();
            AudioComments temp=audioCommentses.get(i);
            map.put("userName",temp.getUser().getUserName());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("content",temp.getContent());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();

        return result;
    }
/***************************教学评论*****************************************************************************/
    /*************************发送教学评论**********************/
    @RequestMapping(params = "method=sendTeachingComments",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public  void sendTeachingComments(long teachingId,String content, HttpSession session )throws Exception{
        User1 user=(User1)session.getAttribute("loginUser");
        Teaching teaching=teachingDao.getTeachingById(teachingId);
        Date date=new Date();
        TeachingComments teachingComments=new TeachingComments();
        teachingComments.setAddDate(date);
        teachingComments.setContent(content);
        teachingComments.setTeaching(teaching);
        teachingComments.setUser(user);
        commentsDao.addTeachingComment(teachingComments);
    }
    /**************列出某个教学页面中的所有评论***************/
    @RequestMapping(params = "method=listTeachingComments",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String listTeachingComments(long teachingId){
        List<TeachingComments> teachingCommentses=commentsDao.getTeachingCommentsList(teachingId);
        if(teachingCommentses==null){
            return "";
        }
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<teachingCommentses.size();i++){
            Map<String,Object> map =new HashMap<String,Object>();
            TeachingComments temp=teachingCommentses.get(i);
            map.put("userName",temp.getUser().getUserName());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("content",temp.getContent());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

}
