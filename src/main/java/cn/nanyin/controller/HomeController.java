package cn.nanyin.controller;

import cn.nanyin.dao.NewsDao;
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
    private NewsDao newsDao;

    @RequestMapping(value = "nyadmin/test", method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView model=new ModelAndView("nyadmin/test");

        News news=newsDao.getNewsById(2);

        model.addObject("news",news);

        return model;
    }

}
