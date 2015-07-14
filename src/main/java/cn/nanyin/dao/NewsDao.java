package cn.nanyin.dao;

import cn.nanyin.model.News;
import cn.nanyin.model.NewsSort;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ���� on 2015/7/13.
 */
@Repository
@Transactional
public class NewsDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<News> getNewsList(Integer start,Integer max)
    {
        Query query=sessionFactory.getCurrentSession().createQuery("from news");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<NewsSort> getNewsSortList(Integer start,Integer max)
    {
        Query query=sessionFactory.getCurrentSession().createQuery("from newssort");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }
}
