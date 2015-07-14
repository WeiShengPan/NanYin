package cn.nanyin.dao;

import cn.nanyin.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by gg on 2015/7/14.
 */
public class UserDao {
    private JdbcTemplate jtl=null;

    public JdbcTemplate getJtl() {
        return jtl;
    }

    public void setJtl(JdbcTemplate jtl) {
        this.jtl = jtl;
    }

    public List<User> getUserList()
    {
        String sql="select * from user";
        List userlist=jtl.queryForList(sql);
        return userlist;
    }
}
