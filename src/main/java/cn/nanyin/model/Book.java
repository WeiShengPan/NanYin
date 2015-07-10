package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class Book {
    private long id;
    private Booksort sort;  //分类
    private String title;   //书名
    private String author;  //作者
    private String content; //介绍
    private String downurl; //下载地址
    private int showorder;  //？
    private int tuijian;    //推荐指数
    private String image;   //缩略图地址
    private int hits;       //点击量
    private Date addtime;   //发布时间
    private int state;      //?

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public int getShoworder() {
        return showorder;
    }

    public void setShoworder(int showorder) {
        this.showorder = showorder;
    }

    public int getTuijian() {
        return tuijian;
    }

    public void setTuijian(int tuijian) {
        this.tuijian = tuijian;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH })
    @JoinColumn(name = "sort_id")
    public Booksort getSort() {
        return sort;
    }

    public void setSort(Booksort sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

