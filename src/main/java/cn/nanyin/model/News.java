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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "sort_id")
    private NewsSort newsSort;  //����
    private String title;   //����
    private String author;  //����
    @Lob
    private String content; //����
    private int priority;  //?
    private String image;   //����ͼ��ַ
    private int hits;       //�����
    private Date addDate;   //����ʱ��
    private int state;      //?
    private String source;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public NewsSort getNewsSort() {
        return newsSort;
    }

    public void setNewsSort(NewsSort newsSort) {
        this.newsSort = newsSort;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
