package cn.nanyin.controller;


import cn.nanyin.adminauth.AdminAuthority;
import cn.nanyin.adminauth.AuthorityType;
import cn.nanyin.dao.CommentsDao;
import cn.nanyin.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AdminAuthority(authorityTypes = AuthorityType.COMMENTS_MANAGEMENT)
public class CommentsController {

    @Autowired
    private CommentsDao commentsDao;

    @RequestMapping(value = "nyadmin/commentslist", method = RequestMethod.GET)
    public ModelAndView showCommentsList() {
        ModelAndView model = new ModelAndView("nyadmin/commentslist");
        List<Comments> commentsList = commentsDao.getCommentsList(0, 50);
        model.addObject("commentslist", commentsList);
        return model;
    }

    @RequestMapping(value = "nyadmin/varyfycomments",method = RequestMethod.GET)
    public ModelAndView varifyComments(long id)
    {
        Comments targetComments=commentsDao.getCommentsById(id);
        targetComments.setVarify(true);
        commentsDao.updateComments(targetComments);
        return new ModelAndView("redirect:commentslist");
    }

    @RequestMapping(value = "nyadmin/commentsdelete",method = RequestMethod.GET)
    public ModelAndView commentsDelete(long id)
    {
        Comments comments=commentsDao.getCommentsById(id);
        commentsDao.deleteComments(comments);
        return new ModelAndView("redirect:commentslist");
    }

}
