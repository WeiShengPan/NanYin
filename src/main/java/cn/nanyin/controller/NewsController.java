package cn.nanyin.controller;

import cn.nanyin.dao.NewsDao;
import cn.nanyin.model.News;
import cn.nanyin.model.NewsSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView addNews(News news,BindingResult result)
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
        model.addObject("newsSortList", newsSortList);
        return model;
    }

    //增加新闻种类
    @RequestMapping(value="nyadmin/newssortadd",method=RequestMethod.POST)
    public ModelAndView addNewsSort(NewsSort newsSort)
    {
        if(newsSort.getUpperNewsSort().getId()!=1)
            newsSort.setLevel(newsSort.getUpperNewsSort().getLevel()+1);
        newsDao.addNewsSort(newsSort);
        return new ModelAndView("redirect:newssort");
    }

    //删除新闻
    @RequestMapping(value="nyadmin/newsdelete",method = RequestMethod.GET)
    public ModelAndView deleteNews(long id)
    {
        News news = newsDao.getNewsById(id);
        newsDao.deleteNews(news);
        return new ModelAndView("redirect:newslist");
    }

    //删除新闻种类
    @RequestMapping(value="nyadmin/newssortdelete",method = RequestMethod.GET)
    public ModelAndView deleteNewsSort(long id)
    {
        NewsSort newsSort=newsDao.getNewsSortById(id);

        //级联删除所有该种类新闻
        List<News> newsList=newsSort.getNews();
        for(int i=0;i<newsList.size();i++)
        {
            News newsTmp1 =newsList.get(i);
            newsSort.removeNews(newsTmp1);
            newsTmp1.setNewsSort(null);
            newsDao.updateNews(newsTmp1);
            newsDao.deleteNews(newsTmp1);
        }

        //级联删除所有下层新闻种类
        List<NewsSort> lowerNewsSortList=newsSort.getLowerNewsSortList();
        for(int i=0;i<lowerNewsSortList.size();i++)
        {
            NewsSort newsSortTmp=lowerNewsSortList.get(i);
            List<News> lowerNewsList=newsSortTmp.getNews();
            for(int j=0;j<lowerNewsList.size();j++)
            {
                News newsTmp2=lowerNewsList.get(i);
                newsSortTmp.removeNews(newsTmp2);
                newsTmp2.setNewsSort(null);
                newsDao.updateNews(newsTmp2);
                newsDao.deleteNews(newsTmp2);
            }
            newsSort.removeNewsSort(newsSortTmp);
            newsSortTmp.setUpperNewsSort(null);
            newsDao.updateNewsSort(newsSortTmp);
            newsDao.deleteNewsSort(newsSortTmp);
        }

        newsDao.deleteNewsSort(newsSort);
        return new ModelAndView("redirect:newssort");
    }

}
