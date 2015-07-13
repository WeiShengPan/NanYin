package cn.nanyin.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class Mediasort {
    @Id
    @GeneratedValue
    private long id;
    private int leve;       //分类级别
    private String name;    //分类名
    private long upperid;   //所属大分类
    private int showorder;  //?
    private String pic;     //?
    private String intro;   //?
    private int state;      //?

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "sort")
    private Set<Media> medias=new HashSet<Media>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLeve() {
        return leve;
    }

    public void setLeve(int leve) {
        this.leve = leve;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUpperid() {
        return upperid;
    }

    public void setUpperid(long upperid) {
        this.upperid = upperid;
    }

    public int getShoworder() {
        return showorder;
    }

    public void setShoworder(int showorder) {
        this.showorder = showorder;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Set<Media> getMedias() {
        return medias;
    }

    public void setMedias(Set<Media> medias) {
        this.medias = medias;
    }
}
