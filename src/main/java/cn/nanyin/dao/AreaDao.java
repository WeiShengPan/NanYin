package cn.nanyin.dao;

import cn.nanyin.model.Area;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gg on 2015/7/15.
 */
@Repository
@Transactional
public class AreaDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Area> getAreaList(Integer start,Integer max)
    {
        Query query=sessionFactory.getCurrentSession().createQuery("from Area");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addArea(Area area)
    {
        Serializable result=null;
        result=sessionFactory.getCurrentSession().save(area);
        return result;
    }

    public void updateArea(Area area)
    {
        sessionFactory.getCurrentSession().update(area);
    }

    public void deleteArea(Area area)
    {
        sessionFactory.getCurrentSession().delete(area);
    }
}
