package cn.nanyin.controller;

import cn.nanyin.dao.NewsDao;
import cn.nanyin.model.NewsSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ���� on 2015/7/13.
 */
@Controller
public class NewsController {

    @Autowired
    private NewsDao newsDao;

    @RequestMapping(value="/nyadmin/newslist",method= RequestMethod.GET)
    public ModelAndView showNewsList()
    {
        ModelAndView model = new ModelAndView("nyadmin/newslist");
        //List<News> newsList=newsDao.getNewsList(0,50);
        //model.addObject("newsList",newsList);
        return model;
    }

    @RequestMapping(value="nyadmin/newsadd",method = RequestMethod.GET)
    public ModelAndView addNews()
    {
        return new ModelAndView("nyadmin/newsadd");
    }

    @RequestMapping(value="nyadmin/newssort",method=RequestMethod.GET)
    public ModelAndView showNewsSortList()
    {
        ModelAndView model;
        NewsSort ns=new NewsSort();
        ns.setLevel(1);
        ns.setName("啊");
        ns.setPriority(1);
        ns.setState(1);
        ns.setUpperId(1);
        newsDao.addNewsSort(ns);
        //List<NewsSort> newsSortList=newsDao.getNewsSortList(0,50);
        // model.addObject("newsSortList",newsSortList);
        return new ModelAndView("nyadmin/newssort");
    }

}
