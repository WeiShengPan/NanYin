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
    private VideoSort videoSort;//所属视频类别

    @OneToMany(mappedBy = "video")
    private List<VideoDetail> videoDetails;

    private String title;

    private String content;//内容

    private String imageURL;//图片url

    private int hits;//点击量

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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }


    public VideoSort getVideoSort() {
        return videoSort;
    }

    public void setVideoSort(VideoSort videoSort) {
        this.videoSort = videoSort;
    }

    public List<VideoDetail> getVideoDetails() {
        return videoDetails;
    }

    public void setVideoDetails(List<VideoDetail> videoDetails) {
        this.videoDetails = videoDetails;
    }
}

