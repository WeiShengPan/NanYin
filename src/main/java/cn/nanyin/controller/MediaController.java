package cn.nanyin.controller;

import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.dao.AudioDao;
import cn.nanyin.dao.MediaDao;
import cn.nanyin.model.Audio;
import cn.nanyin.model.AudioSort;
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
@AdminAuthority(authorityTypes = AuthorityType.MEDIA_MANAGEMENT)
public class MediaController {
    @Autowired
    private AudioDao mediaDao;

    //显示新闻列表页面
    @RequestMapping(value="nyadmin/medialist",method= RequestMethod.GET)
    public ModelAndView showMediaList()
    {
        ModelAndView model = new ModelAndView("nyadmin/medialist");
        List<Audio> mediaList=mediaDao.getAudioList();
        List<AudioSort> mediaSortList=mediaDao.getAudioSortList(0, 50);
        model.addObject("mediaSortList",mediaSortList);
        model.addObject("mediaList",mediaList);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "nyadmin/medialistbysort/{sortid}", method = RequestMethod.GET)
    public List<MediaData> getAudioListBySort(@PathVariable Long sortid) {
        AudioSort mediaSort=mediaDao.getAudioSortById(sortid);
        List<Audio> mediaList=mediaSort.getAudios();
        List<MediaData> mediaDataList=new ArrayList<MediaData>();
        for(int i=0;i<mediaList.size();i++)
        {
            Audio mediaTmp=mediaList.get(i);
            long id=mediaTmp.getId();
            String title=mediaTmp.getTitle();
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
        List<AudioSort> mediaSortList=mediaDao.getAudioSortList(0, 50);
        model.addObject("mediaSortList",mediaSortList);
        return model;
    }

    //添加新闻
    @RequestMapping(value="nyadmin/mediaadd",method = RequestMethod.POST)
    public ModelAndView addMedia(Audio media,BindingResult result)
    {
        media.setAddDate(new Date());
        mediaDao.addAudio(media);
        return new ModelAndView("redirect:mediaaddpage");
    }

    //显示新闻种类列表页面
    @RequestMapping(value="nyadmin/mediasort",method=RequestMethod.GET)
    public ModelAndView showMediaSortList()
    {
        ModelAndView model=new ModelAndView("nyadmin/mediasort");

        List<AudioSort> mediaSortList=mediaDao.getAudioSortList(0, 50);
        model.addObject("mediaSortList", mediaSortList);
        return model;
    }

    //增加新闻种类
    @RequestMapping(value="nyadmin/mediasortadd",method=RequestMethod.POST)
    public ModelAndView addMediaSort(AudioSort mediaSort)
    {
        //if(newsSort.getUpperNewsSort().getId()!=1)
        int level=mediaDao.getAudioSortById(mediaSort.getUpperAudioSort().getId()).getLevel();
        mediaSort.setLevel(level + 1);
        mediaDao.addAudioSort(mediaSort);
        return new ModelAndView("redirect:mediasort");
    }

    //删除新闻
    @RequestMapping(value="nyadmin/mediadelete",method = RequestMethod.GET)
    public ModelAndView deleteMedia(long id)
    {
        Audio media = mediaDao.getAudioById(id);
        mediaDao.deleteAudio(media);
        return new ModelAndView("redirect:medialist");
    }

    //删除新闻种类
    @RequestMapping(value="nyadmin/mediasortdelete",method = RequestMethod.GET)
    public ModelAndView deleteMediaSort(long id)
    {
        AudioSort mediaSort=mediaDao.getAudioSortById(id);

        //级联删除所有该种类新闻
        List<Audio> mediaList=mediaSort.getAudios();
        for(int i=0;i<mediaList.size();i++)
        {
            Audio mediaTmp1 =mediaList.get(i);
            mediaSort.removeAudio(mediaTmp1);
            mediaTmp1.setAudioSort(null);
            mediaDao.updateAudio(mediaTmp1);
            mediaDao.deleteAudio(mediaTmp1);
        }

        //级联删除所有下层新闻种类
        List<AudioSort> lowerMediaSortList=mediaSort.getLowerAudioSortList();
        for(int i=0;i<lowerMediaSortList.size();i++)
        {
            AudioSort mediaSortTmp = lowerMediaSortList.get(i);
            List<Audio> lowerMediaList = mediaSortTmp.getAudios();
            for (int j = 0; j < lowerMediaList.size();j++)
            {
                Audio mediaTmp2=lowerMediaList.get(i);
                mediaSortTmp.removeAudio(mediaTmp2);
                mediaTmp2.setAudioSort(null);
                mediaDao.updateAudio(mediaTmp2);
                mediaDao.deleteAudio(mediaTmp2);
            }
            mediaSort.removeAudioSort(mediaSortTmp);
            mediaSortTmp.setUpperAudioSort(null);
            mediaDao.updateAudioSort(mediaSortTmp);
            mediaDao.deleteAudioSort(mediaSortTmp);
        }

        mediaDao.deleteAudioSort(mediaSort);
        return new ModelAndView("redirect:mediasort");
    }

    //显示修改新闻页面
    @RequestMapping(value="nyadmin/mediaeditpage",method = RequestMethod.GET)
    public ModelAndView showMediasEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/mediaedit");
        Audio media=mediaDao.getAudioById(id);
        model.addObject("media", media);
        List<AudioSort> mediaSortList=mediaDao.getAudioSortList(0, 50);
        model.addObject("mediaSortList", mediaSortList);
        return model;
    }

    //修改新闻
    @RequestMapping(value="nyadmin/mediaedit",method = RequestMethod.POST)
    public ModelAndView editMedia(Audio media)
    {
        Audio targetMedia=mediaDao.getAudioById(media.getId());
        targetMedia.setTitle(media.getTitle());
        targetMedia.setSinger(media.getSinger());
        targetMedia.setPlayer(media.getPlayer());
        targetMedia.setProducer(media.getProducer());
        targetMedia.setGcp(media.getGcp());
        targetMedia.setJp(media.getJp());
        targetMedia.setPath(media.getPath());
        targetMedia.setContent(media.getContent());
        targetMedia.setAudioSort(mediaDao.getAudioSortById(media.getAudioSort().getId()));
        targetMedia.setAddDate(mediaDao.getAudioById(media.getId()).getAddDate());
        targetMedia.setHits(mediaDao.getAudioById(media.getId()).getHits());
        mediaDao.updateAudio(targetMedia);
        return new ModelAndView("redirect:medialist");
    }


    @RequestMapping(value="nyadmin/mediasorteditpage",method = RequestMethod.GET)
    public ModelAndView showMediaSortEditPage(long id)
    {
        ModelAndView model=new ModelAndView("nyadmin/mediasortedit");
        AudioSort mediaSort=mediaDao.getAudioSortById(id);
        model.addObject("mediaSort", mediaSort);
        List<AudioSort> mediaSortList=mediaDao.getAudioSortList(0, 50);
        model.addObject("mediaSortList", mediaSortList);
        return model;
    }

    @RequestMapping(value="nyadmin/mediasortedit",method = RequestMethod.POST)
    public ModelAndView editMediaSort(AudioSort mediaSort)
    {
        AudioSort targetMediaSort=mediaDao.getAudioSortById(mediaSort.getId());
        targetMediaSort.setName(mediaSort.getName());
        targetMediaSort.setUpperAudioSort(mediaDao.getAudioSortById(mediaSort.getUpperAudioSort().getId()));
        targetMediaSort.setLevel(mediaDao.getAudioSortById(mediaSort.getId()).getLevel());
        mediaDao.updateAudioSort(targetMediaSort);
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
    public FileUploadResult uploadMedia(@RequestParam MultipartFile path, HttpSession session) {
        String basePath = "/upload/media/video/";
        FileUpload fileUpload=new FileUpload(basePath,path,session);
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