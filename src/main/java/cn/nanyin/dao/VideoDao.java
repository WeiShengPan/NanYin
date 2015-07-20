package cn.nanyin.dao;

import cn.nanyin.model.Video;
import cn.nanyin.model.VideoSort;
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
public class VideoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Video getVideoById(long id) {
        return (Video) sessionFactory.getCurrentSession().get(Video.class, id);
    }

    public VideoSort getVideoSortById(long id) {
        return (VideoSort) sessionFactory.getCurrentSession().get(VideoSort.class, id);
    }

    public List<Video> getVideoList(Integer start, Integer max) {
        //from Video as Video order by addDate desc
        Query query = sessionFactory.getCurrentSession().createQuery("from Video as video order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<VideoSort> getVideoSortList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from VideoSort");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addVideoSort(VideoSort videoSort) {
        Serializable result = null;
        result = sessionFactory.getCurrentSession().save(videoSort);
        return result;
    }

    public Serializable addVideo(Video video) {
        Serializable result = null;
        result = sessionFactory.getCurrentSession().save(video);
        return result;
    }

    public void deleteVideo(Video video) {
        sessionFactory.getCurrentSession().delete(video);
    }

    public void deleteVideoSort(VideoSort videoSort) {
        sessionFactory.getCurrentSession().delete(videoSort);
    }

    public void updateVideo(Video video)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(video);
    }

    public void updateVideoSort(VideoSort videoSort)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(videoSort);
    }
}
