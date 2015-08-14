package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 张一平 on 2015/8/12.
 */
@Entity               /****************视频评论***********************************/
public class VideoComments {
    @Id
    @GeneratedValue
    private  long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "userId")
    private User1 user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "videoId")
    private Video video;

    private String content;
    private Date addDate;

    public  long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id=id;
    }
    public User1 getUser(){
        return this.user;
    }
    public void setUser(User1 user){
        this.user=user;
    }

    public Video getVideo(){
        return this.video;
    }
    public void setVideo(Video video){
        this.video=video;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content=content;
    }
    public Date getAddDate(){
        return this.addDate;
    }
    public void setAddDate(Date date){
        this.addDate=date;
    }
}
