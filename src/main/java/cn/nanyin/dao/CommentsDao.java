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
    public List<NewsComments> getNewsCommentsList(long newId){
        Query query = sessionFactory.getCurrentSession().createQuery("from NewsComments where news.id="+newId+" order by addDate desc");
        return query.list();
    }

    public NewsComments getNewsCommentsById(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from NewsComments where id="+id+"");
        return (NewsComments)query.list().get(0);
    }

    public List<NewsComments> getNewsCommentsList(Integer start, Integer max){
        Query query = sessionFactory.getCurrentSession().createQuery("from NewsComments order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }
    public List<NewsComments> getNewsCommentsList( ){
        Query query = sessionFactory.getCurrentSession().createQuery("from NewsComments order by addDate desc");
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
    public List<VideoComments> getVideoCommentsList(long videoId){
        Query query = sessionFactory.getCurrentSession().createQuery("from VideoComments where video.id=" + videoId + " order by addDate desc");
        return query.list();
    }

    public VideoComments getVideoCommentsById(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from VideoComments where id="+id+"");
        return (VideoComments)query.list().get(0);
    }

    public List<VideoComments> getVideoCommentsList(Integer start, Integer max){
        Query query = sessionFactory.getCurrentSession().createQuery("from VideoComments order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }
    public List<VideoComments> getVideoCommentsList(){
        Query query = sessionFactory.getCurrentSession().createQuery("from VideoComments order by addDate desc");
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
    public List<AudioComments> getAudioCommentsList(long audioId){
        Query query = sessionFactory.getCurrentSession().createQuery("from AudioComments where audio.id=" + audioId + " order by addDate desc");
        return query.list();
    }

    public AudioComments getAudioCommentsById(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from AudioComments where id="+id+"");
        return (AudioComments)query.list().get(0);
    }

    public List<AudioComments> getAudioCommentsList(Integer start, Integer max){
        Query query = sessionFactory.getCurrentSession().createQuery("from AudioComments order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }
    public List<AudioComments> getAudioCommentsList(){
        Query query = sessionFactory.getCurrentSession().createQuery("from AudioComments order by addDate desc");
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
    public List<TeachingComments> getTeachingCommentsList(long teachingId){
        Query query = sessionFactory.getCurrentSession().createQuery("from TeachingComments where teaching.id="+teachingId+" order by addDate desc");
        return query.list();
    }

    public TeachingComments getTeachingCommentsById(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from TeachingComments where id="+id+"");
        return (TeachingComments)query.list().get(0);
    }

    public List<TeachingComments> getTeachingCommentsList(Integer start, Integer max){
        Query query = sessionFactory.getCurrentSession().createQuery("from TeachingComments order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }
    public List<TeachingComments> getTeachingCommentsList(){
        Query query = sessionFactory.getCurrentSession().createQuery("from TeachingComments order by addDate desc");
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
