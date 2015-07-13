package cn.nanyin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2015/7/10.
 */
//网站基本配置类
@Entity
public class BasicConfig {

    @Id
    @GeneratedValue
    private long id;

    private String bgmURL;//背景音乐

    private String bulletin;//系统公告

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBgmURL() {
        return bgmURL;
    }

    public void setBgmURL(String bgmURL) {
        this.bgmURL = bgmURL;
    }

    public String getBulletin() {
        return bulletin;
    }

    public void setBulletin(String bulletin) {
        this.bulletin = bulletin;
    }
}
