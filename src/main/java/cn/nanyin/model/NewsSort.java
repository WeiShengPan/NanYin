package cn.nanyin.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class NewsSort {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "newsSort")
    private List<News> news;

    @ManyToOne
    private NewsSort upperNewsSort;
    @OneToMany(mappedBy = "upperNewsSort")
    private List<NewsSort> lowerNewsSortList;

    private int level;
    private String name;
    private int priority;
    private int state;

    public void removeNews(News n)
    {
        for(int i=0;i<news.size();i++)
        {
            if(news.get(i).getId()==n.getId())
                news.remove(i);
        }
    }

    public void removeNewsSort(NewsSort ns)
    {
        for(int i=0;i<lowerNewsSortList.size();i++)
        {
            if(lowerNewsSortList.get(i).getId()==ns.getId())
                lowerNewsSortList.remove(i);
        }
    }

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

    public NewsSort getUpperNewsSort() {
        return upperNewsSort;
    }

    public void setUpperNewsSort(NewsSort upperNewsSort) {
        this.upperNewsSort = upperNewsSort;
    }

    public List<NewsSort> getLowerNewsSortList() {
        return lowerNewsSortList;
    }

    public void setLowerNewsSortList(List<NewsSort> lowerNewsSortList) {
        this.lowerNewsSortList = lowerNewsSortList;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
