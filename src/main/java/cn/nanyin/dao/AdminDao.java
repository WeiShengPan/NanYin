package cn.nanyin.dao;

import cn.nanyin.model.AdminUser;
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
public class AdminDao {
    @Autowired
    private SessionFactory sessionFactory;

    public AdminUser getAdminUserById(long id) {
        return (AdminUser) sessionFactory.getCurrentSession().get(AdminUser.class, id);
    }

    public List<AdminUser> getAdminUserList(Integer start,Integer max) {
        Query query=sessionFactory.getCurrentSession().createQuery("from AdminUser");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addAdminUser(AdminUser adminUser) {
        Serializable result=null;
        result=sessionFactory.getCurrentSession().save(adminUser);
        return result;
    }

    public void updateAdminUser(AdminUser adminUser)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(adminUser);
    }

    public void deleteAdminUser(AdminUser adminUser)
    {
        sessionFactory.getCurrentSession().delete(adminUser);
    }
}
