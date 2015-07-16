package cn.nanyin.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/7/10.
 */
@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn
public class AdminUser
        extends User
{

//    @GeneratedValue(strategy = GenerationType.TABLE)
//    @Id
//    @GeneratedValue
//    private long id;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    private String lastLoginIP;

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

}
