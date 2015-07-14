package cn.nanyin.controller;

import cn.nanyin.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gg on 2015/7/14.
 */
@Controller
public class UserController {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value="/nyadmin/userlist",method= RequestMethod.GET)
    public ModelAndView showUserList()
    {
        return new ModelAndView("nyadmin/userList");
    }

    @RequestMapping(value="nyadmin/useradd",method = RequestMethod.GET)
    public ModelAndView addUser()
    {
        return new ModelAndView("nyadmin/useradd");
    }
//    @RequestMapping(value="/nyadmin/userlist",method= RequestMethod.GET)
//    public ModelAndView showUserList()
//    {
//        Map map=new HashMap();
//        map.put("userlist",userDao.getUserList());
//        return new ModelAndView("nyadmin/userlist",map);
//    }
}
