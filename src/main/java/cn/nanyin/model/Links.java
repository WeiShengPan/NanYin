package cn.nanyin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2015/7/10.
 */
//友情链接类
@Entity
public class Links {
    @Id
    @GeneratedValue
    private long id;//

    private String name;

    private String url;//链接URL

    private String file;//图片所在url

    private boolean linkType;//链接类型（图片链接/文字链接）

    private int priority;//优先级



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLinkType() {
        return linkType;
    }

    public void setLinkType(boolean linkType) {
        this.linkType = linkType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
