package cn.nanyin.dao;

import cn.nanyin.model.News;
import cn.nanyin.model.Product;
import cn.nanyin.model.ProductSort;
import cn.nanyin.model.Teaching;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 张一平 on 2015/8/20.
 */
@Repository
@Transactional
public class TeachingDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Teaching getTeachingById(long id) {
        return (Teaching) sessionFactory.getCurrentSession().get(Teaching.class, id);
    }

    public List<Teaching> getTeachingList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Teaching order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<Teaching> getTeachingList(String typeName,Integer start, Integer max){
        Query query = sessionFactory.getCurrentSession().createQuery("from Teaching where teachingSort.name='"+typeName+"' order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }
    public int getNum(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Teaching");
        int num=query.list().size();
        return num;
    }
    public int getNum(String typeName){
        Query query = sessionFactory.getCurrentSession().createQuery("from Teaching where teachingSort.name='"+typeName+"'");
        int num=query.list().size();
        return num;
    }

    public void updateTeaching(Teaching teaching)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(teaching);
    }

    public void deleteTeaching(Teaching teaching) {
        sessionFactory.getCurrentSession().delete(teaching);
    }


}
