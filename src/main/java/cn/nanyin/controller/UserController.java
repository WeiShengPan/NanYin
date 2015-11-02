package cn.nanyin.controller;

import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.dao.AreaDao;
import cn.nanyin.dao.MyMessageDao;
import cn.nanyin.dao.User1Dao;
import cn.nanyin.dao.UserDao;
import cn.nanyin.model.Area;
import cn.nanyin.model.MyMessage;
import cn.nanyin.model.User;
import cn.nanyin.model.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

/**
 * Created by gg on 2015/7/14.
 */
@Controller
@AdminAuthority(authorityTypes = AuthorityType.USER_MANAGEMENT)
public class UserController {
    @Autowired
    private User1Dao userDao;
    @Autowired
    private MyMessageDao myMessageDao;
//    @Autowired
//    private AreaDao areaDao;

    //展示用户列表
    @RequestMapping(value="/nyadmin/userlist",method= RequestMethod.GET)
    public ModelAndView showUserList()
    {
        ModelAndView model=new ModelAndView("nyadmin/userList");
        List<User1> userList =userDao.getUserList(0,50);
        model.addObject("userList", userList);
        return model;
    }

    @RequestMapping(value="/nyadmin/userlistsearch",method= RequestMethod.POST)
    public ModelAndView showUserListSearch(User1 user)
    {
        ModelAndView model=new ModelAndView("nyadmin/userList");
        if(user.getUserName().equals("")||user.getUserName()==null){
            List<User1> user1List =userDao.getUserList(0,50);
            model.addObject("userList", user1List);
            return model;
        }
        List<User1> userList =userDao.getUserList();
        List<User1> tempList =userDao.getUserList();
        for(User1 user1:userList){
            if(user1.getUserName().equals(user.getUserName())||user1.getUserName()==user.getUserName()){
                tempList.remove(user1);
            }
        }
        userList.removeAll(tempList);
        if(userList.isEmpty()){
            return model;
        }
        model.addObject("userList", userList);
        return model;
    }

    //添加用户页面
    @RequestMapping(value="nyadmin/useraddpage",method=RequestMethod.GET)
    public ModelAndView showUserAddPage()
    {
        ModelAndView model=new ModelAndView("nyadmin/useradd");
//        List<Area> areaList=areaDao.getAreaList(0, 50);
//        model.addObject("areaList",areaList);
        return model;
    }

    //添加用户操作
    @RequestMapping(value="nyadmin/useradd",method = RequestMethod.POST)
    public ModelAndView addUser(User1 user,BindingResult result)
    {
        user.setRegisterDate(new Date());
        userDao.addUser(user);
        return new ModelAndView("redirect:userlist");
    }

    //删除用户
    @RequestMapping(value="nyadmin/userdelete",method = RequestMethod.GET)
    public ModelAndView deleteUser(long id)
    {
        User1 user = userDao.getUserById(id);
        userDao.deleteUser(user);
        return new ModelAndView("redirect:userlist");
    }

    //显示修改用户页面
    @RequestMapping(value="nyadmin/usereditpage",method = RequestMethod.GET)
    public ModelAndView showUserEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/useredit");
        User1 user=userDao.getUserById(id);
        model.addObject("user", user);
//        List<Area> areaList=areaDao.getAreaList(0, 50);
//        model.addObject("areaList", areaList);
        return model;
    }

    //修改用户
    @RequestMapping(value="nyadmin/useredit",method = RequestMethod.POST)
    public ModelAndView editUser(User1 user)
    {
        User1 targetUser=userDao.getUserById(user.getId());
        targetUser.setUserName(user.getUserName());
        targetUser.setPassword(user.getPassword());
        targetUser.setName(user.getName());
        targetUser.setGender(user.isGender());
        targetUser.setTel(user.getTel());
//        targetUser.setAddress(user.getAddress());
        targetUser.setEmail(user.getEmail());
//        targetUser.setSocialAccount(user.getSocialAccount());
        targetUser.setQuestion(user.getQuestion());
        targetUser.setAnswer(user.getAnswer());
//        targetUser.setJoinCommunity(user.isJoinCommunity());
//        targetUser.setCollege(user.getCollege());
//        targetUser.setVolunteer(user.isVolunteer());
//        targetUser.setSkill(user.getSkill());
        targetUser.setLevel(user.getLevel());
//        targetUser.setArea(areaDao.getAreaById(user.getArea().getId()));
        targetUser.setRegisterDate(userDao.getUserById(user.getId()).getRegisterDate());
//        targetUser.setRegIP(userDao.getUserById(user.getId()).getRegIP());
//        targetUser.setLastLoginDate(userDao.getUserById(user.getId()).getLastLoginDate());
        targetUser.setState(user.getState());

      /*  targetUser.setAnsNum(userDao.getUserById(user.getId()).getAnsNum());
        if(user.getState()==true && user.getLevel()==true){
            targetUser.setLimitNum(0);
        }
        else{
            targetUser.setLimitNum(6);
        }
    */


        userDao.updateUser(targetUser);
        return new ModelAndView("redirect:userlist");
    }

    @RequestMapping(value="/nyadmin/messagelist",method= RequestMethod.GET)
    public ModelAndView showMessageList(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/messagelist");
        List<MyMessage> myMessageList =myMessageDao.getMessage(id);
        model.addObject("myMessageList", myMessageList);
        User1 user=userDao.getUserById(id);
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value="nyadmin/messageaddpage",method=RequestMethod.GET)
    public ModelAndView showMessageAddPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/messageadd");
        User1 user=userDao.getUserById(id);
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value="nyadmin/messageadd",method = RequestMethod.POST)
    public ModelAndView addMessage(MyMessage myMessage)
    {
        myMessage.setDate(new Date());
        myMessage.setState(false);
        myMessageDao.addMessage(myMessage);
        return  new ModelAndView(new RedirectView("messagelist?id="+myMessage.getUser().getId()));
    }

    @RequestMapping(value="nyadmin/messagedelete",method = RequestMethod.GET)
    public ModelAndView deleteMessage(long id)
    {
        MyMessage myMessage = myMessageDao.getMessageById(id);
        long user_id=myMessage.getUser().getId();
        myMessageDao.deleteMessage(myMessage);
        return  new ModelAndView(new RedirectView("messagelist?id="+user_id));
    }
}
