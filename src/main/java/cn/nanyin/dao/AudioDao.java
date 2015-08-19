package cn.nanyin.dao;

import cn.nanyin.model.Audio;
import cn.nanyin.model.AudioSort;
import cn.nanyin.model.Video;
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
public class AudioDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Audio getAudioById(long id)
    {
        return (Audio)sessionFactory.getCurrentSession().get(Audio.class, id);
    }

    public List<Audio> getAudioList(Integer start, Integer max) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Audio  order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<Audio> getAudioList(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Audio order by addDate desc");
        return query.list();
    }

    public List<Audio> getAudioList(String typeName){
        Query query = sessionFactory.getCurrentSession().createQuery("from Audio where audioSort.name='"+typeName+"' order by addDate desc");
        return query.list();
    }

    public List<Audio> getAudioList(String typeName,Integer start, Integer max){
        Query query = sessionFactory.getCurrentSession().createQuery("from Audio where audioSort.name='"+typeName+"' order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public int getNum(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Audio");
        int n=query.list().size();
        return n;
    }
    public int getNum(String typeName){
        Query query = sessionFactory.getCurrentSession().createQuery("from Audio where audioSort.name='"+typeName+"'");
        int n=query.list().size();
        return n;
    }

    public void updateAudio(Audio audio)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(audio);
    }

    public AudioSort getAudioSortById(long id)
    {
        return (AudioSort)sessionFactory.getCurrentSession().get(AudioSort.class,id);
    }

    public List<AudioSort> getAudioSortList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from AudioSort");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addAudioSort(AudioSort mediaSort) {
        Serializable result = sessionFactory.getCurrentSession().save(mediaSort);
        return result;
    }

    public Serializable addAudio(Audio media) {
        Serializable result =  sessionFactory.getCurrentSession().save(media);
        return result;
    }

    public void deleteAudio(Audio media) {
        sessionFactory.getCurrentSession().delete(media);
    }

    public void deleteAudioSort(AudioSort mediaSort) {
        sessionFactory.getCurrentSession().delete(mediaSort);
    }

    public void updateAudioSort(AudioSort mediaSort)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(mediaSort);
    }
}
