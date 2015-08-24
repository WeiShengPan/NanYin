package cn.nanyin.dao;

import cn.nanyin.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/12.
 */
@Repository
@Transactional
public class CommentsDao {

    @Autowired
    private SessionFactory sessionFactory;

    /***********************************新闻**************************************************************************/
    public List<NewsComments> getNewsCommentsList(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from NewsComments where news.id="+id+" order by addDate desc");
        return query.list();
    }

    public Serializable addNewsComments(NewsComments newsComments) {
        Serializable result= sessionFactory.getCurrentSession().save(newsComments);
        return result;
    }

    public void deleteNewsComments(NewsComments newsComments) {
        sessionFactory.getCurrentSession().delete(newsComments);
    }
    /***********************************视频**************************************************************************/
    public List<VideoComments> getVideoCommentsList(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from VideoComments where video.id=" + id + " order by addDate desc");
        return query.list();
    }

    public Serializable addVideoComments(VideoComments videoComments) {
        Serializable result= sessionFactory.getCurrentSession().save(videoComments);
        return result;
    }

    public void deleteVideoComments(VideoComments videoComments) {
        sessionFactory.getCurrentSession().delete(videoComments);
    }

    /***********************************音频**************************************************************************/
    public List<AudioComments> getAudioCommentsList(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from AudioComments where audio.id=" + id + " order by addDate desc");
        return query.list();
    }

    public Serializable addAudioComments(AudioComments audioComments) {
        Serializable result= sessionFactory.getCurrentSession().save(audioComments);
        return result;
    }

    public void deleteAudioComments(AudioComments audioComments) {
        sessionFactory.getCurrentSession().delete(audioComments);
    }

    /***********************************教学**************************************************************************/
    public List<TeachingComments> getTeachingCommentsList(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from TeachingComments where teaching.id="+id+" order by addDate desc");
        return query.list();
    }

    public Serializable addTeachingComment(TeachingComments teachingComments) {
        Serializable result= sessionFactory.getCurrentSession().save(teachingComments);
        return result;
    }

    public void deleteTeachingComments(TeachingComments teachingComments) {
        sessionFactory.getCurrentSession().delete(teachingComments);
    }
}
