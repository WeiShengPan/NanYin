package cn.nanyin.dao;

import cn.nanyin.model.News;
import cn.nanyin.model.NewsSort;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ���� on 2015/7/13.
 */
@Repository
@Transactional
public class NewsDao {
    @Autowired
    private SessionFactory sessionFactory;

    public News getNewsById(long id) {
        return (News) sessionFactory.getCurrentSession().get(News.class, id);
    }

    public NewsSort getNewsSortById(long id) {
        return (NewsSort) sessionFactory.getCurrentSession().get(NewsSort.class, id);
    }

    public List<News> getNewsList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from News");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<NewsSort> getNewsSortList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from NewsSort");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addNewsSort(NewsSort newsSort) {
        Serializable result = null;
        result = sessionFactory.getCurrentSession().save(newsSort);
        return result;
    }

    public Serializable addNews(News news) {
        Serializable result = null;
        result = sessionFactory.getCurrentSession().save(news);
        return result;
    }

    public void deleteNews(News news) {
        sessionFactory.getCurrentSession().delete(news);
    }

    public void deleteNewsSort(NewsSort newsSort) {
        sessionFactory.getCurrentSession().delete(newsSort);
    }
}
