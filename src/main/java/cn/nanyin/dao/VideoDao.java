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
 * Created by 张一平 on 2015/8/11.
 */
@Repository
@Transactional
public class VideoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Video getVideoById(long id)
    {
        return (Video)sessionFactory.getCurrentSession().get(Video.class, id);
    }

    public List<Video> getVideoList(Integer start, Integer max) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Video  order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<Video> getVideoList(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Video order by addDate desc");
        return query.list();
    }

    public List<Video> getVideoList(String typeName){
        Query query = sessionFactory.getCurrentSession().createQuery("from Video where videoSort.name='"+typeName+"' order by addDate desc");
        return query.list();
    }
    public List<Video> getAudioList(String typeName,Integer start, Integer max){
        Query query = sessionFactory.getCurrentSession().createQuery("from Video where videoSort.name='"+typeName+"' order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }
    public int getNum(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Video");
        int n=query.list().size();
        return n;
    }
    public int getNum(String typeName){
        Query query = sessionFactory.getCurrentSession().createQuery("from Video where videoSort.name='"+typeName+"'");
        int n=query.list().size();
        return n;
    }

    public List<Video> findVideos(String text) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Video where title like '%"+text+"%'");
        return query.list();
    }

    public void updateVideo(Video video)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(video);
    }

    public List<VideoSort> getVideoSortList(Integer start,Integer max)
    {
        Query query=sessionFactory.getCurrentSession().createQuery("from VideoSort");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public VideoSort getVideoSortById(long id)
    {
        return (VideoSort)sessionFactory.getCurrentSession().get(VideoSort.class,id);
    }

    public Serializable addVideo(Video video) {
        Serializable result =  sessionFactory.getCurrentSession().save(video);
        return result;
    }

    public Serializable addVideoSort(VideoSort videoSort) {
        Serializable result = sessionFactory.getCurrentSession().save(videoSort);
        return result;
    }

    public void deleteVideo(Video video) {
        sessionFactory.getCurrentSession().delete(video);
    }

    public void deleteVideoSort(VideoSort videoSort) {
        sessionFactory.getCurrentSession().delete(videoSort);
    }

    public void updateVideoSort(VideoSort videoSort)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(videoSort);
    }

}
