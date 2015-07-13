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
    private MediaSorts mediaSorts;     //����
    private String name;        //����
    private String singer;      //�ݳ�
    private String performer;   //����
    private String cameraman;   //��Ӱ
    private String producer;    //����
    private int playtype;       //?
    private String url;         //ý���ַ
    private String pic;         //����ͼ��ַ
    private String gcp;         //工尺谱
    private String jp;          //简谱
    private String content;     //˵��
    private int recommendation;        //�Ƽ�ָ��
    private Date addDate;       //����ʱ��
    private int hits;           //�����
    private int state;          //?

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MediaSorts getMediaSorts() {
        return mediaSorts;
    }

    public void setMediaSorts(MediaSorts mediaSorts) {
        this.mediaSorts = mediaSorts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlaytype() {
        return playtype;
    }

    public void setPlaytype(int playtype) {
        this.playtype = playtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public int getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(int recommendation) {
        this.recommendation = recommendation;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
}
