package cn.nanyin.controller;


import cn.nanyin.dao.MessageDao;
import cn.nanyin.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value = "nyadmin/messagelist", method = RequestMethod.GET)
    public ModelAndView showMessageList() {
        ModelAndView model = new ModelAndView("nyadmin/messagelist");
        List<Message> messageList = messageDao.getMessageList(0, 50);
        model.addObject("messagelist", messageList);
        return model;
    }

}
