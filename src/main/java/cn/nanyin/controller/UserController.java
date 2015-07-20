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

    //展示用户列表
    @RequestMapping(value="/nyadmin/userlist",method= RequestMethod.GET)
    public ModelAndView showUserList()
    {
        ModelAndView model=new ModelAndView("nyadmin/userlist");
        List<User> userList =userDao.getUserList(0,50);
        model.addObject("userList", userList);
        return model;
    }

    //添加用户页面
    @RequestMapping(value="nyadmin/useraddpage",method=RequestMethod.GET)
    public ModelAndView showUserAddPage()
    {
        ModelAndView model=new ModelAndView("nyadmin/useradd");
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList",areaList);
        return model;
    }

    //添加用户操作
    @RequestMapping(value="nyadmin/useradd",method = RequestMethod.POST)
    public ModelAndView addUser(User user)
    {
        user.setRegDate(new Date());
        userDao.addUser(user);
        return new ModelAndView("redirect:useraddpage");
    }

    //删除用户
    @RequestMapping(value="nyadmin/userdelete",method = RequestMethod.GET)
    public ModelAndView deleteUser(long id)
    {
        User user = userDao.getUserById(id);
        userDao.deleteUser(user);
        return new ModelAndView("redirect:userlist");
    }

    //显示修改用户页面
    @RequestMapping(value="nyadmin/usereditpage",method = RequestMethod.GET)
    public ModelAndView showUserEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/useredit");
        User user=userDao.getUserById(id);
        model.addObject("user", user);
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList", areaList);
        return model;
    }

    //修改用户
    @RequestMapping(value="nyadmin/useredit",method = RequestMethod.POST)
    public ModelAndView editUser(User user)
    {
        User targetUser=userDao.getUserById(user.getId());
        targetUser.setUsername(user.getUsername());
        targetUser.setPassword(user.getPassword());
        targetUser.setName(user.getName());
        targetUser.setGender(user.isGender());
        targetUser.setTelephone(user.getTelephone());
        targetUser.setAddress(user.getAddress());
        targetUser.setEmail(user.getEmail());
        targetUser.setSocialAccount(user.getSocialAccount());
        targetUser.setQuestion(user.getQuestion());
        targetUser.setAnswer(user.getAnswer());
        targetUser.setJoinCommunity(user.isJoinCommunity());
        targetUser.setCollege(user.getCollege());
        targetUser.setVolunteer(user.isVolunteer());
        targetUser.setSkill(user.getSkill());
        targetUser.setLevel(user.getLevel());
        targetUser.setArea(areaDao.getAreaById(user.getArea().getId()));
        targetUser.setRegDate(userDao.getUserById(user.getId()).getRegDate());
        targetUser.setRegIP(userDao.getUserById(user.getId()).getRegIP());
        targetUser.setLastLoginDate(userDao.getUserById(user.getId()).getLastLoginDate());
        targetUser.setState(userDao.getUserById(user.getId()).getState());

        userDao.updateUser(targetUser);
        return new ModelAndView("redirect:userlist");
    }
}
