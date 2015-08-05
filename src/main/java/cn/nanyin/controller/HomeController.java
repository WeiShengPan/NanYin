package cn.nanyin.controller;

import cn.nanyin.dao.MediaDao;
import cn.nanyin.dao.NewsDao;
import cn.nanyin.model.Media;
import cn.nanyin.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 玮晟 on 2015/7/24.
 */
@Controller
public class HomeController {

    @Autowired
    private MediaDao mediaDao;

    @RequestMapping(value = "t", method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView model=new ModelAndView("t");

        //Media media=mediaDao.getMediaById(1);

       // model.addObject("media",media);

        return model;
    }

    @RequestMapping(value = "nyadmin/test", method = RequestMethod.GET)
    public ModelAndView te() {

        ModelAndView model=new ModelAndView("nyadmin/test");

        //Media media=mediaDao.getMediaById(1);

        // model.addObject("media",media);

        return model;
    }



}
