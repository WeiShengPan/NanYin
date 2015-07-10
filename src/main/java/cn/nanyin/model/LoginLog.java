package cn.nanyin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/10.
 */
//登录日志类
@Entity
public class LoginLog {

    @Id
    @GeneratedValue
    private long id;//

    private Date logDate;//登录日期

    private String ip;//登录IP

    private String password;//尝试登录所用密码

    private String action;//登录动作

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
