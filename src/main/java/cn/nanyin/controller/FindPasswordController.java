package cn.nanyin.controller;

/**
 * Created by 张一平 on 2015/8/9.
 */

import cn.nanyin.dao.User1Dao;
import cn.nanyin.model.User1;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller                     /*******************找回密码*****************************/
@RequestMapping("/findPsw.do")
public class FindPasswordController {
    @Autowired
    private User1Dao userDao;

    public User1 findUser;
    /**********************通过用户名找注册时的密保问题******************************/
    @RequestMapping(params = "method=findPsw", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String findPsw(String userName){
        User1 user=userDao.findUser(userName);
        findUser=user;
        if(user==null){
            return null;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        int count=findUser.getAnsNum();
        if(count>=6){
            map.put("question",null);
            map.put("flag",1);
            JSONObject jsonObject=JSONObject.fromObject(map);
            String result=jsonObject.toString();
            return result;
        }

        map.put("question", findUser.getQuestion());
        JSONObject jsonObject=JSONObject.fromObject(map);
        String result=jsonObject.toString();
        return result;
    }
    /*********************发送密保答案*****************************************************/
    @RequestMapping(params = "method=sendAns", produces = "plain/text;charset=UTF-8")
    @ResponseBody
    public String sendAns(@RequestParam("answer") String answer){
        int count=findUser.getAnsNum();
        Map<String,Object> map=new HashMap<String,Object>();
        if(answer.equals(findUser.getAnswer().toString())){
            map.put("flag","get");
            map.put("psw",findUser.getPassword().toString());
        }else if(count<=6){
            findUser.setAnsNum(findUser.getAnsNum()+1);
            userDao.updateUser(findUser);
            map.put("flag","miss");
            map.put("psw",null);
        }else {
            map.put("flag","over");
            map.put("psw",null);
        }

        JSONObject jsonObject=JSONObject.fromObject(map);
        String result=jsonObject.toString();
        return result;

    }


}
