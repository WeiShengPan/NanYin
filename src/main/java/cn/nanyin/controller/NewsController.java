package cn.nanyin.controller;

import cn.nanyin.dao.NewsDao;
import cn.nanyin.model.News;
import cn.nanyin.model.NewsSort;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 2015/7/13.
 */
@Controller
public class NewsController {

    @Autowired
    private NewsDao newsDao;

    //显示新闻列表页面
    @RequestMapping(value="nyadmin/newslist",method= RequestMethod.GET)
    public ModelAndView showNewsList()
    {
        ModelAndView model = new ModelAndView("nyadmin/newslist");
        List<News> newsList=newsDao.getNewsList(0, 50);
        model.addObject("newsList",newsList);
        return model;
    }

    //显示增加新闻页面
    @RequestMapping(value="nyadmin/newsaddpage",method=RequestMethod.GET)
    public ModelAndView showNewsAddPage()
    {
        ModelAndView model=new ModelAndView("nyadmin/newsadd");
        List<NewsSort> newsSortList=newsDao.getNewsSortList(0, 50);
        model.addObject("newsSortList",newsSortList);
        return model;
    }

    //添加新闻
    @RequestMapping(value="nyadmin/newsadd",method = RequestMethod.POST)
    public ModelAndView addNews(News news)
    {
        news.setAddDate(new Date());
        newsDao.addNews(news);
        return new ModelAndView("redirect:newsaddpage");
    }

    //显示新闻种类列表页面
    @RequestMapping(value="nyadmin/newssort",method=RequestMethod.GET)
    public ModelAndView showNewsSortList()
    {
        ModelAndView model=new ModelAndView("nyadmin/newssort");

        List<NewsSort> newsSortList=newsDao.getNewsSortList(0,50);
        model.addObject("newsSortList",newsSortList);
        return model;
    }

    //增加新闻种类
    @RequestMapping(value="nyadmin/newssortadd",method=RequestMethod.POST)
    public ModelAndView addNewsSort(NewsSort newsSort)
    {
        newsDao.addNewsSort(newsSort);
        return new ModelAndView("redirect:newssort");
    }

}
