package cn.nanyin.controller;

import cn.nanyin.dao.*;
import cn.nanyin.model.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    private DirectoryDao directoryDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private TeachingDao teachingDao;

    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private ArticleDao articleDao;

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

    /***********************************图文乐理列表************************************************************/
    @RequestMapping(params = "method=showLibraryList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showLibraryList(int type,int curPage,int pageSize)throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        int totalNum=0;
        int totalPage=0;
        if(type==0){
            String typeName="南音文库";
            totalNum=articleDao.getNum(typeName,1);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName, start, pageSize, 1);
        }else if(type==1){
            String typeName="南音曲词";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==2){
            String typeName="南音论文";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==3){
            String typeName="南音钩沉";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==4){
            String typeName="南音文萃";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==5){
            String typeName="南音轶事";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==6){
            String typeName="诗词楹联";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }

        List<Map> list=new ArrayList<Map>();

        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            String date=temp.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title", temp.getTitle());
            map.put("author", temp.getAuthor());
            map.put("date",date);
            map.put("hits",temp.getHits());
            map.put("type",temp.getBookSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=libraryTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String libraryTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            String typeName="南音文库";
            bookList=articleDao.getBookList(typeName, start, pageSize, 1);
        }else if(type==1){
            String typeName="南音曲词";
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==2){
            String typeName="南音论文";
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==3){
            String typeName="南音钩沉";
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==4){
            String typeName="南音文萃";
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==5){
            String typeName="南音轶事";
            bookList=articleDao.getBookList(typeName, start, pageSize, 0);
        }else if(type==6){
            String typeName="诗词楹联";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }

        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            String date=temp.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title", temp.getTitle());
            map.put("author", temp.getAuthor());
            map.put("date",date);
            map.put("hits",temp.getHits());
            map.put("type",temp.getBookSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;

    }

    @RequestMapping(params = "method=showGalleryList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showGalleryList(int type,int curPage,int pageSize)throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        int totalNum=0;
        int totalPage=0;
        if(type==0){
            String typeName="南音图谱";
            totalNum=articleDao.getNum(typeName,1);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,1);
        }else if(type==1){
            String typeName="南音工乂谱";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==2){
            String typeName="南音简谱";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==3){
            String typeName="电子相册";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==4){
            String typeName="专题剪影";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==5){
            String typeName="珍贵图录";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==6){
            String typeName="名家题词";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }

        List<Map> list=new ArrayList<Map>();

        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            String date=temp.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title", temp.getTitle());
            map.put("author", temp.getAuthor());
            map.put("date",date);
            map.put("hits",temp.getHits());
            map.put("type",temp.getBookSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=galleryTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String galleryTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            String typeName="南音图谱";
            bookList=articleDao.getBookList(typeName,start,pageSize,1);
        }else if(type==1){
            String typeName="南音工乂谱";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==2){
            String typeName="南音简谱";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==3){
            String typeName="电子相册";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==4){
            String typeName="专题剪影";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==5){
            String typeName="珍贵图录";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==6){
            String typeName="名家题词";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }

        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            String date=temp.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title", temp.getTitle());
            map.put("author", temp.getAuthor());
            map.put("date",date);
            map.put("hits",temp.getHits());
            map.put("type",temp.getBookSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;

    }

    @RequestMapping(params = "method=showMusicTheoryList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showMusicTheoryList(int type,int curPage,int pageSize)throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        int totalNum=0;
        int totalPage=0;
        if(type==0){
            String typeName="南音乐理";
            totalNum=articleDao.getNum(typeName,1);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,1);
        }else if(type==1){
            String typeName="南音常识";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==2){
            String typeName="工乂谱简介";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==3){
            String typeName="南音乐器";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==4){
            String typeName="南音唱腔";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==5){
            String typeName="滚门曲牌";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==6){
            String typeName="南音本事";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }

        List<Map> list=new ArrayList<Map>();

        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            String date=temp.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title", temp.getTitle());
            map.put("author", temp.getAuthor());
            map.put("date",date);
            map.put("hits",temp.getHits());
            map.put("type",temp.getBookSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=musicTheoryTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String musicTheoryTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            String typeName="南音乐理";
            bookList=articleDao.getBookList(typeName,start,pageSize,1);
        }else if(type==1){
            String typeName="南音常识";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==2){
            String typeName="工乂谱简介";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==3){
            String typeName="南音乐器";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==4){
            String typeName="南音唱腔";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==5){
            String typeName="滚门曲牌";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==6){
            String typeName="南音本事";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }

        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            String date=temp.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title", temp.getTitle());
            map.put("author", temp.getAuthor());
            map.put("date",date);
            map.put("hits",temp.getHits());
            map.put("type",temp.getBookSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;

    }


    @RequestMapping(params = "method=showDirectoryList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showDirectoryList(int type,int curPage,int pageSize)throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        int totalNum=0;
        int totalPage=0;
        if(type==0){
            String typeName="南音名录";
            totalNum=articleDao.getNum(typeName,1);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,1);
        }else if(type==1){
            String typeName="南音人物志";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==2){
            String typeName="南音传承人";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==3){
            String typeName="南音新秀榜";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==4){
            String typeName="学术界名录";
            totalNum=articleDao.getNum(typeName,0);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }

        List<Map> list=new ArrayList<Map>();

        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            String date=temp.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title", temp.getTitle());
            map.put("author", temp.getAuthor());
            map.put("date",date);
            map.put("hits",temp.getHits());
            map.put("type",temp.getBookSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }


    @RequestMapping(params = "method=directoryTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String directoryTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Book> bookList=new ArrayList<Book>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            String typeName="南音名录";
            bookList=articleDao.getBookList(typeName,start,pageSize,1);
        }else if(type==1){
            String typeName="南音人物志";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==2){
            String typeName="南音传承人";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==3){
            String typeName="南音新秀榜";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }else if(type==4){
            String typeName="学术界名录";
            bookList=articleDao.getBookList(typeName,start,pageSize,0);
        }

        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            String date=temp.getAddDate().toString();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title", temp.getTitle());
            map.put("author", temp.getAuthor());
            map.put("date",date);
            map.put("hits",temp.getHits());
            map.put("type",temp.getBookSort().getName());
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
            String typeName="南音曲库";
            totalNum=audioDao.getNums(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            audioList=audioDao.getAudioLists(typeName,start,pageSize);
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
            String typeName="南音曲库";
            audioList=audioDao.getAudioLists(typeName,start,pageSize);
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

    /***********************************诗词音画************************************************************/
    @RequestMapping(params = "method=showPoetryList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showPoetryList(int type,int curPage,int pageSize)throws Exception{

        int totalNum=0;
        int totalPage=0;

        List<Audio> audioList=new ArrayList<Audio>();
        if(type==0){
            String typeName="诗词音画";
            totalNum=audioDao.getNums(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            audioList= audioDao.getAudioLists(typeName, start, pageSize);
        }else if(type==1){
            String typeName="唐诗宋词南管唱";
            totalNum = audioDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start = (curPage - 1) * pageSize;
            audioList=audioDao.getAudioList(typeName, start, pageSize);
        }else if(type==2){
            String typeName = "丁马成南音作品";
            totalNum = audioDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start = (curPage - 1) * pageSize;
            audioList=audioDao.getAudioList(typeName, start, pageSize);
        }else if(type==3){
            String typeName="郑梦集茶乡清曲";
            totalNum = audioDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start = (curPage - 1) * pageSize;
            audioList=audioDao.getAudioList(typeName, start, pageSize);
        }else if(type==4){
            String typeName="陈丽华台湾风情";
            totalNum = audioDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start = (curPage - 1) * pageSize;
            audioList=audioDao.getAudioList(typeName, start, pageSize);
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


    @RequestMapping(params = "method=poetryTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String poetryTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Audio> audioList=new ArrayList<Audio>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            String typeName="诗词音画";
            audioList=audioDao.getAudioLists(typeName, start, pageSize);
        }else if(type==1){
            String typeName="唐诗宋词南管唱";
            audioList=audioDao.getAudioList(typeName, start, pageSize);
        }else if(type==2){
            String typeName="丁马成南音作品";
            audioList=audioDao.getAudioList(typeName, start, pageSize);
        }else if(type==3){
            String typeName="郑梦集茶乡清曲";
            audioList=audioDao.getAudioList(typeName, start, pageSize);
        }else if(type==4){
            String typeName="陈丽华台湾风情";
            audioList=audioDao.getAudioList(typeName, start, pageSize);
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




    /***********************************名录列表************************************************************/
   /* @RequestMapping(params = "method=showDirectoryList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showDirectoryList(int type,int curPage,int pageSize)throws Exception{

        int totalNum=0;
        int totalPage=0;

        List<Directory> directoryList=new ArrayList<Directory>();
        if(type==0){
            totalNum=directoryDao.getNum();
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            directoryList=directoryDao.getDirectoryList(start,pageSize);
        }else if(type==1){
            String typeName="南音人物志";
            totalNum=directoryDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            directoryList=directoryDao.getDirectoryList(typeName,start, pageSize);
        }else if(type==2){
            String typeName="南音传承人";
            totalNum=directoryDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            directoryList=directoryDao.getDirectoryList(typeName, start, pageSize);
        }else if(type==3){
            String typeName="南音新秀榜";
            totalNum=directoryDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            directoryList=directoryDao.getDirectoryList(typeName, start, pageSize);
        }else if(type==4){
            String typeName="学术界名录";
            totalNum=directoryDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            directoryList=directoryDao.getDirectoryList(typeName, start, pageSize);
        }else if(type==5){
            String typeName="本网机构";
            totalNum=directoryDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            directoryList=directoryDao.getDirectoryList(typeName, start, pageSize);
        }
        List<Map> list=new ArrayList<Map>();
        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<directoryList.size();i++){
            Directory temp=directoryList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("author",temp.getAuthor());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("hits",temp.getHits());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=directoryTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String directoryTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Directory> directoryList=new ArrayList<Directory>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            directoryList=directoryDao.getDirectoryList(start,pageSize);
        }else if(type==1){
            String typeName="南音人物志";
            directoryList=directoryDao.getDirectoryList(typeName,start, pageSize);
        }else if(type==2){
            String typeName="南音传承人";
            directoryList=directoryDao.getDirectoryList(typeName,start, pageSize);
        }else if(type==3){
            String typeName="南音新秀榜";
            directoryList=directoryDao.getDirectoryList(typeName,start, pageSize);
        }else if(type==4){
            String typeName="学术界名录";
            directoryList=directoryDao.getDirectoryList(typeName,start, pageSize);
        }else if(type==5){
            String typeName="本网机构";
            directoryList=directoryDao.getDirectoryList(typeName,start, pageSize);
        }
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<directoryList.size();i++){
            Directory temp=directoryList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("author",temp.getAuthor());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("hits",temp.getHits());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;

    }*/

    /***********************************商城列表************************************************************/
    @RequestMapping(params = "method=showProductList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showProductList(int type,int curPage,int pageSize)throws Exception{
        int totalNum=0;
        int totalPage=0;
        List<Product> productList=new ArrayList<Product>();
        if(type==1){
            String typeName="南音书籍";
            totalNum=productDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==2){
            String typeName="南音音像";
            totalNum=productDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if (type==3){
            String typeName="南音乐器";
            totalNum=productDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==4){
            String typeName="南音伴奏";
            totalNum=productDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==5){
            String typeName="专辑录制";
            totalNum=productDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==6) {
            String typeName = "文创艺品";
            totalNum = productDao.getNum(typeName);
            double re = (double) totalNum / pageSize;
            totalPage = (int) Math.ceil(re);
            int start = (curPage - 1) * pageSize;
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==7){
            String typeName="其他配件";
            totalNum=productDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            productList=productDao.getProductList(typeName, start,pageSize);
        }
        List<Map> list=new ArrayList<Map>();
        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<productList.size();i++){
            Product temp=productList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("name",temp.getName());
            map.put("path",temp.getFile());
            map.put("price",temp.getPrice());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=productTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String productTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Product> productList=new ArrayList<Product>();
        int start=(curPage-1)*pageSize;
        if(type==1){
            String typeName="南音书籍";
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==2){
            String typeName="南音音像";
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if (type==3){
            String typeName="南音乐器";
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==4){
            String typeName="南音伴奏";
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==5){
            String typeName="专辑录制";
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==6) {
            String typeName = "文创艺品";
            productList=productDao.getProductList(typeName, start,pageSize);
        }else if(type==7){
            String typeName="其他配件";
            productList=productDao.getProductList(typeName, start,pageSize);
        }
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<productList.size();i++){
            Product temp=productList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("name",temp.getName());
            map.put("path",temp.getFile());
            map.put("price",temp.getPrice());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    /***********************************教学列表************************************************************/
    @RequestMapping(params = "method=showTeachingList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showTeachingList(int type,int curPage,int pageSize)throws Exception{
        int totalNum=0;
        int totalPage=0;
        List<Teaching> teachingList=new ArrayList<Teaching>();
        if(type==0){
            totalNum=teachingDao.getNum();
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            teachingList=teachingDao.getTeachingList( start,pageSize);
        }else if(type==1){
            String typeName="工乂谱识谱";
            totalNum=teachingDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            teachingList=teachingDao.getTeachingList(typeName, start,pageSize);
        }else if(type==2){
            String typeName="乐器技法";
            totalNum=teachingDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            teachingList=teachingDao.getTeachingList(typeName, start,pageSize);
        }else if(type==3){
            String typeName="唱腔指导";
            totalNum=teachingDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            teachingList=teachingDao.getTeachingList(typeName, start,pageSize);
        }else if(type==4){
            String typeName="名师访谈";
            totalNum=teachingDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            teachingList=teachingDao.getTeachingList(typeName, start,pageSize);
        }
        List<Map> list=new ArrayList<Map>();
        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<teachingList.size();i++){
            Teaching temp=teachingList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("teacher",temp.getTeacher());
            map.put("source",temp.getSource());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("hits",temp.getHits());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=teachingTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String teachingTurnPage(int type,int curPage,int pageSize)throws Exception{
        List<Teaching> teachingList=new ArrayList<Teaching>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            teachingList=teachingDao.getTeachingList( start,pageSize);
        }else if(type==1){
            String typeName="工乂谱识谱";
            teachingList=teachingDao.getTeachingList(typeName, start,pageSize);
        }else if(type==2){
            String typeName="乐器技法";
            teachingList=teachingDao.getTeachingList(typeName, start,pageSize);
        }else if(type==3){
            String typeName="唱腔指导";
            teachingList=teachingDao.getTeachingList(typeName, start,pageSize);
        }else if(type==4){
            String typeName="名师访谈";
            teachingList=teachingDao.getTeachingList(typeName, start,pageSize);
        }
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<teachingList.size();i++){
            Teaching temp=teachingList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("teacher",temp.getTeacher());
            map.put("source",temp.getSource());
            String date=temp.getAddDate().toString();
            map.put("date",date);
            map.put("hits",temp.getHits());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;

    }



    /***********************************社团列表************************************************************/
    @RequestMapping(params = "method=showCollegeList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showCollegeList(int type,int curPage,int pageSize)throws Exception {
        int totalNum = 0;
        int totalPage = 0;
        List<College> collegeList=new ArrayList<College>();
        if(type==0){
            totalNum=collegeDao.getNum();
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            collegeList=collegeDao.getCollegeList( start,pageSize);
        }else if(type==1){
            String typeName="荣誉社团";
            totalNum=collegeDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }else if(type==2){
            String typeName="国内社团";
            totalNum=collegeDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }else if(type==3){
            String typeName="港澳台社团";
            totalNum=collegeDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }else if(type==4){
            String typeName="东南亚社团";
            totalNum=collegeDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }else if(type==5){
            String typeName="传习培训";
            totalNum=collegeDao.getNum(typeName);
            double re=(double)totalNum/pageSize;
            totalPage=(int)Math.ceil(re);
            int start=(curPage-1)*pageSize;
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }
        List<Map> list=new ArrayList<Map>();
        Map<String,Object> pageInfo=new HashMap<String,Object>();
        pageInfo.put("totalNum",totalNum);
        pageInfo.put("totalPage",totalPage);
        list.add(pageInfo);

        for(int i=0;i<collegeList.size();i++){
            College temp=collegeList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("name",temp.getName());
            map.put("address",temp.getAddress());
            map.put("vip",temp.isVip());
            map.put("tel",temp.getTelephone());
            map.put("contact",temp.getContact());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=collegeTurnPage", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String collegeTurnPage(int type,int curPage,int pageSize)throws Exception {
        List<College> collegeList=new ArrayList<College>();
        int start=(curPage-1)*pageSize;
        if(type==0){
            collegeList=collegeDao.getCollegeList(start,pageSize);
        }else if(type==1){
            String typeName="荣誉社团";
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }else if(type==2){
            String typeName="国内社团";
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }else if(type==3){
            String typeName="港澳台社团";
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }else if(type==4){
            String typeName="东南亚社团";
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }else if(type==5){
            String typeName="传习培训";
            collegeList=collegeDao.getCollegeList(typeName, start,pageSize);
        }
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<collegeList.size();i++){
            College temp=collegeList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("name",temp.getName());
            map.put("address",temp.getAddress());
            map.put("vip",temp.isVip());
            map.put("tel",temp.getTelephone());
            map.put("contact",temp.getContact());
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
