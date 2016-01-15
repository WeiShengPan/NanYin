package cn.nanyin.controller;

import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.dao.CommentsDao;
import cn.nanyin.model.AudioComments;
import cn.nanyin.model.NewsComments;
import cn.nanyin.model.TeachingComments;
import cn.nanyin.model.VideoComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by MYNP on 2015/8/18 0018.
 */
@Controller
@AdminAuthority(authorityTypes = AuthorityType.COMMENTS_MANAGEMENT)
public class CommentController {
    @Autowired
    private CommentsDao commentsDao;

    //展示留言列表
    @RequestMapping(value="/nyadmin/commentslist",method= RequestMethod.GET)
    public ModelAndView showCollegeList() {
        ModelAndView model=new ModelAndView("nyadmin/commentslist");
        List<NewsComments> newsCommentsList =commentsDao.getNewsCommentsList();
        model.addObject("newsCommentsList", newsCommentsList);

        List<VideoComments> videoCommentsList =commentsDao.getVideoCommentsList();
        model.addObject("videoCommentsList", videoCommentsList);

        List<AudioComments> audioCommentsList =commentsDao.getAudioCommentsList();
        model.addObject("audioCommentsList", audioCommentsList);

        List<TeachingComments> teachingCommentsList =commentsDao.getTeachingCommentsList();
        model.addObject("teachingCommentsList", teachingCommentsList);
        return model;
    }

    //删除新闻留言
    @RequestMapping(value="nyadmin/newscommentsdelete",method = RequestMethod.GET)
    public ModelAndView deleteNewsComments(long id) {
        NewsComments newsComments = commentsDao.getNewsCommentsById(id);
        commentsDao.deleteNewsComments(newsComments);
        return new ModelAndView("redirect:commentslist");
    }

    //显示新闻留言详细页面
    @RequestMapping(value="nyadmin/newscommentspage",method = RequestMethod.GET)
    public ModelAndView showNewsCommentsPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/newscommentspage");
        NewsComments newsComments=commentsDao.getNewsCommentsById(id);
        model.addObject("newsComments", newsComments);
        return model;
    }

    //删除视频留言
    @RequestMapping(value="nyadmin/videocommentsdelete",method = RequestMethod.GET)
    public ModelAndView deleteVideoComments(long id) {
        VideoComments videoComments = commentsDao.getVideoCommentsById(id);
        commentsDao.deleteVideoComments(videoComments);
        return new ModelAndView("redirect:commentslist");
    }

    //显示视频留言详细页面
    @RequestMapping(value="nyadmin/videocommentspage",method = RequestMethod.GET)
    public ModelAndView showVideoCommentsPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/videocommentspage");
        VideoComments videoComments=commentsDao.getVideoCommentsById(id);
        model.addObject("videoComments", videoComments);
        return model;
    }

    //删除音频留言
    @RequestMapping(value="nyadmin/audiocommentsdelete",method = RequestMethod.GET)
    public ModelAndView deleteAudioComments(long id) {
        AudioComments audioComments = commentsDao.getAudioCommentsById(id);
        commentsDao.deleteAudioComments(audioComments);
        return new ModelAndView("redirect:commentslist");
    }

    //显示音频留言详细页面
    @RequestMapping(value="nyadmin/audiocommentspage",method = RequestMethod.GET)
    public ModelAndView showAudioCommentsPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/audiocommentspage");
        AudioComments audioComments=commentsDao.getAudioCommentsById(id);
        model.addObject("audioComments", audioComments);
        return model;
    }

    //删除教学留言
    @RequestMapping(value="nyadmin/teachingcommentsdelete",method = RequestMethod.GET)
    public ModelAndView deleteTeachingComments(long id) {
        TeachingComments teachingComments = commentsDao.getTeachingCommentsById(id);
        commentsDao.deleteTeachingComments(teachingComments);
        return new ModelAndView("redirect:commentslist");
    }

    //显示教学留言详细页面
    @RequestMapping(value="nyadmin/teachingcommentspage",method = RequestMethod.GET)
    public ModelAndView showTeachingCommentsPage(long id) {
        ModelAndView model=new ModelAndView("nyadmin/teachingCommentspage");
        TeachingComments teachingComments=commentsDao.getTeachingCommentsById(id);
        model.addObject("teachingComments", teachingComments);
        return model;
    }
}
