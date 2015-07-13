package cn.nanyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/7/13.
 */
@Controller
public class AdminIndexController {

    @RequestMapping(value="/nyadmin/admin_index",method= RequestMethod.GET)
    public ModelAndView index()
    {
        return new ModelAndView("nyadmin/admin_index");
    }

    @RequestMapping(value="/nyadmin/admin_main",method=RequestMethod.GET)
    public ModelAndView mainFrame()
    {
        return new ModelAndView("nyadmin/admin_main");
    }

    @RequestMapping(value="/nyadmin/admin_left",method=RequestMethod.GET)
    public ModelAndView leftFrame()
    {
        return new ModelAndView("nyadmin/admin_left");
    }
}
