package cn.nanyin.dao;

import cn.nanyin.model.Directory;
import cn.nanyin.model.News;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 张一平 on 2015/8/21.
 */
@Repository
@Transactional
public class DirectoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Directory getDirectoryById(long id) {
        return (Directory) sessionFactory.getCurrentSession().get(Directory.class, id);
    }

    public List<Directory> getDirectoryList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Directory order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<Directory> getDirectoryList(String typeName,Integer start, Integer max){
        Query query = sessionFactory.getCurrentSession().createQuery("from Directory where directorySort.name='"+typeName+"' order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public int getNum(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Directory");
        int num=query.list().size();
        return num;
    }
    public int getNum(String typeName){
        Query query = sessionFactory.getCurrentSession().createQuery("from Directory where directorySort.name='"+typeName+"'");
        int num=query.list().size();
        return num;
    }

    public void updateDirectory(Directory directory)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(directory);
    }

}
