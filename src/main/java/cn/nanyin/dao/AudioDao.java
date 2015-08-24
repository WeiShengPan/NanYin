package cn.nanyin.dao;

import cn.nanyin.model.Audio;
import cn.nanyin.model.Video;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Audio> findAudios(String text) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Audio where title like '%"+text+"%'");
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
}
