package cn.nanyin.dao;

import cn.nanyin.model.Media;
import cn.nanyin.model.MediaSort;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
@Repository
@Transactional
public class MediaDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Media getMediaById(long id)
    {
        return (Media)sessionFactory.getCurrentSession().get(Media.class,id);
    }

    public MediaSort getMediaSortById(long id)
    {
        return (MediaSort)sessionFactory.getCurrentSession().get(MediaSort.class,id);
    }

    public List<Media> getMediaList(Integer start, Integer max) {
        //from News as news order by addDate desc
        Query query = sessionFactory.getCurrentSession().createQuery("from Media as news order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<MediaSort> getMediaSortList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from MediaSort");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addMediaSort(MediaSort mediaSort) {
        Serializable result = sessionFactory.getCurrentSession().save(mediaSort);
        return result;
    }

    public Serializable addMedia(Media media) {
        Serializable result =  sessionFactory.getCurrentSession().save(media);
        return result;
    }

    public void deleteMedia(Media media) {
        sessionFactory.getCurrentSession().delete(media);
    }

    public void deleteMediaSort(MediaSort mediaSort) {
        sessionFactory.getCurrentSession().delete(mediaSort);
    }

    public void updateMedia(Media media)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(media);
    }

    public void updateMediaSort(MediaSort mediaSort)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(mediaSort);
    }
}
