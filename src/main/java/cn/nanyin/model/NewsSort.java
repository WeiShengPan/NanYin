package cn.nanyin.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class NewsSort {
    @Id
    @GeneratedValue
    private long id;
    private int level;       //���༶��
    private String name;    //����
    private long upperId;   //���������
    private int priority;  //?
    private int state;      //?

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "sort")
    private Set<News> news;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUpperId() {
        return upperId;
    }

    public void setUpperId(long upperId) {
        this.upperId = upperId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }
}
