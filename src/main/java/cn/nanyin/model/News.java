package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class News {
    @Id
    @GeneratedValue

    private long id;
    private Newssort sort;  //����
    private String title;   //����
    private String author;  //����
    private String content; //����
    private int showorder;  //?
    private int tuijian;    //�Ƽ�ָ��
    private String image;   //����ͼ��ַ
    private int hits;       //�����
    private Date addtime;   //����ʱ��
    private int state;      //?

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH })
    @JoinColumn(name = "sort_id")
    public Newssort getSort() {
        return sort;
    }

    public void setSort(Newssort sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
