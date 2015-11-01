package cn.nanyin.controller;

import cn.nanyin.dao.*;
import cn.nanyin.model.*;
import com.sun.deploy.net.HttpResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.image.ImageWatched;

import javax.servlet.http.HttpServletRequest;
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
    private ArticleDao articleDao;

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
        List<News> newsList=newsDao.getNewsList(0, 6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<newsList.size();i++){
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
    /*************新闻的缩略图******************/
    @RequestMapping(params = "method=getNewsImages", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getNewsImages( )throws  Exception{
        List<News> newsList=newsDao.getNewsImages(0, 5);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<newsList.size();i++){
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

    /******************************图文乐理******************************************************************************/
    /*****首页中的图文乐理模块（从数据库中读取6条数据）***************/
    @RequestMapping(params = "method=getContent", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getContent(int type){
        String typeName="";
        if(type==1){
            typeName+="南音文库";
        }else if(type==2){
            typeName+="南音图谱";
        }else if(type==3){
            typeName+="南音乐理";
        }
        List<Book> bookList=articleDao.getBookList(typeName,0,6,1);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("type", temp.getBookSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=libraryLink", produces = "plain/text;charset=UTF-8")
    public ModelAndView showLibrary( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("libraryDetail");
        long libraryId=Long.parseLong(id);
        Book bookDetail=articleDao.getBookById(libraryId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("title",bookDetail.getTitle());
        map.put("author",bookDetail.getAuthor());
        String date=bookDetail.getAddDate().toString();
        map.put("date",date);
        map.put("hits", bookDetail.getHits());
        map.put("source", bookDetail.getSource());
        map.put("content", bookDetail.getContent());

        model.addObject("library", map);

        Book updateBook=bookDetail;
        updateBook.setHits(bookDetail.getHits() + 1);
        articleDao.updateBook(updateBook);

        return model;

    }

    @RequestMapping(params = "method=galleryLink", produces = "plain/text;charset=UTF-8")
    public ModelAndView showGallery( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("galleryDetail");
        long galleryId=Long.parseLong(id);
        Book bookDetail=articleDao.getBookById(galleryId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("title",bookDetail.getTitle());
        map.put("author",bookDetail.getAuthor());
        String date=bookDetail.getAddDate().toString();
        map.put("date",date);
        map.put("hits", bookDetail.getHits());
        map.put("source", bookDetail.getSource());
        map.put("content", bookDetail.getContent());

        model.addObject("gallery", map);

        Book updateBook=bookDetail;
        updateBook.setHits(bookDetail.getHits() + 1);
        articleDao.updateBook(updateBook);

        return model;

    }

    /*************图库的缩略图******************/
    @RequestMapping(params = "method=getGalleryImages", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getGalleryImages( )throws  Exception{
        List<Book> bookList=articleDao.getGalleryImages(0,10);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<bookList.size();i++){
            Book temp=bookList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id", temp.getId());
            map.put("path", temp.getFile());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }


    @RequestMapping(params = "method=musicTheoryLink", produces = "plain/text;charset=UTF-8")
    public ModelAndView showMusicTheory( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("musicTheoryDetail");
        long musicTheoryId=Long.parseLong(id);
        Book bookDetail=articleDao.getBookById(musicTheoryId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("title",bookDetail.getTitle());
        map.put("author",bookDetail.getAuthor());
        String date=bookDetail.getAddDate().toString();
        map.put("date",date);
        map.put("hits", bookDetail.getHits());
        map.put("source", bookDetail.getSource());
        map.put("content", bookDetail.getContent());

        model.addObject("musicTheory", map);

        Book updateBook=bookDetail;
        updateBook.setHits(bookDetail.getHits() + 1);
        articleDao.updateBook(updateBook);

        return model;

    }


/******************************音频******************************************************************************/
    /*****首页中的音频模块（从数据库中读取6条音频）***************/
    @RequestMapping(params = "method=getAudios", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getAudios(){
        List<Audio> audioList=audioDao.getAudioList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<audioList.size();i++){
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
    public ModelAndView showAudio( @RequestParam("id") String id,HttpSession session){
        ModelAndView model = new ModelAndView("audioDetail");
        long audioId=Long.parseLong(id);
        Audio audioDetail=audioDao.getAudioById(audioId);

        Map<String,Object> map=new HashMap<String,Object>();

        User1 user=(User1)session.getAttribute("loginUser");
        if(user!=null){
            map.put("flag",1);
            Audio updateAideo=audioDetail;
            updateAideo.setHits(audioDetail.getHits() + 1);
            audioDao.updateAudio(updateAideo);
        }else{
            map.put("flag",0);
        }

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
        map.put("gcp", audioDetail.getGcp());
        map.put("jp", audioDetail.getJp());
        model.addObject("audio", map);

        return model;
    }

/**************************************视频***************************************************************************/
    /*****首页中的视频模块（从数据库中读取6条视频）***************/
    @RequestMapping(params = "method=getVideos", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getVideos(){
        List<Video> videoList=videoDao.getVideoList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<videoList.size();i++){
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
    public ModelAndView showVideo( @RequestParam("id") String id,HttpSession session){
        ModelAndView model = new ModelAndView("videoDetail");
        long videoId=Long.parseLong(id);
        Video videoDetail=videoDao.getVideoById(videoId);
        Map<String,Object> map=new HashMap<String,Object>();
        User1 user=(User1)session.getAttribute("loginUser");
        if(user!=null){
            map.put("flag",1);
            Video updateVideo=videoDetail;
            updateVideo.setHits(videoDetail.getHits() + 1);
            videoDao.updateVideo(updateVideo);
        }else{
            map.put("flag",0);
        }
        map.put("id",videoDetail.getId());
        map.put("title", videoDetail.getTitle());
        map.put("singer", videoDetail.getSinger());
        String date=videoDetail.getAddDate().toString();
        map.put("date",date);
        map.put("path",videoDetail.getPath());
        map.put("hits", videoDetail.getHits());
        map.put("player",videoDetail.getPlayer());
        map.put("cameraman", videoDetail.getCameraman());
        map.put("producer", videoDetail.getProducer());
        map.put("content",videoDetail.getContent());
        map.put("gcp", videoDetail.getGcp());
        map.put("jp", videoDetail.getJp());
        model.addObject("video", map);

        return model;
    }

    /******************************名录******************************************************************************/
    /*****首页中的名录模块（从数据库中读取6条数据）***************/
    @RequestMapping(params = "method=getDirectory", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getDirectory(){
        List<Directory> directories=directoryDao.getDirectoryList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<directories.size();i++){
            Directory temp=directories.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("type",temp.getDirectorySort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=directoryLink")
    public ModelAndView showDirectory( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("directoryDetail");
        long directoryId=Long.parseLong(id);
        Directory directoryDetail=directoryDao.getDirectoryById(directoryId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",directoryDetail.getId());
        map.put("title", directoryDetail.getTitle());
        map.put("author",directoryDetail.getAuthor());
        String date=directoryDetail.getAddDate().toString();
        map.put("date", date);
        map.put("hits", directoryDetail.getHits());
        map.put("content",directoryDetail.getContent());
        map.put("source", directoryDetail.getSource());

        model.addObject("directory", map);

        Directory updateDirectory=directoryDetail;
        updateDirectory.setHits(directoryDetail.getHits() + 1);
        directoryDao.updateDirectory(updateDirectory);

        return model;
    }

    /*****首页中的商城模块（从数据库中读取6条信息）***************/
    @RequestMapping(params = "method=getProducts", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getProducts(){
        List<Product> productList=productDao.getProductList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<productList.size();i++){
            Product temp=productList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getName());
            map.put("type",temp.getProductSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=showProductList", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String showProductionList(int type){
        String typeName="";
        if(type==1){
            typeName+="南音书籍";
        }else if(type==2){
            typeName+="南音音像";
        }else if(type==3){
            typeName+="南音乐器";
        }else if(type==4){
            typeName+="南音伴奏";
        }else if(type==5){
            typeName+="专辑录制";
        } else if(type==6){
            typeName+="文创艺品 ";
        }else if(type==7){
            typeName+="其他配件";
        }
        List<Product> productList=productDao.getProductList(typeName,0,5);
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

    @RequestMapping(params = "method=productLink")
    public ModelAndView showProduct( @RequestParam("id") String id){
        ModelAndView model = new ModelAndView("productDetail");
        long productId=Long.parseLong(id);
        Product productDetail=productDao.getProductById(productId);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name", productDetail.getName());
        map.put("price", productDetail.getPrice());
        String date=productDetail.getAddDate().toString();
        map.put("date",date);
        map.put("path",productDetail.getFile());
        map.put("link", productDetail.getLink());
        map.put("content",productDetail.getContent());

        model.addObject("product", map);
        return model;
    }

    /*************商城的缩略图******************/
    @RequestMapping(params = "method=getProductImages", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getProductImages( )throws  Exception{
        List<Product> productList=productDao.getProductImages(0, 10);
        System.out.println("共有图片："+productList.size());
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<productList.size();i++){
            Product temp=productList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id", temp.getId());
            map.put("path", temp.getFile());
            map.put("name",temp.getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }


    /*****首页中的社团模块（从数据库中读取6条信息）***************/
    @RequestMapping(params = "method=getColleges", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getColleges(){
        List<College> collegeList=collegeDao.getCollegeList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<collegeList.size();i++){
            College temp=collegeList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getName());
            map.put("type",temp.getCollegeArea().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=collegeLink")
    public ModelAndView showCollege( @RequestParam("id") String id){
        long productId=Long.parseLong(id);
        College collegeDetail=collegeDao.getCollegeById(productId);
        boolean vip=collegeDetail.isVip();
        String a="";
        if(vip){
            a+="collegeDetail1";
        }else{
            a+="collegeDetail2";
        }
        ModelAndView model = new ModelAndView(a);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name", collegeDetail.getName());
        map.put("members", collegeDetail.getMainMembers());
        String activeDate=collegeDetail.getActivityDate();
        map.put("activeDate",activeDate);
        map.put("leader",collegeDetail.getLeader());
        map.put(" exLeader",collegeDetail.getExLeader());
        map.put("email",collegeDetail.getEmail());
        String formDate=collegeDetail.getFormDate();
        map.put("formDate",formDate);
        map.put("address",collegeDetail.getAddress());
        map.put("contact",collegeDetail.getContact());
        map.put("tel",collegeDetail.getTelephone());
        map.put("memberNum",collegeDetail.getMemberNum());
        map.put("introduction",collegeDetail.getIntroduction());
        map.put("vip",collegeDetail.isVip());
        map.put("path",collegeDetail.getFile());
        map.put("organization",collegeDetail.getOrganizationStructure());
        map.put("activeImages",collegeDetail.getActiveImages());

        model.addObject("college", map);
        return model;
    }

    /*****首页中的教学模块（从数据库中读取6条信息）***************/
    @RequestMapping(params = "method=getTeaching", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getTeaching(){
        List<Teaching> teachingList=teachingDao.getTeachingList(0,6);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<teachingList.size();i++){
            Teaching temp=teachingList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("type",temp.getTeachingSort().getName());
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }

    @RequestMapping(params = "method=teachingLink")
    public ModelAndView showTeaching( @RequestParam("id") String id,HttpSession session){
        ModelAndView model = new ModelAndView("teachingDetail");
        long teachingId=Long.parseLong(id);
        Teaching teachingDetail=teachingDao.getTeachingById(teachingId);
        Map<String,Object> map=new HashMap<String,Object>();

        User1 user=(User1)session.getAttribute("loginUser");
        if(user!=null&&user.getState()){
            if(user.getLevel()){
                map.put("flag",1);
                Teaching updateTeaching=teachingDetail;
                updateTeaching.setHits(teachingDetail.getHits() + 1);
                teachingDao.updateTeaching(updateTeaching);
            }else{
                map.put("flag",0);
            }
        }else{
            map.put("flag",0);
        }

        map.put("id",teachingDetail.getId());
        map.put("title",teachingDetail.getTitle());
        map.put("teacher",teachingDetail.getTeacher());
        String date=teachingDetail.getAddDate().toString();
        map.put("date", date);
        map.put("path",teachingDetail.getFile());
        map.put("source",teachingDetail.getSource());
        map.put("hits",teachingDetail.getHits());
        map.put("content", teachingDetail.getContent());
        map.put("gcp", teachingDetail.getGcp());
        map.put("jp", teachingDetail.getJp());
        model.addObject("teaching", map);



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

    @RequestMapping(params = "method=searchContent",produces = "plain/text;charset=UTF-8")
    public  ModelAndView searchContent(HttpServletRequest request){

        String text=request.getParameter("searchContent");

        ModelAndView  model = new ModelAndView("searchList");
        int type=Integer.parseInt(request.getParameter("selectItem"));

        System.out.println(text+","+type);

        Map<String,Object> dataMap=new HashMap<String,Object>();

        String typeName="";
        if(type==1){
            typeName+="南音文库";
            List<Book> bookList=articleDao.findBooks(typeName, text);
            List<Map> list=new ArrayList<Map>();
            for(int i=0;i<bookList.size();i++){
                Book book=bookList.get(i);
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("id",book.getId());
                map.put("title",book.getTitle());
                String date=book.getAddDate().toString();
                map.put("date",date);
                map.put("hits",book.getHits());
                list.add(map);
            }
            JSONArray jsonArray=JSONArray.fromObject(list);
            String result=jsonArray.toString();
            dataMap.put("dataList",result);
            dataMap.put("type",type);
            model.addObject("data", dataMap);
        }else if(type==2){
            List<Video> videoList=videoDao.findVideos(text);
            List<Map> list=new ArrayList<Map>();
            for(int i=0;i<videoList.size();i++){
                Video temp=videoList.get(i);
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("id", temp.getId());
                map.put("title", temp.getTitle());
                String date=temp.getAddDate().toString();
                map.put("date",date);
                map.put("hits", temp.getHits());
                list.add(map);
            }
            JSONArray jsonArray=JSONArray.fromObject(list);
            String result=jsonArray.toString();
            dataMap.put("dataList",result);
            dataMap.put("type",type);
            model.addObject("data",dataMap);
        }else if(type==3){
            List<Audio> audioList=audioDao.findAudios(text);
            List<Map> list=new ArrayList<Map>();
            for(int i=0;i<audioList.size();i++){
                Audio temp=audioList.get(i);
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("id", temp.getId());
                map.put("title", temp.getTitle());
                String date=temp.getAddDate().toString();
                map.put("date",date);
                map.put("hits", temp.getHits());
                list.add(map);
            }
            JSONArray jsonArray=JSONArray.fromObject(list);
            String result=jsonArray.toString();
            dataMap.put("dataList",result);
            dataMap.put("type",type);
            model.addObject("data",dataMap);
        }

        return model;

    }


}
