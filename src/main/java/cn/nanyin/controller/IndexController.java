package cn.nanyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/7/8.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index.jsp", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
