package cn.nanyin.controller;

import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.dao.AnnouncementDao;
import cn.nanyin.model.Announcement;
import cn.nanyin.util.FileUpload;
import cn.nanyin.util.FileUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import java.util.Date;
import java.util.List;

/**
 * Created by 玮晟 on 2015/8/25.
 */
@Controller
@AdminAuthority(authorityTypes = AuthorityType.ANNOUNCEMENT_MANAGEMENT)
public class AnnouncementController {

    @Autowired
    private AnnouncementDao announcementDao;


    @RequestMapping(value = "nyadmin/announcementlist", method = RequestMethod.GET)
    public ModelAndView showNewsList() {
        ModelAndView model = new ModelAndView("nyadmin/announcementlist");
        List<Announcement> announcementList = announcementDao.getAnnouncementList(0, 50);
        model.addObject("announcementList", announcementList);
        return model;
    }


    //显示增加新闻页面
    @RequestMapping(value = "nyadmin/announcementaddpage", method = RequestMethod.GET)
    public ModelAndView showAnnouncementAddPage() {
        ModelAndView model = new ModelAndView("nyadmin/announcementadd");
        return model;
    }


    //添加新闻
    @RequestMapping(value = "nyadmin/announcementadd", method = RequestMethod.POST)
    public ModelAndView addAnnouncement(Announcement announcement, BindingResult result) {
        announcement.setAddDate(new Date());
        announcementDao.addAnnouncement(announcement);
        return new ModelAndView("redirect:announcementaddpage");
    }


    //删除新闻
    @RequestMapping(value = "nyadmin/announcementdelete", method = RequestMethod.GET)
    public ModelAndView deleteAnnouncement(long id) {
        Announcement announcement = announcementDao.getAnnouncementById(id);
        announcementDao.deleteAnnouncement(announcement);
        return new ModelAndView("redirect:announcementlist");
    }


    //显示修改新闻页面
    @RequestMapping(value = "nyadmin/announcementeditpage", method = RequestMethod.GET)
    public ModelAndView showAnnouncementEditPage(long id) {
        ModelAndView model = new ModelAndView("nyadmin/announcementedit");
        Announcement announcement = announcementDao.getAnnouncementById(id);
        model.addObject("announcement", announcement);
        return model;
    }

    //修改新闻
    @RequestMapping(value = "nyadmin/announcementedit", method = RequestMethod.POST)
    public ModelAndView editAnnouncement(Announcement announcement) {
        Announcement targetAnnouncement = announcementDao.getAnnouncementById(announcement.getId());
        targetAnnouncement.setTitle(announcement.getTitle());
        targetAnnouncement.setContent(announcement.getContent());
        targetAnnouncement.setAddDate(announcementDao.getAnnouncementById(announcement.getId()).getAddDate());
        announcementDao.updateAnnouncement(targetAnnouncement);
        return new ModelAndView("redirect:announcementlist");
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/ckeditorannouncementimage", method = RequestMethod.POST)
    public String uploadCkeditorImage(@RequestParam MultipartFile upload, HttpSession session,HttpServletResponse response,HttpServletRequest request)
    {
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath = "/upload/announcement/image/";
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
