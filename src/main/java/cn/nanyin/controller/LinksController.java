package cn.nanyin.controller;

import cn.nanyin.dao.LinksDao;
import cn.nanyin.model.Links;
import cn.nanyin.util.FileUpload;
import cn.nanyin.util.FileUploadResult;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 玮晟 on 2015/7/28.
 */
@Controller
public class LinksController {

    @Autowired
    private LinksDao linksDao;

    @RequestMapping(value = "nyadmin/linkslist", method = RequestMethod.GET)
    public ModelAndView showLinksList() {
        ModelAndView model = new ModelAndView("nyadmin/linkslist");
        List<Links> linksList = linksDao.getLinksList(0, 50);
        model.addObject("linkslist", linksList);
        return model;
    }

    @RequestMapping(value = "nyadmin/linksaddpage")
    public ModelAndView showLinksAddPage() {
        ModelAndView model = new ModelAndView("nyadmin/linksadd");
        return model;
    }

    @RequestMapping(value = "nyadmin/linksadd", method = RequestMethod.POST)
    public ModelAndView addNews(Links links, BindingResult result) {
        linksDao.addLinks(links);
        return new ModelAndView("redirect:linksaddpage");
    }

    @RequestMapping(value = "nyadmin/linkseditpage")
    public ModelAndView showLinksEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/linksedit");
        Links links=linksDao.getLinksById(id);
        model.addObject("links", links);
        return model;
    }

    @RequestMapping(value = "nyadmin/linksedit",method = RequestMethod.POST)
    public ModelAndView editLinks(Links links)
    {
        Links targetLinks=linksDao.getLinksById(links.getId());
        targetLinks.setName(links.getName());
        targetLinks.setFile(links.getFile());
        targetLinks.setPriority(links.getPriority());
        targetLinks.setUrl(links.getUrl());
        targetLinks.setLinkType(links.isLinkType());
        linksDao.updateLinks(targetLinks);
        return new ModelAndView("redirect:linkslist");
    }

    @RequestMapping(value = "nyadmin/linksdelete",method = RequestMethod.GET)
    public ModelAndView deleteLinks(long id)
    {
        Links links=linksDao.getLinksById(id);
        linksDao.deleteLinks(links);
        return new ModelAndView("redirect:linkslist");
    }

    /**
     * 上传文件
     * @param file
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "nyadmin/linksimage", method = RequestMethod.POST)
    public FileUploadResult uploadImage(@RequestParam MultipartFile file, HttpSession session) {
        String basePath = "/upload/links/image/";
        FileUpload fileUpload=new FileUpload(basePath,file,session);
        return fileUpload.upload();
    }
}
