package cn.nanyin.controller;

import cn.nanyin.dao.VideoDao;
import cn.nanyin.model.VideoSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "nyadmin/addvideosort",method = RequestMethod.POST)
    public ModelAndView addVideoSort(VideoSort videoSort)
    {
        int level=videoDao.getVideoSortById(videoSort.getUpperVideoSort().getId()).getLevel();
        videoSort.setLevel(level + 1);
        videoDao.addVideoSort(videoSort);
        return new ModelAndView("redirect:videosort");
    }
}
