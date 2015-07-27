package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class Media {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH })
    @JoinColumn(name = "sort_id")
    private MediaSort mediaSort;
    private String name;       //标题
    private String singer;      //演唱
    private String performer;   //演奏
    private String cameraman;   //摄像
    private String producer;    //制作
    private int type;
    private String media;         //媒体文件地址
    private String gcp;         //工尺谱
    private String jp;          //简谱
    @Lob
    private String content;
    private Date addDate;
    private int hits;
    private String file;    //缩略图


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MediaSort getMediaSort() {
        return mediaSort;
    }

    public void setMediaSort(MediaSort mediaSort) {
        this.mediaSort = mediaSort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getGcp() {
        return gcp;
    }

    public void setGcp(String gcp) {
        this.gcp = gcp;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }


    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getCameraman() {
        return cameraman;
    }

    public void setCameraman(String cameraman) {
        this.cameraman = cameraman;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
