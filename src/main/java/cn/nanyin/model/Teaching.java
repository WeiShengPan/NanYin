package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/20.
 */
@Entity
public class Teaching {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "sort_id")
    private TeachingSort teachingSort;

    private String title;
    private String teacher;   //指导老师
    private Date addDate;
    private long hits;
    private String source;   //来源
    private String file;   //教学视频存放路径
    private  String content;//说明
    private String gcp;         //工尺谱
    private String jp;          //简谱


    public  long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTeacher(){
        return this.teacher;
    }
    public void setTeacher(String teacher){
        this.teacher=teacher;
    }
    public Date getAddDate(){
        return this.addDate;
    }
    public void setAddDate(Date addDate){
        this.addDate=addDate;
    }
    public long getHits(){
        return this.hits;
    }
    public void setHits(long hits){
        this.hits=hits;
    }
    public String getSource(){
        return this.source;
    }
    public void setSource(String source){
        this.source=source;
    }
    public String getFile(){
        return this.file;
    }
    public void setFile(String file){
        this.file=file;
    }
    public TeachingSort getTeachingSort(){
        return this.teachingSort;
    }
    public void setTeachingSort(TeachingSort teachingSort){
        this.teachingSort=teachingSort;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content=content;
    }
    public String getGcp(){return this.gcp;}
    public void setGcp(String gcp){this.gcp=gcp;}
    public String getJp(){return this.jp;}
    public void setJp(String jp){this.jp=jp;}
}
