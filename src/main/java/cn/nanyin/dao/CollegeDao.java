package cn.nanyin.dao;

import cn.nanyin.model.College;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gg on 2015/7/20.
 */
@Repository
@Transactional
public class CollegeDao {
    @Autowired
    private SessionFactory sessionFactory;

    public College getCollegeById(long id) {
        return (College) sessionFactory.getCurrentSession().get(College.class, id);
    }

    public List<College> getCollegeList(Integer start,Integer max) {
        Query query=sessionFactory.getCurrentSession().createQuery("from College");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addCollege(College college) {
        Serializable result=null;
        result=sessionFactory.getCurrentSession().save(college);
        return result;
    }

    public void updateCollege(College college)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(college);
    }

    public void deleteCollege(College college)
    {
        sessionFactory.getCurrentSession().delete(college);
    }
}
