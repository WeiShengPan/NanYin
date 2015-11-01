package cn.nanyin.controller;

import cn.nanyin.dao.MyMessageDao;
import cn.nanyin.dao.User1Dao;
import cn.nanyin.model.MyMessage;
import cn.nanyin.model.User1;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by 张一平 on 2015/8/9.
 */
@Controller                                 /************************注册***************************/
@RequestMapping("/register.do")
public class RegisterController {
    @Autowired
    private User1Dao userDao;

    @Autowired
    private MyMessageDao myMessageDao;
    /****************注册会员（普通会员和高级会员）********************************/
    @RequestMapping(params = "method=register",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public void register1(@RequestParam("type") int type,String userName,String psw,String name,boolean gender,
                          String tel,String email,String question,String answer)throws  Exception {
        User1 user=new User1();
        user.setUserName(userName);
        user.setPassword(psw);
        user.setGender(gender);
        user.setName(name);
        user.setTel(tel);
        user.setEmail(email);
        user.setQuestion(question);
        user.setAnswer(answer);
        Date date=new Date();
        user.setRegisterDate(date);
        if(type==1){
            user.setState(true);
            user.setLevel(false);
        }else if(type==2){
            user.setLevel(true);
            user.setState(false);
        }
        userDao.addUser(user);

    }
    /******************验证用户名是否在数据库中已经存在******************************/
    @RequestMapping(params = "method=verification",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String verification(String userName){
        User1 loginUser=userDao.findUser(userName);
        if(loginUser!=null){
            return "fail";
        }else{
            return "success";
        }
    }
    /******************更改用户信息******************************/
    @RequestMapping(params = "method=alterUserInfo",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public void alterUserInfo(String name,String tel,String email,String question,String answer,HttpSession session){
        User1 user=(User1)session.getAttribute("loginUser");
        user.setName(name);
        user.setTel(tel);
        user.setEmail(email);
        user.setQuestion(question);
        user.setAnswer(answer);
        userDao.updateUser(user);

        session.setAttribute("loginUser",user);
    }
    /******************更改密码******************************/
    @RequestMapping(params = "method=alterPsw",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public void alterPsw(String newPsw,HttpSession session){
        User1 user=(User1)session.getAttribute("loginUser");
        user.setPassword(newPsw);
        userDao.updateUser(user);
        session.setAttribute("loginUser", user);
    }
    /******************获得某个用户的个人会员中心中的信息******************************/
    @RequestMapping(params = "method=getMessage",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String getMessage(long userId){
        List<MyMessage> myMessageList=myMessageDao.getMessage(userId);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<myMessageList.size();i++){
            MyMessage temp=myMessageList.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",temp.getId());
            map.put("title",temp.getTitle());
            map.put("content",temp.getContent());
            map.put("state",temp.getState());
            String date=temp.getDate().toString();
            map.put("date",date);
            list.add(map);
        }
        JSONArray jsonArray=JSONArray.fromObject(list);
        String result=jsonArray.toString();
        return result;
    }
    /******************更改信息******************************/
    @RequestMapping(params = "method=updateMessage",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public void updateMessage(long id){
        MyMessage myMessage=myMessageDao.getMessageById(id);
        myMessage.setState(true);
        myMessageDao.updateNews(myMessage);

    }
    /******************删除信息******************************/
    @RequestMapping(params = "method=deleteMessage",produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public void deleteMessage(long id){
        MyMessage myMessage=myMessageDao.getMessageById(id);
        myMessageDao.deleteMessage(myMessage);
    }

}
