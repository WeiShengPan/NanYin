package cn.nanyin.controller;

import cn.nanyin.dao.NewsDao;
import cn.nanyin.model.News;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/7/29.
 */
@Controller
@RequestMapping("/index.do")
public class ViewController {

    @Autowired
    private NewsDao newsDao;

    @RequestMapping(params = "method=dispatcher")
    public  ModelAndView showNewsList(@RequestParam("page") String page){

        ModelAndView model = new ModelAndView(""+page+"");
        return model;
    }


    @RequestMapping(params = "method=news")
    public void getNews(HttpServletRequest request,HttpServletResponse response)throws  Exception{
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
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out=response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    @RequestMapping(params = "method=newsLink")
    public ModelAndView showNews( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("newsDetail");
        long newsId=Long.parseLong(id);
        News newsDetail=newsDao.getNewsById(newsId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("title",newsDetail.getTitle());
        map.put("author",newsDetail.getAuthor());
        String date=newsDetail.getAddDate().toString();
        map.put("date",date);
        map.put("hits",newsDetail.getHits());
        map.put("source",newsDetail.getSource());
        map.put("content", newsDetail.getContent());

        JSONObject jsonObject=JSONObject.fromObject(map);
        String result=jsonObject.toString();
        model.addObject("news",result);

        News updateNews=newsDetail;
        updateNews.setHits(newsDetail.getHits()+1);
        newsDao.updateNews(updateNews);

        return model;
    }

    @RequestMapping(params = "method=showNewsList")
    @ResponseBody
    public  void showNewsList(HttpServletRequest request,HttpServletResponse response)throws Exception{
        List<News> newsList=newsDao.getNewsList();
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
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out=response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }


}
