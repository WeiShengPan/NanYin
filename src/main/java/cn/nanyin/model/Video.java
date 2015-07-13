package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/10.
 */
@Entity
public class Video {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private VideoSorts videoSort;//所属视频类别

    @OneToMany(mappedBy = "video")
    private List<VideoDetail> videoDetails;

    private String title;

    private int priority;//优先级

    private String content;//内容

    private String imageURL;//图片url

    private int hits;//点击量

    private int state;

    private Date addDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;

    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }


    public VideoSorts getVideoSort() {
        return videoSort;
    }

    public void setVideoSort(VideoSorts videoSort) {
        this.videoSort = videoSort;
    }

    public List<VideoDetail> getVideoDetails() {
        return videoDetails;
    }

    public void setVideoDetails(List<VideoDetail> videoDetails) {
        this.videoDetails = videoDetails;
    }
}

