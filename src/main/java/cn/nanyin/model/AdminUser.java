package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/10.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AdminUser extends  User{

    private String lastLoginIP;

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

}
