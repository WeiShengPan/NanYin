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
    private Mediasort sort;     //����
    private String name;        //����
    private String singer;      //�ݳ�
    private String performer;   //����
    private String cameraman;   //��Ӱ
    private String producer;    //����
    private int playtype;       //?
    private String url;         //ý���ַ
    private String pic;         //����ͼ��ַ
    private String gcp;         //�����׵�ַ
    private String jp;          //���׵�ַ
    private String content;     //˵��
    private int tuijian;        //�Ƽ�ָ��
    private Date addtime;       //����ʱ��
    private int hits;           //�����
    private int state;          //?

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH })
    @JoinColumn(name = "sort_id")
    public Mediasort getSort() {
        return sort;
    }

    public void setSort(Mediasort sort) {
        this.sort = sort;
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

    public int getTuijian() {
        return tuijian;
    }

    public void setTuijian(int tuijian) {
        this.tuijian = tuijian;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
}
