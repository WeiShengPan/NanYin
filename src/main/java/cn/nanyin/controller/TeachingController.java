package cn.nanyin.controller;

import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.dao.TeachingDao;
import cn.nanyin.model.Teaching;
import cn.nanyin.model.TeachingSort;
import cn.nanyin.util.FileUpload;
import cn.nanyin.util.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 玮晟 on 2015/8/26.
 */
@Controller
@AdminAuthority(authorityTypes = AuthorityType.TEACHING_MANAGEMENT)
public class TeachingController {
    
    @Autowired
    private TeachingDao teachingDao;

    @RequestMapping(value = "nyadmin/teachinglist",method = RequestMethod.GET)
    public ModelAndView showTeachingList()
    {
        ModelAndView model=new ModelAndView("nyadmin/teachinglist");
        List<Teaching> teachingList=teachingDao.getTeachingList(0,50);
        List<TeachingSort> teachingSortList=teachingDao.getTeachingSortList(0,50);
        model.addObject("teachingList",teachingList);
        model.addObject("teachingSortList",teachingSortList);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/teachinglistbysort/{sortid}", method = RequestMethod.GET)
    public List<TeachingData> getAudioListBySort(@PathVariable Long sortid) {
        TeachingSort teachingSort=teachingDao.getTeachingSortById(sortid);
        List<Teaching> teachingList=teachingSort.getTeachings();
        List<TeachingData> teachingDataList=new ArrayList<TeachingData>();
        for(int i=0;i<teachingList.size();i++)
        {
            Teaching teachingTmp=teachingList.get(i);
            long id=teachingTmp.getId();
            String title=teachingTmp.getTitle();
            Date addDate=teachingTmp.getAddDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=sdf.format(addDate);
            TeachingData teachingData=new TeachingData(id,title,date,teachingSort.getName());
            teachingDataList.add(teachingData);
        }
        return teachingDataList;
    }

    //显示增加新闻页面
    @RequestMapping(value="nyadmin/teachingaddpage",method=RequestMethod.GET)
    public ModelAndView showTeachingAddPage()
    {
        ModelAndView model=new ModelAndView("nyadmin/teachingadd");
        List<TeachingSort> teachingSortList=teachingDao.getTeachingSortList(0, 50);
        model.addObject("teachingSortList",teachingSortList);
        return model;
    }

    //添加新闻
    @RequestMapping(value="nyadmin/teachingadd",method = RequestMethod.POST)
    public ModelAndView addTeaching(Teaching teaching,BindingResult result)
    {
        teaching.setAddDate(new Date());
        teachingDao.addTeaching(teaching);
        return new ModelAndView("redirect:teachingaddpage");
    }

    //显示新闻种类列表页面
    @RequestMapping(value="nyadmin/teachingsort",method=RequestMethod.GET)
    public ModelAndView showTeachingSortList()
    {
        ModelAndView model=new ModelAndView("nyadmin/teachingsort");

        List<TeachingSort> teachingSortList=teachingDao.getTeachingSortList(0, 50);
        model.addObject("teachingSortList", teachingSortList);
        return model;
    }

    //增加新闻种类
    @RequestMapping(value="nyadmin/teachingsortadd",method=RequestMethod.POST)
    public ModelAndView addTeachingSort(TeachingSort teachingSort)
    {
        //if(newsSort.getUpperNewsSort().getId()!=1)
        int level=teachingDao.getTeachingSortById(teachingSort.getUpperTeachingSort().getId()).getLevel();
        teachingSort.setLevel(level + 1);
        teachingDao.addTeachingSort(teachingSort);
        return new ModelAndView("redirect:teachingsort");
    }

    //删除新闻
    @RequestMapping(value="nyadmin/teachingdelete",method = RequestMethod.GET)
    public ModelAndView deleteTeaching(long id)
    {
        Teaching teaching = teachingDao.getTeachingById(id);
        teachingDao.deleteTeaching(teaching);
        return new ModelAndView("redirect:teachinglist");
    }

    //删除新闻种类
    @RequestMapping(value="nyadmin/teachingsortdelete",method = RequestMethod.GET)
    public ModelAndView deleteTeachingSort(long id)
    {
        TeachingSort teachingSort=teachingDao.getTeachingSortById(id);

        //级联删除所有该种类新闻
        List<Teaching> teachingList=teachingSort.getTeachings();
        for(int i=0;i<teachingList.size();i++)
        {
            Teaching teachingTmp1 =teachingList.get(i);
            teachingSort.removeTeaching(teachingTmp1);
            teachingTmp1.setTeachingSort(null);
            teachingDao.updateTeaching(teachingTmp1);
            teachingDao.deleteTeaching(teachingTmp1);
        }

        //级联删除所有下层新闻种类
        List<TeachingSort> lowerTeachingSortList=teachingSort.getLowerTeachingSortList();
        for(int i=0;i<lowerTeachingSortList.size();i++)
        {
            TeachingSort teachingSortTmp = lowerTeachingSortList.get(i);
            List<Teaching> lowerTeachingList = teachingSortTmp.getTeachings();
            for (int j = 0; j < lowerTeachingList.size();j++)
            {
                Teaching teachingTmp2=lowerTeachingList.get(i);
                teachingSortTmp.removeTeaching(teachingTmp2);
                teachingTmp2.setTeachingSort(null);
                teachingDao.updateTeaching(teachingTmp2);
                teachingDao.deleteTeaching(teachingTmp2);
            }
            teachingSort.removeTeachingSort(teachingSortTmp);
            teachingSortTmp.setUpperTeachingSort(null);
            teachingDao.updateTeachingSort(teachingSortTmp);
            teachingDao.deleteTeachingSort(teachingSortTmp);
        }

        teachingDao.deleteTeachingSort(teachingSort);
        return new ModelAndView("redirect:teachingsort");
    }

    //显示修改新闻页面
    @RequestMapping(value="nyadmin/teachingeditpage",method = RequestMethod.GET)
    public ModelAndView showTeachingEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/teachingedit");
        Teaching teaching=teachingDao.getTeachingById(id);
        model.addObject("teaching", teaching);
        List<TeachingSort> teachingSortList=teachingDao.getTeachingSortList(0, 50);
        model.addObject("teachingSortList", teachingSortList);
        return model;
    }

    //修改新闻
    @RequestMapping(value="nyadmin/teachingedit",method = RequestMethod.POST)
    public ModelAndView editTeaching(Teaching teaching)
    {
        Teaching targetTeaching=teachingDao.getTeachingById(teaching.getId());
        targetTeaching.setTitle(teaching.getTitle());
        targetTeaching.setTeacher(teaching.getTeacher());
        targetTeaching.setSource(teaching.getSource());
        targetTeaching.setContent(teaching.getContent());

        targetTeaching.setFile(teaching.getFile());
        targetTeaching.setGcp(teaching.getGcp());
        targetTeaching.setJp(teaching.getJp());
        targetTeaching.setTeachingSort(teachingDao.getTeachingSortById(teaching.getTeachingSort().getId()));
        targetTeaching.setAddDate(teachingDao.getTeachingById(teaching.getId()).getAddDate());
        targetTeaching.setHits(teachingDao.getTeachingById(teaching.getId()).getHits());
        teachingDao.updateTeaching(targetTeaching);
        return new ModelAndView("redirect:teachinglist");
    }


    @RequestMapping(value="nyadmin/teachingsorteditpage",method = RequestMethod.GET)
    public ModelAndView showTeachingSortEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/teachingsortedit");
        TeachingSort teachingSort=teachingDao.getTeachingSortById(id);
        model.addObject("teachingSort", teachingSort);
        List<TeachingSort> teachingSortList=teachingDao.getTeachingSortList(0, 50);
        model.addObject("teachingSortList", teachingSortList);
        return model;
    }

    @RequestMapping(value="nyadmin/teachingsortedit",method = RequestMethod.POST)
    public ModelAndView editTeachingSort(TeachingSort teachingSort)
    {
        TeachingSort targetTeachingSort=teachingDao.getTeachingSortById(teachingSort.getId());
        targetTeachingSort.setName(teachingSort.getName());
        targetTeachingSort.setUpperTeachingSort(teachingDao.getTeachingSortById(teachingSort.getUpperTeachingSort().getId()));
        targetTeachingSort.setLevel(teachingDao.getTeachingSortById(teachingSort.getId()).getLevel());
        teachingDao.updateTeachingSort(targetTeachingSort);
        return new ModelAndView("redirect:teachingsort");
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/teachingvideo", method = RequestMethod.POST)
    public FileUploadResult uploadTeaching(@RequestParam MultipartFile file, HttpSession session) {
        String basePath = "/upload/teaching/video/";
        FileUpload fileUpload=new FileUpload(basePath,file,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/teachinggcp", method = RequestMethod.POST)
    public FileUploadResult uploadgcp(@RequestParam MultipartFile gcp, HttpSession session) {

        String basePath="/upload/teaching/image/";
        FileUpload fileUpload=new FileUpload(basePath,gcp,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/teachingjp", method = RequestMethod.POST)
    public FileUploadResult uploadjp(@RequestParam MultipartFile jp, HttpSession session) {

        String basePath="/upload/teaching/image/";
        FileUpload fileUpload=new FileUpload(basePath,jp,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/ckeditorteachingimage", method = RequestMethod.POST)
    public String uploadCkeditorImage(@RequestParam MultipartFile upload, HttpSession session,HttpServletResponse response,HttpServletRequest request)
    {
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath = "/upload/teaching/image/";
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

class TeachingData
{
    public long id;
    public String title;
    public String date;
    public String sortName;

    public TeachingData(long id,String title,String date,String sortName)
    {
        this.id=id;
        this.title=title;
        this.date=date;
        this.sortName=sortName;
    }

}