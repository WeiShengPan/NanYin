package cn.nanyin.controller;

import cn.nanyin.dao.AdminDao;
import cn.nanyin.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/8.
 */
@Controller
public class IndexController {
    @Autowired
    private AdminDao adminDao;

//    //进入登录页面
//    @RequestMapping(value = "nyadmin", method = RequestMethod.GET)
//    public ModelAndView index() {
//        return new ModelAndView("nyadmin/index");
//    }

    //登录验证操作
    @RequestMapping(value = "nyadmin/login", method = RequestMethod.POST)
    public ModelAndView login(AdminUser adminUser) {
        List<AdminUser> adminList= adminDao.getAdminUserList(0, 50);
        for(int i=0;i<adminList.size();i++)
        {
            AdminUser adminUserTmp1 =adminList.get(i);
            if(adminUser.getAdminName().equals(adminUserTmp1.getAdminName()) && adminUser.getPassword().equals(adminUserTmp1.getPassword())){
                adminUserTmp1.setLastLoginDate(new Date());
                adminDao.updateAdminUser(adminUserTmp1);
                return new ModelAndView("redirect:adminside?id="+ adminUserTmp1.getId());
                //return new ModelAndView("redirect:adminUsereditpage?id="+ adminUserTmp1.getId());
            }
            else{
                continue;
            }
        }
        JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
        return new ModelAndView("nyadmin/index");
    }

    //修改密码页面
    @RequestMapping(value = "nyadmin/adminUsereditpage", method = RequestMethod.GET)
    public ModelAndView showAdminUserEditPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/adminUseredit");
        AdminUser adminUser=adminDao.getAdminUserById(id);
        model.addObject("adminUser", adminUser);
        return model;
    }

    //修改密码操作
    @RequestMapping(value = "nyadmin/adminUseredit", method = RequestMethod.POST)
    public ModelAndView editAdminUser(AdminUser adminUser) {
        AdminUser targetAdminUser=adminDao.getAdminUserById(adminUser.getId());
        if(targetAdminUser.getPassword().equals(adminUser.getPassword())){
            targetAdminUser.setPassword(adminUser.getNewPass());
            adminDao.updateAdminUser(targetAdminUser);
            return new ModelAndView("redirect:adminlist");
        }
        return new ModelAndView("redirect:adminUsereditpage?id="+ adminUser.getId());
    }

//    @RequestMapping(value = "nyadmin/adminside", method = RequestMethod.GET)
//    public ModelAndView showSide(long id) {
//        ModelAndView model=new ModelAndView("nyadmin/admin.side");
//        AdminUser adminUser=adminDao.getAdminUserById(id);
//        model.addObject("adminUser",adminUser);
//        return model;
//    }

    @RequestMapping(value = "nyadmin/index", method = RequestMethod.GET)
    public ModelAndView showSide() {
        ModelAndView model=new ModelAndView("nyadmin/admin.side");
        return model;
    }

}
