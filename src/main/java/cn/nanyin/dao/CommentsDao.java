package cn.nanyin.dao;

import cn.nanyin.model.Comments;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public class CommentsDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Serializable addComments(Comments comments)
    {
        Serializable result=sessionFactory.getCurrentSession().save(comments);
        return result;
    }

    public List<Comments> getCommentsList(Integer start,Integer max)
    {
        Query query=sessionFactory.getCurrentSession().createQuery("from Comments as c order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Comments getCommentsById(long id)
    {
        return (Comments)sessionFactory.getCurrentSession().get(Comments.class,id);
    }

    public void deleteComments(Comments comments)
    {
        sessionFactory.getCurrentSession().delete(comments);
    }

    public void updateComments(Comments comments)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(comments);
    }
}
