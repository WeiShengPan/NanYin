package cn.nanyin.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/7/10.
 */
@Entity
public class AdminUser {
    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String lastLoginIP;

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

}
