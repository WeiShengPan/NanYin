package cn.nanyin.dao;

import cn.nanyin.model.Links;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Repository
@Transactional
public class LinksDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Links getLinksById(long id)
    {
        return (Links)sessionFactory.getCurrentSession().get(Links.class,id);
    }

    public Serializable addLinks(Links links)
    {
        Serializable result=sessionFactory.getCurrentSession().save(links);
        return result;
    }

    public List<Links> getLinksList(Integer start,Integer max)
    {
        Query query=sessionFactory.getCurrentSession().createQuery("from Links as links order by priority desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public void updateLinks(Links links)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(links);
    }

    public void deleteLinks(Links links)
    {
        sessionFactory.getCurrentSession().delete(links);
    }
}
