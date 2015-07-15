package cn.nanyin.dao;

import cn.nanyin.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gg on 2015/7/14.
 */
@Repository
@Transactional
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    public List<User> getUserList()
    {
        Query query=sessionFactory.getCurrentSession().createQuery("from user");
//        query.setFirstResult(start);
//        query.setMaxResults(max);
        return query.list();
    }
}
