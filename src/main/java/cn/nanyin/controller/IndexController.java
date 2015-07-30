package cn.nanyin.controller;

import cn.nanyin.dao.AdminDao;
import cn.nanyin.model.AdminUser;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/8.
 */
@Controller
@SessionAttributes("AdminSession")
public class IndexController {
    @Autowired
    private AdminDao adminDao;

    //进入登录页面
    @RequestMapping(value = "nyadmin", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,ModelMap map) {
        HttpSession session = request.getSession();
        AdminUser admin = (AdminUser)session.getAttribute("AdminSession");
        if(admin!=null){
            AdminUser adminUser=new AdminUser();
            adminUser.setId(0);
            map.addAttribute("AdminSession", adminUser);
        }
        return new ModelAndView("nyadmin/index");
    }

    //登录验证操作
    @RequestMapping(value = "nyadmin/login", method = RequestMethod.POST)
    public ModelAndView login(AdminUser adminUser,ModelMap map) {
        List<AdminUser> adminList= adminDao.getAdminUserList(0, 50);
        for(int i=0;i<adminList.size();i++)
        {
            AdminUser adminUserTmp1 =adminList.get(i);
            if(adminUser.getAdminName().equals(adminUserTmp1.getAdminName()) && adminUser.getPassword().equals(adminUserTmp1.getPassword())){
                adminUserTmp1.setLastLoginDate(new Date());
                adminDao.updateAdminUser(adminUserTmp1);
                map.addAttribute("AdminSession",adminUserTmp1);
                return new ModelAndView("redirect:adminside");
            }
            else{
                continue;
            }
        }
        return new ModelAndView("nyadmin/adminloginerror");
    }

    //修改密码页面
    @RequestMapping(value = "nyadmin/adminUsereditpage", method = RequestMethod.GET)
    public ModelAndView showAdminUserEditPage(HttpSession session) {
        ModelAndView model=new ModelAndView("nyadmin/adminUseredit");
        AdminUser admin=(AdminUser)session.getAttribute("AdminSession");
        AdminUser adminUser=adminDao.getAdminUserById(admin.getId());
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

    @RequestMapping(value = "nyadmin/adminside", method = RequestMethod.GET)
    public ModelAndView showSide(HttpSession session) {
        ModelAndView model=new ModelAndView("nyadmin/admin.side");
        AdminUser admin=(AdminUser)session.getAttribute("AdminSession");
        AdminUser adminUser=adminDao.getAdminUserById(admin.getId());
        model.addObject("adminUser",adminUser);
        return model;
    }

    //管理员账户登录错误处理
    @RequestMapping(value="nyadmin/adminloginerror",method = RequestMethod.GET)
    public ModelAndView showAdminLoginError() {
        return new ModelAndView("nyadmin/adminloginerror");
    }

    //管理员账户权限错误处理
    @RequestMapping(value="nyadmin/adminautherror",method = RequestMethod.GET)
    public ModelAndView showAdminAuthError() {
        return new ModelAndView("nyadmin/adminautherror");
    }

}
