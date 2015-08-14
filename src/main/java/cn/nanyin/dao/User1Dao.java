package cn.nanyin.dao;

import cn.nanyin.model.User1;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by 张一平 on 2015/8/9.
 */
@Repository
@Transactional
public class User1Dao {
    @Autowired
    private SessionFactory sessionFactory;

    public User1 getUserById(long id) {return (User1) sessionFactory.getCurrentSession().get(User1.class, id);}

    public User1 findUser(String name,String psw){
        Query query = sessionFactory.getCurrentSession().createQuery("from User1 where userName ='"+name+"' and password ='"+psw+" '");
        if(query.list().size()>0){
            return (User1)query.list().get(0);
        }else{
            return null;
        }
    }

    public User1 findUser(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from User1 where userName ='"+name+"'");
        if(query.list().size()>0){
            return (User1)query.list().get(0);
        }else{
            return null;
        }
    }

    public Serializable addUser(User1 user)
    {
        Serializable result=null;
        result=sessionFactory.getCurrentSession().save(user);
        return result;
    }

    public void updateUser(User1 user)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    public void deleteUser(User1 user)
    {
        sessionFactory.getCurrentSession().delete(user);
    }

}
