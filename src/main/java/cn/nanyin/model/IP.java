package cn.nanyin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2015/7/10.
 */
@Entity
public class IP {
    @Id
    @GeneratedValue
    private long id;//1234

    private String starIP;

    private String endIP;

    private String location;//所在地

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEndIP() {
        return endIP;
    }

    public void setEndIP(String endIP) {
        this.endIP = endIP;
    }

    public String getStarIP() {
        return starIP;
    }

    public void setStarIP(String starIP) {
        this.starIP = starIP;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
