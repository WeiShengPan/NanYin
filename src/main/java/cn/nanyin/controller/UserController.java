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
        List<User> userList=userDao.getUserList(0,50);
        model.addObject("userList",userList);
        return model;
    }

    @RequestMapping(value="nyadmin/useradd",method = RequestMethod.GET)
    public ModelAndView addUser(User user)
    {
//        Area ns=new Area();ns.setLevel(1);ns.setName("啊1");ns.setPriority(1);ns.setState(1);ns.setUpperId(1);
//        User u=new User();
//        u.setUsername("中国");
//        u.setPassword("123456");
//        u.setAddress("");u.setAnswer("");u.setCollege("");u.setEmail("");u.setGender(false);u.setJoin(false);
//        u.setLastLoginDate(null);u.setLevel(1);u.setName("");
//        u.setArea(ns);
//        userDao.addUser(u);
        return new ModelAndView("nyadmin/useradd");
    }

    @RequestMapping(value="nyadmin/userarea",method=RequestMethod.GET)
    public ModelAndView showAreaList()
    {
        ModelAndView model=new ModelAndView("nyadmin/userarea");
//        Area ns=new Area();
//        ns.setLevel(1);
//        ns.setName("啊1");
//        ns.setPriority(1);
//        ns.setState(1);
//        ns.setUpperId(1);
//        areaDao.addArea(ns);
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList", areaList);
        return model;
    }
}
