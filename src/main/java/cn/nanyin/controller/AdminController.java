package cn.nanyin.controller;

import cn.nanyin.dao.AdminDao;
import cn.nanyin.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by gg on 2015/7/20.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminDao adminDao;

    //展示管理员账户列表
    @RequestMapping(value="nyadmin/adminlist",method= RequestMethod.GET)
    public ModelAndView showAdminUserList() {
        ModelAndView model=new ModelAndView("nyadmin/adminlist");
        List<AdminUser> adminList= adminDao.getAdminUserList(0, 50);
        model.addObject("adminList", adminList);
        return model;
    }

    //添加管理员账户
    @RequestMapping(value="nyadmin/adminadd",method = RequestMethod.POST)
    public ModelAndView addAdminUser(AdminUser adminUser) {
        adminDao.addAdminUser(adminUser);
        return new ModelAndView("redirect:adminlist");
    }

    //删除管理员账户
    @RequestMapping(value="nyadmin/admindelete",method = RequestMethod.GET)
    public ModelAndView deleteAdminUser(long id) {
        AdminUser adminUser= adminDao.getAdminUserById(id);
        adminDao.deleteAdminUser(adminUser);
        return new ModelAndView("redirect:adminlist");
    }

    //地区修改页面
    @RequestMapping(value="nyadmin/admineditpage",method = RequestMethod.GET)
    public ModelAndView showAdminUserEditPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/adminedit");
        AdminUser adminUser= adminDao.getAdminUserById(id);
        model.addObject("adminUser", adminUser);
        return model;
    }

    //地区修改操作
    @RequestMapping(value="nyadmin/adminedit",method = RequestMethod.POST)
    public ModelAndView editAdminUser(AdminUser adminUser) {
        AdminUser targetAdminUser = adminDao.getAdminUserById(adminUser.getId());
        targetAdminUser.setName(adminUser.getName());
        targetAdminUser.setAdminName(adminUser.getAdminName());
        targetAdminUser.setPassword(adminUser.getPassword());
        targetAdminUser.setPermission(adminUser.getPermission());
        targetAdminUser.setLastLoginDate(adminDao.getAdminUserById(adminUser.getId()).getLastLoginDate());
        targetAdminUser.setLastLoginIP(adminDao.getAdminUserById(adminUser.getId()).getLastLoginIP());
        targetAdminUser.setState(adminDao.getAdminUserById(adminUser.getId()).getState());

        adminDao.updateAdminUser(targetAdminUser);
        return new ModelAndView("redirect:adminlist");
    }
}
