package cn.nanyin.controller;

import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.dao.CollegeDao;
import cn.nanyin.model.AdminUser;
import cn.nanyin.model.College;
import cn.nanyin.model.CollegeArea;
import cn.nanyin.util.FileUpload;
import cn.nanyin.util.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by gg on 2015/7/20.
 */
@Controller
@AdminAuthority(authorityTypes = AuthorityType.COLLEGE_MANAGEMENT)
public class CollegeController {
    @Autowired
    private CollegeDao collegeDao;

    //展示社团列表
    @RequestMapping(value="/nyadmin/collegelist",method= RequestMethod.GET)
    public ModelAndView showCollegeList() {
        ModelAndView model=new ModelAndView("nyadmin/collegelist");
        List<College> collegeList =collegeDao.getCollegeList(0, 50);
        model.addObject("collegeList", collegeList);
        return model;
    }

    //添加社团页面
    @RequestMapping(value="nyadmin/collegeaddpage",method=RequestMethod.GET)
    public ModelAndView showCollegeAddPage() {
        ModelAndView model=new ModelAndView("nyadmin/collegeadd");
        List<CollegeArea> collegeAreaList=collegeDao.getCollegeAreaList(0, 50);
        model.addObject("collegeAreaList",collegeAreaList);
        return model;
    }

    //添加社团操作
    @RequestMapping(value="nyadmin/collegeadd",method = RequestMethod.POST)
    public ModelAndView addCollege(College college) {
        if(college.isVip()==false){
            //college.setIntroduction("");
            college.setActiveImages("");
            college.setOrganizationStructure("");
            college.setFile("");
        }
        collegeDao.addCollege(college);
        return new ModelAndView("redirect:collegelist");
    }

    //删除社团
    @RequestMapping(value="nyadmin/collegedelete",method = RequestMethod.GET)
    public ModelAndView deleteCollege(long id) {
        College college = collegeDao.getCollegeById(id);
        collegeDao.deleteCollege(college);
        return new ModelAndView("redirect:collegelist");
    }

    //显示修改社团页面
    @RequestMapping(value="nyadmin/collegeeditpage",method = RequestMethod.GET)
    public ModelAndView showCollegeEditPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/collegeedit");
        College college=collegeDao.getCollegeById(id);
        model.addObject("college", college);
        List<CollegeArea> collegeAreaList=collegeDao.getCollegeAreaList(0, 50);
        model.addObject("collegeAreaList", collegeAreaList);
        return model;
    }

    //修改社团
    @RequestMapping(value="nyadmin/collegeedit",method = RequestMethod.POST)
    public ModelAndView editCollege(College college) {
        College targetCollege=collegeDao.getCollegeById(college.getId());
        targetCollege.setEmail(college.getEmail());
        targetCollege.setContact(college.getContact());
        targetCollege.setName(college.getName());
        targetCollege.setActivityDate(college.getActivityDate());
        targetCollege.setAddress(college.getAddress());
        targetCollege.setExLeader(college.getExLeader());
        targetCollege.setFormDate(college.getFormDate());
        targetCollege.setLeader(college.getLeader());
        targetCollege.setMainMembers(college.getMainMembers());
        targetCollege.setMemberNum(college.getMemberNum());
        targetCollege.setTelephone(college.getTelephone());
        targetCollege.setVip(college.isVip());
        targetCollege.setCollegeArea(collegeDao.getCollegeAreaById(college.getCollegeArea().getId()));
        targetCollege.setState(collegeDao.getCollegeById(college.getId()).getState());
        if(targetCollege.isVip()==false){
            targetCollege.setIntroduction("");
            targetCollege.setActiveImages("");
            targetCollege.setOrganizationStructure("");
            targetCollege.setFile("");
        }
        else{
            targetCollege.setIntroduction(college.getIntroduction());
            targetCollege.setActiveImages(college.getActiveImages());
            targetCollege.setOrganizationStructure(college.getOrganizationStructure());
            targetCollege.setFile(college.getFile());
        }

        collegeDao.updateCollege(targetCollege);
        return new ModelAndView("redirect:collegelist");
    }

    //展示地区列表
    @RequestMapping(value="nyadmin/collegearea",method= RequestMethod.GET)
    public ModelAndView showCollegeAreaList() {
        ModelAndView model=new ModelAndView("nyadmin/collegearea");
        List<CollegeArea> collegeAreaList=collegeDao.getCollegeAreaList(0, 50);
        model.addObject("collegeAreaList", collegeAreaList);
        return model;
    }

    //添加地区
    @RequestMapping(value="nyadmin/collegeareaadd",method = RequestMethod.POST)
    public ModelAndView addCollegeArea(CollegeArea collegeArea) {
        int level=collegeDao.getCollegeAreaById(collegeArea.getUpperCollegeArea().getId()).getLevel();
        collegeArea.setLevel(level+1);
        collegeDao.addCollegeArea(collegeArea);
        return new ModelAndView("redirect:collegearea");
    }

    //删除地区
    @RequestMapping(value="nyadmin/collegeareadelete",method = RequestMethod.GET)
    public ModelAndView deleteCollegeArea(long id) {
        CollegeArea collegeArea=collegeDao.getCollegeAreaById(id);

        //级联删除所有该地区社团
        List<College> collegeList=collegeArea.getColleges();
        for(int i=0;i<collegeList.size();i++)
        {
            College collegeTmp1 =collegeList.get(i);
            collegeArea.removeCollege(collegeTmp1);
            collegeTmp1.setCollegeArea(null);
            collegeDao.updateCollege(collegeTmp1);
            collegeDao.deleteCollege(collegeTmp1);
        }

        //级联删除所有下层地区
        List<CollegeArea> lowerCollegeAreaList=collegeArea.getLowerCollegeAreaList();
        for(int i=0;i<lowerCollegeAreaList.size();i++)
        {
            CollegeArea collegeAreaTmp=lowerCollegeAreaList.get(i);

            List<College> lowerCollegeList=collegeAreaTmp.getColleges();
            for(int k=0;k<lowerCollegeList.size();k++)
            {
                College collegeTmp2 =lowerCollegeList.get(i);
                collegeAreaTmp.removeCollege(collegeTmp2);
                collegeTmp2.setCollegeArea(null);
                collegeDao.updateCollege(collegeTmp2);
                collegeDao.deleteCollege(collegeTmp2);
            }
            collegeArea.removeCollegeArea(collegeAreaTmp);
            collegeAreaTmp.setUpperCollegeArea(null);
            collegeDao.updateCollegeArea(collegeAreaTmp);
            collegeDao.deleteCollegeArea(collegeAreaTmp);
        }

        collegeDao.deleteCollegeArea(collegeArea);
        return new ModelAndView("redirect:collegearea");
    }

    //地区修改页面
    @RequestMapping(value="nyadmin/collegeareaeditpage",method = RequestMethod.GET)
    public ModelAndView showCollegeAreaEditPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/collegeareaedit");
        CollegeArea collegeArea=collegeDao.getCollegeAreaById(id);
        model.addObject("collegeArea", collegeArea);
        List<CollegeArea> collegeAreaList=collegeDao.getCollegeAreaList(0, 50);
        model.addObject("collegeAreaList", collegeAreaList);
        return model;
    }

    //地区修改操作
    @RequestMapping(value="nyadmin/collegeareaedit",method = RequestMethod.POST)
    public ModelAndView editCollegeArea(CollegeArea collegeArea) {
        CollegeArea targetCollegeArea = collegeDao.getCollegeAreaById(collegeArea.getId());
        targetCollegeArea.setName(collegeArea.getName());
        targetCollegeArea.setUpperCollegeArea(collegeDao.getCollegeAreaById(collegeArea.getUpperCollegeArea().getId()));
        targetCollegeArea.setLevel(collegeDao.getCollegeAreaById(collegeArea.getId()).getLevel());

        collegeDao.updateCollegeArea(targetCollegeArea);
        return new ModelAndView("redirect:collegearea");
    }

    /**
     * 上传文件
     * @param file
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "nyadmin/collegevideo", method = RequestMethod.POST)
    public FileUploadResult uploadVideo(@RequestParam MultipartFile file, HttpSession session) {
        String basePath = "/upload/college/video/";
        FileUpload fileUpload=new FileUpload(basePath,file,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/ckeditorcollegeimage", method = RequestMethod.POST)
    public String uploadCkeditorImage(@RequestParam MultipartFile upload, HttpSession session,HttpServletResponse response,HttpServletRequest request) {
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath = "/upload/college/image/";
        FileUpload fileUpload=new FileUpload(basePath,upload,session);
        FileUploadResult fileUploadResult=fileUpload.upload();

        String callback = request.getParameter("CKEditorFuncNum");
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction("
                + callback + ",'" + fileUploadResult.getFileName() + "',''" + ")");
        out.println("</script>");

        return null;
    }
}
