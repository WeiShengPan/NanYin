package cn.nanyin.dao;


import cn.nanyin.model.Announcement;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/4.
 */

@Repository
@Transactional
public class AnnouncementDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Announcement getAnnouncementById(long id) {
        return (Announcement) sessionFactory.getCurrentSession().get(Announcement.class, id);
    }

    public List<Announcement> getAnnouncementList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Announcement order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<Announcement> getAnnouncementList(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Announcement order by addDate desc");
        return query.list();
    }

    public int getNum(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Announcement");
        int n=query.list().size();
        return n;
    }

    public Serializable addAnnouncement(Announcement announcement)
    {
        Serializable result=sessionFactory.getCurrentSession().save(announcement);
        return result;
    }

    public void deleteAnnouncement(Announcement announcement)
    {
        sessionFactory.getCurrentSession().delete(announcement);
    }

    public void updateAnnouncement(Announcement announcement)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(announcement);

    }


}
