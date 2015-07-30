package cn.nanyin.controller;

import cn.nanyin.dao.MediaDao;
import cn.nanyin.model.Media;
import cn.nanyin.model.MediaSort;
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
 * Created by Administrator on 2015/7/20.
 */
@Controller
public class MediaController {
    @Autowired
    private MediaDao mediaDao;

    //显示新闻列表页面
    @RequestMapping(value="nyadmin/medialist",method= RequestMethod.GET)
    public ModelAndView showMediaList()
    {
        ModelAndView model = new ModelAndView("nyadmin/medialist");
        List<Media> mediaList=mediaDao.getMediaList(0, 50);
        List<MediaSort> mediaSortList=mediaDao.getMediaSortList(0,50);
        model.addObject("mediaSortList",mediaSortList);
        model.addObject("mediaList",mediaList);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/medialistbysort/{sortid}", method = RequestMethod.GET)
    public List<MediaData> getMediaListBySort(@PathVariable Long sortid) {
        MediaSort mediaSort=mediaDao.getMediaSortById(sortid);
        List<Media> mediaList=mediaSort.getMediaList();
        List<MediaData> mediaDataList=new ArrayList<MediaData>();
        for(int i=0;i<mediaList.size();i++)
        {
            Media mediaTmp=mediaList.get(i);
            long id=mediaTmp.getId();
            String title=mediaTmp.getName();
            Date addDate=mediaTmp.getAddDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date=sdf.format(addDate);
            MediaData mediaData=new MediaData(id,title,date,mediaSort.getName());
            mediaDataList.add(mediaData);
        }
        return mediaDataList;
    }

    //显示增加新闻页面
    @RequestMapping(value="nyadmin/mediaaddpage",method=RequestMethod.GET)
    public ModelAndView showNewsAddPage()
    {
        ModelAndView model=new ModelAndView("nyadmin/mediaadd");
        List<MediaSort> mediaSortList=mediaDao.getMediaSortList(0, 50);
        model.addObject("mediaSortList",mediaSortList);
        return model;
    }

    //添加新闻
    @RequestMapping(value="nyadmin/mediaadd",method = RequestMethod.POST)
    public ModelAndView addMedia(Media media,BindingResult result)
    {
        media.setAddDate(new Date());
        mediaDao.addMedia(media);
        return new ModelAndView("redirect:mediaaddpage");
    }

    //显示新闻种类列表页面
    @RequestMapping(value="nyadmin/mediasort",method=RequestMethod.GET)
    public ModelAndView showMediaSortList()
    {
        ModelAndView model=new ModelAndView("nyadmin/mediasort");

        List<MediaSort> mediaSortList=mediaDao.getMediaSortList(0, 50);
        model.addObject("mediaSortList", mediaSortList);
        return model;
    }

    //增加新闻种类
    @RequestMapping(value="nyadmin/mediasortadd",method=RequestMethod.POST)
    public ModelAndView addMediaSort(MediaSort mediaSort)
    {
        //if(newsSort.getUpperNewsSort().getId()!=1)
        int level=mediaDao.getMediaSortById(mediaSort.getUpperMediaSort().getId()).getLevel();
        mediaSort.setLevel(level + 1);
        mediaDao.addMediaSort(mediaSort);
        return new ModelAndView("redirect:mediasort");
    }

    //删除新闻
    @RequestMapping(value="nyadmin/mediadelete",method = RequestMethod.GET)
    public ModelAndView deleteMedia(long id)
    {
       Media media = mediaDao.getMediaById(id);
        mediaDao.deleteMedia(media);
        return new ModelAndView("redirect:medialist");
    }

    //删除新闻种类
    @RequestMapping(value="nyadmin/mediasortdelete",method = RequestMethod.GET)
    public ModelAndView deleteMediaSort(long id)
    {
        MediaSort mediaSort=mediaDao.getMediaSortById(id);

        //级联删除所有该种类新闻
        List<Media> mediaList=mediaSort.getMediaList();
        for(int i=0;i<mediaList.size();i++)
        {
            Media mediaTmp1 =mediaList.get(i);
            mediaSort.removeMedia(mediaTmp1);
            mediaTmp1.setMediaSort(null);
            mediaDao.updateMedia(mediaTmp1);
            mediaDao.deleteMedia(mediaTmp1);
        }

        //级联删除所有下层新闻种类
        List<MediaSort> lowerMediaSortList=mediaSort.getLowerMediaSortList();
        for(int i=0;i<lowerMediaSortList.size();i++)
        {
            MediaSort mediaSortTmp = lowerMediaSortList.get(i);
            List<Media> lowerMediaList = mediaSortTmp.getMediaList();
            for (int j = 0; j < lowerMediaList.size();j++)
            {
                Media mediaTmp2=lowerMediaList.get(i);
                mediaSortTmp.removeMedia(mediaTmp2);
                mediaTmp2.setMediaSort(null);
                mediaDao.updateMedia(mediaTmp2);
                mediaDao.deleteMedia(mediaTmp2);
            }
            mediaSort.removeMediaSort(mediaSortTmp);
            mediaSortTmp.setUpperMediaSort(null);
            mediaDao.updateMediaSort(mediaSortTmp);
            mediaDao.deleteMediaSort(mediaSortTmp);
        }

        mediaDao.deleteMediaSort(mediaSort);
        return new ModelAndView("redirect:mediasort");
    }

    //显示修改新闻页面
    @RequestMapping(value="nyadmin/mediaeditpage",method = RequestMethod.GET)
    public ModelAndView showMediasEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/mediaedit");
        Media media=mediaDao.getMediaById(id);
        model.addObject("media", media);
        List<MediaSort> mediaSortList=mediaDao.getMediaSortList(0, 50);
        model.addObject("mediaSortList", mediaSortList);
        return model;
    }

    //修改新闻
    @RequestMapping(value="nyadmin/mediaedit",method = RequestMethod.POST)
    public ModelAndView editMedia(Media media)
    {
        Media targetMedia=mediaDao.getMediaById(media.getId());
        targetMedia.setName(media.getName());
        targetMedia.setSinger(media.getSinger());
        targetMedia.setPerformer(media.getPerformer());
        targetMedia.setCameraman(media.getCameraman());
        targetMedia.setProducer(media.getProducer());
        targetMedia.setType(media.getType());
        targetMedia.setMedia(media.getMedia());
        targetMedia.setGcp(media.getGcp());
        targetMedia.setJp(media.getJp());
        targetMedia.setFile(media.getFile());
        targetMedia.setContent(media.getContent());
        targetMedia.setMediaSort(mediaDao.getMediaSortById(media.getMediaSort().getId()));
        targetMedia.setAddDate(mediaDao.getMediaById(media.getId()).getAddDate());
        targetMedia.setHits(mediaDao.getMediaById(media.getId()).getHits());
        mediaDao.updateMedia(targetMedia);
        return new ModelAndView("redirect:medialist");
    }


    @RequestMapping(value="nyadmin/mediasorteditpage",method = RequestMethod.GET)
    public ModelAndView showMediaSortEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/mediasortedit");
        MediaSort mediaSort=mediaDao.getMediaSortById(id);
        model.addObject("mediaSort", mediaSort);
        List<MediaSort> mediaSortList=mediaDao.getMediaSortList(0, 50);
        model.addObject("mediaSortList", mediaSortList);
        return model;
    }

    @RequestMapping(value="nyadmin/mediasortedit",method = RequestMethod.POST)
    public ModelAndView editMediaSort(MediaSort mediaSort)
    {
        MediaSort targetMediaSort=mediaDao.getMediaSortById(mediaSort.getId());
        targetMediaSort.setName(mediaSort.getName());
        targetMediaSort.setUpperMediaSort(mediaDao.getMediaSortById(mediaSort.getUpperMediaSort().getId()));
        targetMediaSort.setLevel(mediaDao.getMediaSortById(mediaSort.getId()).getLevel());
        mediaDao.updateMediaSort(targetMediaSort);
        return new ModelAndView("redirect:mediasort");
    }

    /**
     *
     * @param file
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "nyadmin/mediaimage", method = RequestMethod.POST)
    public FileUploadResult uploadImage(@RequestParam MultipartFile file, HttpSession session) {

        String basePath="/upload/media/image/";
        FileUpload fileUpload=new FileUpload(basePath,file,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/mediagcp", method = RequestMethod.POST)
    public FileUploadResult uploadgcp(@RequestParam MultipartFile gcp, HttpSession session) {

        String basePath="/upload/media/image/";
        FileUpload fileUpload=new FileUpload(basePath,gcp,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/mediajp", method = RequestMethod.POST)
    public FileUploadResult uploadjp(@RequestParam MultipartFile jp, HttpSession session) {

        String basePath="/upload/media/image/";
        FileUpload fileUpload=new FileUpload(basePath,jp,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/mediavideo", method = RequestMethod.POST)
    public FileUploadResult uploadMedia(@RequestParam MultipartFile media, HttpSession session) {
        String basePath = "/upload/media/video/";
        FileUpload fileUpload=new FileUpload(basePath,media,session);
        return fileUpload.upload();
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/ckeditormediaimage", method = RequestMethod.POST)
    public String uploadCkeditorImage(@RequestParam MultipartFile upload, HttpSession session,HttpServletResponse response,HttpServletRequest request)
    {
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String basePath = "/upload/media/image/";
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
class MediaData
{
    public long id;
    public String title;
    public String date;
    public String sortName;

    public MediaData(long id,String title,String date,String sortName)
    {
        this.id=id;
        this.title=title;
        this.date=date;
        this.sortName=sortName;
    }

}