package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/12.
 */
@Entity                                                             /**************************音频表****************/
public class Audio {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "sort_id")
    private AudioSort audioSort;

    private String title;
    private String singer; //演唱者
    private String player;  //演奏者
    private String producer;//制作人
    private long hits;
    private String path;
    private Date addDate;

    private String content;     //说明
    private String gcp;         //工尺谱
    private String jp;          //简谱


    public long getId(){
        return this.id;
    }
    public  void setId( long id){
        this.id=id;
    }
    public  String getTitle(){
        return  this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public  String getSinger(){
        return this.singer;
    }
    public void setSinger(String singer){
        this.singer=singer;
    }
    public String getPlayer(){
        return this.player;
    }
    public void setPlayer(String player){
        this.player=player;
    }
    public String getProducer(){
        return this.producer;
    }
    public void setProducer(String producer){
        this.producer=producer;
    }
    public String getPath(){
        return this.path;
    }
    public void setPath(String path){
        this.path=path;
    }
    public long getHits(){
        return this.hits;
    }
    public  void setHits(long hits){
        this.hits=hits;
    }
    public Date getAddDate(){
        return this.addDate;
    }
    public void setAddDate(Date addDate){
        this.addDate=addDate;
    }
    public AudioSort getAudioSort(){
        return this.audioSort;
    }
    public void setAudioSort(AudioSort audioSort){
        this.audioSort=audioSort;
    }
    public String getContent(){return this.content;}
    public void setContent(String content){this.content=content;}
    public String getGcp(){return this.gcp;}
    public void setGcp(String gcp){this.gcp=gcp;}
    public String getJp(){return this.jp;}
    public void setJp(String jp){this.jp=jp;}


}
