package cn.nanyin.controller;

import cn.nanyin.dao.AreaDao;
import cn.nanyin.dao.CollegeDao;
import cn.nanyin.model.Area;
import cn.nanyin.model.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by gg on 2015/7/20.
 */
@Controller
public class CollegeController {
    @Autowired
    private CollegeDao collegeDao;
    @Autowired
    private AreaDao areaDao;

    //展示用户列表
    @RequestMapping(value="/nyadmin/collegelist",method= RequestMethod.GET)
    public ModelAndView showCollegeList() {
        ModelAndView model=new ModelAndView("nyadmin/collegelist");
        List<College> collegeList =collegeDao.getCollegeList(0, 50);
        model.addObject("collegeList", collegeList);
        return model;
    }

    //添加用户页面
    @RequestMapping(value="nyadmin/collegeaddpage",method=RequestMethod.GET)
    public ModelAndView showCollegeAddPage() {
        ModelAndView model=new ModelAndView("nyadmin/collegeadd");
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList",areaList);
        return model;
    }

    //添加用户操作
    @RequestMapping(value="nyadmin/collegeadd",method = RequestMethod.POST)
    public ModelAndView addCollege(College college) {
        collegeDao.addCollege(college);
        return new ModelAndView("redirect:collegelist");
    }

    //删除用户
    @RequestMapping(value="nyadmin/collegedelete",method = RequestMethod.GET)
    public ModelAndView deleteCollege(long id) {
        College college = collegeDao.getCollegeById(id);
        collegeDao.deleteCollege(college);
        return new ModelAndView("redirect:collegelist");
    }

    //显示修改用户页面
    @RequestMapping(value="nyadmin/collegeeditpage",method = RequestMethod.GET)
    public ModelAndView showCollegeEditPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/collegeedit");
        College college=collegeDao.getCollegeById(id);
        model.addObject("college", college);
        List<Area> areaList=areaDao.getAreaList(0, 50);
        model.addObject("areaList", areaList);
        return model;
    }

    //修改用户
    @RequestMapping(value="nyadmin/collegeedit",method = RequestMethod.POST)
    public ModelAndView editCollege(College college) {
        College targetCollege=collegeDao.getCollegeById(college.getId());
        targetCollege.setEmail(college.getEmail());
        targetCollege.setName(college.getName());
        targetCollege.setActivityDate(college.getActivityDate());
        targetCollege.setAddress(college.getAddress());
        targetCollege.setContact(college.getContact());
        targetCollege.setExLeader(college.getExLeader());
        targetCollege.setFormDate(college.getFormDate());
        targetCollege.setImage(college.getImage());
        targetCollege.setIntroduction(college.getIntroduction());
        targetCollege.setLeader(college.getLeader());
        targetCollege.setMainMembers(college.getMainMembers());
        targetCollege.setMemberNum(college.getMemberNum());
        targetCollege.setTelephone(college.getTelephone());
        targetCollege.setVip(college.isVip());
        targetCollege.setArea(areaDao.getAreaById(college.getArea().getId()));
        targetCollege.setState(collegeDao.getCollegeById(college.getId()).getState());

        collegeDao.updateCollege(targetCollege);
        return new ModelAndView("redirect:collegelist");
    }
}
