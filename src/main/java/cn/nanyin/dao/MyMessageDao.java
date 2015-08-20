package cn.nanyin.dao;

import cn.nanyin.model.MyMessage;
import cn.nanyin.model.User1;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/10.
 */
@Repository
@Transactional
public class MyMessageDao {
    @Autowired
    private SessionFactory sessionFactory;

    public MyMessage getMessageById(long id) {return (MyMessage) sessionFactory.getCurrentSession().get(MyMessage.class, id);}

    public List<MyMessage> getMessage(long id){
        Query query = sessionFactory.getCurrentSession().createQuery("from MyMessage where user.id="+id+"  order by date desc");
        return query.list();
    }
    public void updateNews(MyMessage myMessage)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(myMessage);
    }
    public void deleteMessage(MyMessage myMessage)
    {
        sessionFactory.getCurrentSession().delete(myMessage);
    }

    public Serializable addMessage(MyMessage myMessage) {
        Serializable result=null;
        result=sessionFactory.getCurrentSession().save(myMessage);
        return result;
    }

}
