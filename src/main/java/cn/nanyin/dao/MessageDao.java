package cn.nanyin.dao;

import cn.nanyin.model.Message;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public class MessageDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Serializable addMessage(Message message)
    {
        Serializable result=sessionFactory.getCurrentSession().save(message);
        return result;
    }

    public List<Message> getMessageList(Integer start,Integer max)
    {
        Query query=sessionFactory.getCurrentSession().createQuery("from Message as m order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Message getMessageById(long id)
    {
        return (Message)sessionFactory.getCurrentSession().get(Message.class,id);
    }

    public void deleteMessage(Message message)
    {
        sessionFactory.getCurrentSession().delete(message);
    }

    public void updateMessage(Message message)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(message);
    }
}
