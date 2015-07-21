package cn.nanyin.controller;

import cn.nanyin.dao.VideoDao;
import cn.nanyin.model.Video;
import cn.nanyin.model.VideoDetail;
import cn.nanyin.model.VideoSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
@Controller
public class VideoController {
    @Autowired
    private VideoDao videoDao;

    @RequestMapping(value = "nyadmin/videosort",method = RequestMethod.GET)
    public ModelAndView showVideoSortList()
    {
        ModelAndView model=new ModelAndView("nyadmin/videosort");
        List<VideoSort> videoSortList=videoDao.getVideoSortList(0,50);
        model.addObject("videoSortList",videoSortList);
        return model;
    }

    @RequestMapping(value = "nyadmin/videosortadd",method = RequestMethod.POST)
    public ModelAndView addVideoSort(VideoSort videoSort)
    {
        int level=videoDao.getVideoSortById(videoSort.getUpperVideoSort().getId()).getLevel();
        videoSort.setLevel(level + 1);
        videoDao.addVideoSort(videoSort);
        return new ModelAndView("redirect:videosort");
    }

    @RequestMapping(value = "nyadmin/videosortedit",method = RequestMethod.POST)
    public ModelAndView editVideoSort(VideoSort videoSort)
    {
        VideoSort targetVideoSort=videoDao.getVideoSortById(videoSort.getId());
        targetVideoSort.setName(videoSort.getName());
        targetVideoSort.setUpperVideoSort(videoDao.getVideoSortById(videoSort.getUpperVideoSort().getId()));
        targetVideoSort.setLevel(videoSort.getLevel());
        videoDao.updateVideoSort(targetVideoSort);
        return new ModelAndView("redirect:videosort");
    }

    @RequestMapping(value = "nyadmin/videosorteditpage",method = RequestMethod.GET)
    public ModelAndView showVideoSortEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/videosortedit");
        VideoSort videoSort=videoDao.getVideoSortById(id);
        model.addObject("videoSort", videoSort);
        List<VideoSort> videoSortList=videoDao.getVideoSortList(0,50);
        model.addObject("videoSortList", videoSortList);
        return model;
    }

    @RequestMapping(value="nyadmin/videosortdelete",method = RequestMethod.GET)
    public ModelAndView deleteVideoSort(long id)
    {
        return null;
    }

    @RequestMapping(value = "nyadmin/videolist",method = RequestMethod.GET)
    public ModelAndView showVideoList()
    {
        ModelAndView model=new ModelAndView("nyadmin/videolist");
        List<Video> videoList=videoDao.getVideoList(0,50);
        model.addObject("videoList",videoList);
        return model;
    }

    @RequestMapping(value="nyadmin/videoadd")
    public ModelAndView addVideo(Video video,BindingResult result)
    {
        video.setAddDate(new Date());
        videoDao.addVideo(video);
        return new ModelAndView("redirect:videoaddpage");
    }

    @RequestMapping(value = "nyadmin/videoaddpage",method = RequestMethod.GET)
    public ModelAndView showVideoAddPage()
    {
        ModelAndView model=new ModelAndView("nyadmin/videoadd");
        List<VideoSort> videoSortList=videoDao.getVideoSortList(0,50);
        model.addObject("videoSortList", videoSortList);
        return model;
    }

    @RequestMapping(value = "nyadmin/videoeditpage",method = RequestMethod.GET)
    public ModelAndView showVideoEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/videoedit");
        Video video=videoDao.getVideoById(id);
        model.addObject("video",video);
        List<VideoSort> videoSortList=videoDao.getVideoSortList(0,50);
        model.addObject("videoSortList",videoSortList);
        return model;

    }

    @RequestMapping(value = "nyadmin/videoedit",method = RequestMethod.POST)
    public ModelAndView editVideo(Video video)
    {
        Video targetVideo=videoDao.getVideoById(video.getId());
        targetVideo.setTitle(video.getTitle());
        targetVideo.setContent(video.getContent());
        targetVideo.setImage(video.getImage());
        targetVideo.setVideoSort(videoDao.getVideoSortById(video.getVideoSort().getId()));
        targetVideo.setAddDate(videoDao.getVideoById(video.getId()).getAddDate());
        targetVideo.setHits(videoDao.getVideoById(video.getId()).getHits());
        targetVideo.setVideoDetails(videoDao.getVideoById(video.getId()).getVideoDetails());
        videoDao.updateVideo(video);
        return new ModelAndView("redirect:videolist");
    }

    @RequestMapping(value = "nyadmin/videodelete",method = RequestMethod.GET)
    public ModelAndView deleteVideo(long id)
    {
        Video video=videoDao.getVideoById(id);

        List<VideoDetail> videoDetails=video.getVideoDetails();
        for(int i=0;i<videoDetails.size();i++)
        {
            VideoDetail videoDetailTmp=videoDetails.get(i);
            video.removeVideoDetail(videoDetailTmp);
            videoDetailTmp.setVideo(null);
            videoDao.updateVideoDetail(videoDetailTmp);
            videoDao.deleteVideoDetail(videoDetailTmp);
        }
        videoDao.deleteVideo(video);
        return new ModelAndView("redirect:videolist");
    }

    @RequestMapping(value = "nyadmin/videodetail",method = RequestMethod.GET)
    public ModelAndView showVideoDetailList(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/videodetail");
        List<VideoDetail> videoDetailList=videoDao.getVideoDetailList(0,50,id);
        model.addObject("videoDetailList",videoDetailList);
        Video video=videoDao.getVideoById(id);
        model.addObject("video",video);

        return model;
    }

    @RequestMapping(value = "nyadmin/videodetailadd",method = RequestMethod.POST)
    public ModelAndView addVideoDetail(VideoDetail videoDetail)
    {
        videoDetail.setAddDate(new Date());
        videoDao.addVideoDetail(videoDetail);
        return new ModelAndView("redirect:videodetail");
    }

    @RequestMapping(value = "nyadmin/videodetaileditpage")
    public ModelAndView showVideoDetailEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/videodetailedit");
        VideoDetail videoDetail=videoDao.getVideoDetailById(id);
        Video video=videoDao.getVideoById(videoDetail.getVideo().getId());
        model.addObject("videoDetail", videoDetail);
        model.addObject("video", video);
        return model;
    }

    @RequestMapping(value = "nyadmin/videodetailedit",method = RequestMethod.POST)
    public ModelAndView editVideoDetail(VideoDetail videoDetail)
    {
        VideoDetail targetVideoDetail=videoDao.getVideoDetailById(videoDetail.getId());
        targetVideoDetail.setTitle(videoDetail.getTitle());
        targetVideoDetail.setTeacher(videoDetail.getTeacher());
        targetVideoDetail.setEditor(videoDetail.getEditor());
        targetVideoDetail.setSource(videoDetail.getSource());
        targetVideoDetail.setRemark(videoDetail.getRemark());
        targetVideoDetail.setUrl(videoDetail.getUrl());
        targetVideoDetail.setContent(videoDetail.getContent());
        targetVideoDetail.setVideo(videoDao.getVideoById(videoDetail.getVideo().getId()));
        targetVideoDetail.setAddDate(videoDao.getVideoDetailById(videoDetail.getId()).getAddDate());
        targetVideoDetail.setHits(videoDao.getVideoDetailById(videoDetail.getId()).getHits());

        videoDao.updateVideoDetail(videoDetail);
        return new ModelAndView("redirect:videodetail");
    }

    @RequestMapping(value = "nyadmin/videodetaildelete",method = RequestMethod.GET)
    public ModelAndView deleteVideoDetail(long id)
    {
        VideoDetail videoDetail=videoDao.getVideoDetailById(id);
        videoDao.deleteVideoDetail(videoDetail);
        return new ModelAndView("redirect:videodetail");
    }


}
