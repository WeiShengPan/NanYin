package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 张一平 on 2015/8/10.
 */
@Entity                                       /********************会员中心中的用户信息***************************/
public class MyMessage {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User1 user;

    private String title;
    private String content;
    private Date date;
    private boolean state; //标记是否阅读过

    public long getId(){
        return this.id;
    }
    public  void setId(long id){
        this.id=id;
    }
    public User1 getUser(){
        return this.user;
    }
    public void setUser(User1 user){
        this.user=user;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content=content;
    }
    public Date getDate(){
        return this.date;
    }
    public void setDate( Date date){
        this.date=date;
    }
    public boolean getState(){
        return this.state;
    }
    public void setState(boolean state){
        this.state=state;
    }

}
