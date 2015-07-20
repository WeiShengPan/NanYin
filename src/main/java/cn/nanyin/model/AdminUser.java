package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/10.
 */
@Entity
public class AdminUser {
    @Id
    @GeneratedValue
    private long id;
    private String adminName;   //管理员账户
    private String password;
    private String name;        //管理员真实姓名
    private String permission;  //权限
    private Date lastLoginDate; //最近登录时间
    private String lastLoginIP; //最近登录IP
    private int state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
