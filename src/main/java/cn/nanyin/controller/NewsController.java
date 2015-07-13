package cn.nanyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by звк… on 2015/7/13.
 */
@Controller
public class NewsController {

    @RequestMapping(value="/nyadmin/newslist",method= RequestMethod.GET)
    public ModelAndView showNewsList()
    {
        return new ModelAndView("nyadmin/newslist");
    }
}
