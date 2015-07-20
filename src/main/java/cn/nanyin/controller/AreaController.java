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
 * Created by gg on 2015/7/20.
 */
@Controller
public class AreaController {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private UserDao userDao;

    //展示地区列表
    @RequestMapping(value="nyadmin/arealist",method= RequestMethod.GET)
    public ModelAndView showAreaList()
    {
        ModelAndView model=new ModelAndView("nyadmin/arealist");
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList", areaList);
        return model;
    }

    //添加地区
    @RequestMapping(value="nyadmin/areaadd",method = RequestMethod.POST)
    public ModelAndView addArea(Area area)
    {
        if(area.getUpperArea().getId()!=1)
            area.setLevel(area.getUpperArea().getLevel()+1);
        areaDao.addArea(area);
        return new ModelAndView("redirect:arealist");
    }

    //删除地区
    @RequestMapping(value="nyadmin/areadelete",method = RequestMethod.GET)
    public ModelAndView deleteArea(long id)
    {
        Area area=areaDao.getAreaById(id);

        //级联删除所有该地区用户
        List<User> userList=area.getUsers();
        for(int i=0;i<userList.size();i++)
        {
            User userTmp1 =userList.get(i);
            area.removeUser(userTmp1);
            userTmp1.setArea(null);
            userDao.updateUser(userTmp1);
            userDao.deleteUser(userTmp1);
        }

        //级联删除所有下层新闻种类
        List<Area> lowerAreaList=area.getLowerAreaList();
        for(int i=0;i<lowerAreaList.size();i++)
        {
            Area areaTmp=lowerAreaList.get(i);
            List<User> lowerUserList=areaTmp.getUsers();
            for(int j=0;j<lowerUserList.size();j++)
            {
                User userTmp2=lowerUserList.get(i);
                areaTmp.removeUser(userTmp2);
                userTmp2.setArea(null);
                userDao.updateUser(userTmp2);
                userDao.deleteUser(userTmp2);
            }
            area.removeArea(areaTmp);
            areaTmp.setUpperArea(null);
            areaDao.updateArea(areaTmp);
            areaDao.deleteArea(areaTmp);
        }

        areaDao.deleteArea(area);
        return new ModelAndView("redirect:arealist");
    }

    //地区修改页面
    @RequestMapping(value="nyadmin/areaeditpage",method = RequestMethod.GET)
    public ModelAndView showAreaEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/areaedit");
        Area area=areaDao.getAreaById(id);
        model.addObject("area", area);
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList",areaList);
        return model;
    }

    //地区修改操作
    @RequestMapping(value="nyadmin/areaedit",method = RequestMethod.POST)
    public ModelAndView editArea(Area area) {
        Area targetArea = areaDao.getAreaById(area.getId());
        targetArea.setName(area.getName());
        targetArea.setPriority(area.getPriority());
        targetArea.setUpperArea(areaDao.getAreaById(area.getUpperArea().getId()));
        targetArea.setLevel(areaDao.getAreaById(area.getId()).getLevel());
        targetArea.setState(areaDao.getAreaById(area.getId()).getState());

        areaDao.updateArea(targetArea);
        return new ModelAndView("redirect:arealist");
    }
}
