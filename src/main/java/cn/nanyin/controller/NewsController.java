package cn.nanyin.controller;

import cn.nanyin.dao.NewsDao;
import cn.nanyin.model.News;
import cn.nanyin.model.NewsSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 2015/7/13.
 */
@Controller
public class NewsController {

    @Autowired
    private NewsDao newsDao;

    /**
     * 显示新闻列表页面
     * @return
     */
    @RequestMapping(value="nyadmin/newslist",method= RequestMethod.GET)
    public ModelAndView showNewsList()
    {
        ModelAndView model = new ModelAndView("nyadmin/newslist");
        List<News> newsList=newsDao.getNewsList(0, 50);
        List<NewsSort> newsSortList=newsDao.getNewsSortList(0,50);
        model.addObject("newsList",newsList);
        model.addObject("newsSortList",newsSortList);
        return model;
    }

    /**
     *
     * @param sortid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "nyadmin/newslistbysort/{sortid}", method = RequestMethod.GET)
    public List<NewsData> getNewsListBySort(@PathVariable Long sortid) {
        NewsSort newsSort=newsDao.getNewsSortById(sortid);
        List<News> newsList=newsSort.getNews();
        List<NewsData> newsDataList=new ArrayList<NewsData>();
        for(int i=0;i<newsList.size();i++)
        {
            News newsTmp=newsList.get(i);
            long id=newsTmp.getId();
            String title=newsTmp.getTitle();
            Date addDate=newsTmp.getAddDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=sdf.format(addDate);
            NewsData newsData=new NewsData(id,title,date,newsSort.getName());
            newsDataList.add(newsData);
        }
        return newsDataList;
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
        //if(newsSort.getUpperNewsSort().getId()!=1)
        int level=newsDao.getNewsSortById(newsSort.getUpperNewsSort().getId()).getLevel();
        newsSort.setLevel(level+1);
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

    //显示修改新闻页面
    @RequestMapping(value="nyadmin/newseditpage",method = RequestMethod.GET)
    public ModelAndView showNewsEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/newsedit");
        News news=newsDao.getNewsById(id);
        model.addObject("news", news);
        List<NewsSort> newsSortList = newsDao.getNewsSortList(0, 50);
        model.addObject("newsSortList", newsSortList);
        return model;
    }

    //修改新闻
    @RequestMapping(value="nyadmin/newsedit",method = RequestMethod.POST)
    public ModelAndView editNews(News news) {
        News targetNews = newsDao.getNewsById(news.getId());
        targetNews.setTitle(news.getTitle());
        targetNews.setAuthor(news.getAuthor());
        targetNews.setSource(news.getSource());
        targetNews.setImage(news.getImage());
        targetNews.setContent(news.getContent());
        targetNews.setNewsSort(newsDao.getNewsSortById(news.getNewsSort().getId()));
        targetNews.setAddDate(newsDao.getNewsById(news.getId()).getAddDate());
        targetNews.setHits(newsDao.getNewsById(news.getId()).getHits());
        newsDao.updateNews(targetNews);
        return new ModelAndView("redirect:newslist");
    }


    @RequestMapping(value="nyadmin/newssorteditpage",method = RequestMethod.GET)
    public ModelAndView showNewsSortEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/newssortedit");
        NewsSort newsSort=newsDao.getNewsSortById(id);
        model.addObject("newsSort", newsSort);
        List<NewsSort> newsSortList=newsDao.getNewsSortList(0, 50);
        model.addObject("newsSortList", newsSortList);
        return model;
    }

    @RequestMapping(value="nyadmin/newssortedit",method = RequestMethod.POST)
    public ModelAndView editNewsSort(NewsSort newsSort)
    {
        NewsSort targetNewsSort=newsDao.getNewsSortById(newsSort.getId());
        targetNewsSort.setName(newsSort.getName());
        targetNewsSort.setUpperNewsSort(newsDao.getNewsSortById(newsSort.getUpperNewsSort().getId()));
        targetNewsSort.setLevel(newsDao.getNewsSortById(newsSort.getId()).getLevel());
        newsDao.updateNewsSort(targetNewsSort);
        return new ModelAndView("redirect:newssort");
    }
}


class NewsData
{
    public long id;
    public String title;
    public String date;
    public String sortName;

    public NewsData(long id,String title,String date,String sortName)
    {
        this.id=id;
        this.title=title;
        this.date=date;
        this.sortName=sortName;
    }

}
