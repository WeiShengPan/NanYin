package cn.nanyin.controller;

import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.dao.VideoDao;
import cn.nanyin.model.Video;
import cn.nanyin.model.VideoSort;
import cn.nanyin.util.FileUpload;
import cn.nanyin.util.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 玮晟 on 2015/8/20.
 */

@Controller
@AdminAuthority(authorityTypes = AuthorityType.VIDEO_MANAGEMENT)
public class VideoController {
    @Autowired
    private VideoDao videoDao;

    @RequestMapping(value = "nyadmin/videolist",method = RequestMethod.GET)
    public ModelAndView showVideoList()
    {
        ModelAndView model=new ModelAndView("nyadmin/videolist");
        List<Video> videoList=videoDao.getVideoList(0,50);
        List<VideoSort> videoSortList=videoDao.getVideoSortList(0,50);
        model.addObject("videoList",videoList);
        model.addObject("videoSortList",videoSortList);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/videolistbysort/{sortid}", method = RequestMethod.GET)
    public List<VideoData> getAudioListBySort(@PathVariable Long sortid) {
        VideoSort videoSort=videoDao.getVideoSortById(sortid);
        List<Video> videoList=videoSort.getVideos();
        List<VideoData> videoDataList=new ArrayList<VideoData>();
        for(int i=0;i<videoList.size();i++)
        {
            Video videoTmp=videoList.get(i);
            long id=videoTmp.getId();
            String title=videoTmp.getTitle();
            Date addDate=videoTmp.getAddDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=sdf.format(addDate);
            VideoData videoData=new VideoData(id,title,date,videoSort.getName());
            videoDataList.add(videoData);
        }
        return videoDataList;
    }

    //显示增加新闻页面
    @RequestMapping(value="nyadmin/videoaddpage",method=RequestMethod.GET)
    public ModelAndView showVideoAddPage()
    {
        ModelAndView model=new ModelAndView("nyadmin/videoadd");
        List<VideoSort> videoSortList=videoDao.getVideoSortList(0, 50);
        model.addObject("videoSortList",videoSortList);
        return model;
    }

    //添加新闻
    @RequestMapping(value="nyadmin/videoadd",method = RequestMethod.POST)
    public ModelAndView addVideo(Video video,BindingResult result)
    {
        video.setAddDate(new Date());
        videoDao.addVideo(video);
        return new ModelAndView("redirect:videoaddpage");
    }

    //显示新闻种类列表页面
    @RequestMapping(value="nyadmin/videosort",method=RequestMethod.GET)
    public ModelAndView showVideoSortList()
    {
        ModelAndView model=new ModelAndView("nyadmin/videosort");

        List<VideoSort> videoSortList=videoDao.getVideoSortList(0, 50);
        model.addObject("videoSortList", videoSortList);
        return model;
    }

    //增加新闻种类
    @RequestMapping(value="nyadmin/videosortadd",method=RequestMethod.POST)
    public ModelAndView addVideoSort(VideoSort videoSort)
    {
        //if(newsSort.getUpperNewsSort().getId()!=1)
        int level=videoDao.getVideoSortById(videoSort.getUpperVideoSort().getId()).getLevel();
        videoSort.setLevel(level + 1);
        videoDao.addVideoSort(videoSort);
        return new ModelAndView("redirect:videosort");
    }

    //删除新闻
    @RequestMapping(value="nyadmin/videodelete",method = RequestMethod.GET)
    public ModelAndView deleteVideo(long id)
    {
        Video video = videoDao.getVideoById(id);
        videoDao.deleteVideo(video);
        return new ModelAndView("redirect:videolist");
    }

    //删除新闻种类
    @RequestMapping(value="nyadmin/videosortdelete",method = RequestMethod.GET)
    public ModelAndView deleteVideoSort(long id)
    {
        VideoSort videoSort=videoDao.getVideoSortById(id);

        //级联删除所有该种类新闻
        List<Video> videoList=videoSort.getVideos();
        for(int i=0;i<videoList.size();i++)
        {
            Video videoTmp1 =videoList.get(i);
            videoSort.removeVideo(videoTmp1);
            videoTmp1.setVideoSort(null);
            videoDao.updateVideo(videoTmp1);
            videoDao.deleteVideo(videoTmp1);
        }

        //级联删除所有下层新闻种类
        List<VideoSort> lowerVideoSortList=videoSort.getLowerVideoSortList();
        for(int i=0;i<lowerVideoSortList.size();i++)
        {
            VideoSort videoSortTmp = lowerVideoSortList.get(i);
            List<Video> lowerVideoList = videoSortTmp.getVideos();
            for (int j = 0; j < lowerVideoList.size();j++)
            {
                Video videoTmp2=lowerVideoList.get(i);
                videoSortTmp.removeVideo(videoTmp2);
                videoTmp2.setVideoSort(null);
                videoDao.updateVideo(videoTmp2);
                videoDao.deleteVideo(videoTmp2);
            }
            videoSort.removeVideoSort(videoSortTmp);
            videoSortTmp.setUpperVideoSort(null);
            videoDao.updateVideoSort(videoSortTmp);
            videoDao.deleteVideoSort(videoSortTmp);
        }

        videoDao.deleteVideoSort(videoSort);
        return new ModelAndView("redirect:videosort");
    }

    //显示修改新闻页面
    @RequestMapping(value="nyadmin/videoeditpage",method = RequestMethod.GET)
    public ModelAndView showVideoEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/videoedit");
        Video video=videoDao.getVideoById(id);
        model.addObject("video", video);
        List<VideoSort> videoSortList=videoDao.getVideoSortList(0, 50);
        model.addObject("videoSortList", videoSortList);
        return model;
    }

    //修改新闻
    @RequestMapping(value="nyadmin/videoedit",method = RequestMethod.POST)
    public ModelAndView editVideo(Video video)
    {
        Video targetVideo=videoDao.getVideoById(video.getId());
        targetVideo.setTitle(video.getTitle());
        targetVideo.setSinger(video.getSinger());
        targetVideo.setPlayer(video.getPlayer());
        targetVideo.setProducer(video.getProducer());
        targetVideo.setContent(video.getContent());
        targetVideo.setPath(video.getPath());
        targetVideo.setGcp(video.getGcp());
        targetVideo.setJp(video.getJp());
        targetVideo.setVideoSort(videoDao.getVideoSortById(video.getVideoSort().getId()));
        targetVideo.setAddDate(videoDao.getVideoById(video.getId()).getAddDate());
        targetVideo.setHits(videoDao.getVideoById(video.getId()).getHits());
        videoDao.updateVideo(targetVideo);
        return new ModelAndView("redirect:videolist");
    }


    @RequestMapping(value="nyadmin/videosorteditpage",method = RequestMethod.GET)
    public ModelAndView showVideoSortEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/videosortedit");
        VideoSort videoSort=videoDao.getVideoSortById(id);
        model.addObject("videoSort", videoSort);
        List<VideoSort> videoSortList=videoDao.getVideoSortList(0, 50);
        model.addObject("videoSortList", videoSortList);
        return model;
    }

    @RequestMapping(value="nyadmin/videosortedit",method = RequestMethod.POST)
    public ModelAndView editVideoSort(VideoSort videoSort)
    {
        VideoSort targetVideoSort=videoDao.getVideoSortById(videoSort.getId());
        targetVideoSort.setName(videoSort.getName());
        targetVideoSort.setUpperVideoSort(videoDao.getVideoSortById(videoSort.getUpperVideoSort().getId()));
        targetVideoSort.setLevel(videoDao.getVideoSortById(videoSort.getId()).getLevel());
        videoDao.updateVideoSort(targetVideoSort);
        return new ModelAndView("redirect:videosort");
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/videovideo", method = RequestMethod.POST)
    public FileUploadResult uploadVideo(@RequestParam MultipartFile path, HttpSession session) {
        String basePath = "/upload/video/video/";
        FileUpload fileUpload=new FileUpload(basePath,path,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/videogcp", method = RequestMethod.POST)
    public FileUploadResult uploadgcp(@RequestParam MultipartFile gcp, HttpSession session) {

        String basePath="/upload/video/image/";
        FileUpload fileUpload=new FileUpload(basePath,gcp,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/videojp", method = RequestMethod.POST)
    public FileUploadResult uploadjp(@RequestParam MultipartFile jp, HttpSession session) {

        String basePath="/upload/video/image/";
        FileUpload fileUpload=new FileUpload(basePath,jp,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/ckeditorvideoimage", method = RequestMethod.POST)
    public String uploadCkeditorImage(@RequestParam MultipartFile upload, HttpSession session,HttpServletResponse response,HttpServletRequest request)
    {
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath = "/upload/video/image/";
        FileUpload fileUpload=new FileUpload(basePath,upload,session);
        FileUploadResult fileUploadResult=fileUpload.upload();

        String callback = request.getParameter("CKEditorFuncNum");
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction("
                + callback + ",'" + fileUploadResult.getFileName() + "',''" + ")");
        out.println("</script>");

        return null;
    }

}

class VideoData
{
    public long id;
    public String title;
    public String date;
    public String sortName;

    public VideoData(long id,String title,String date,String sortName)
    {
        this.id=id;
        this.title=title;
        this.date=date;
        this.sortName=sortName;
    }

}