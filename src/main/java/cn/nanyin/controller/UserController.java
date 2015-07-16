package cn.nanyin.controller;

import cn.nanyin.dao.AreaDao;
import cn.nanyin.dao.UserDao;
import cn.nanyin.model.Area;
import cn.nanyin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by gg on 2015/7/14.
 */
@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AreaDao areaDao;

    @RequestMapping(value="/nyadmin/userlist",method= RequestMethod.GET)
    public ModelAndView showUserList()
    {
        ModelAndView model=new ModelAndView("nyadmin/userlist");
        List<User> userList =userDao.getUserList(0,50);
        model.addObject("userList", userList);
        return model;
    }

    @RequestMapping(value="nyadmin/useraddpage",method=RequestMethod.GET)
    public ModelAndView showUserAddPage()
    {
        ModelAndView model=new ModelAndView("nyadmin/useradd");
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList",areaList);
        return model;
    }

    @RequestMapping(value="nyadmin/useradd",method = RequestMethod.POST)
    public ModelAndView addUser(User user)
    {
        user.setRegDate(new Date());
        userDao.addUser(user);
        return new ModelAndView("redirect:useraddpage");
    }

    @RequestMapping(value="nyadmin/userarea",method=RequestMethod.GET)
    public ModelAndView showAreaList()
    {
        ModelAndView model=new ModelAndView("nyadmin/userarea");
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList", areaList);
        return model;
    }

    @RequestMapping(value="nyadmin/userareaadd",method = RequestMethod.POST)
    public ModelAndView addArea(Area area)
    {
        areaDao.addArea(area);
        return new ModelAndView("redirect:userarea");
    }
}
