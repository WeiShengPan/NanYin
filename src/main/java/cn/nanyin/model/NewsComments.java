package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 张一平 on 2015/8/5.
 */
@Entity                                    /********************新闻的评论***************************/
public class NewsComments {
    @Id
    @GeneratedValue
    private  long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "userId")
    private User1 user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "newsId")
    private News news;


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

    public News getNews(){
        return this.news;
    }
    public void setNews(News news){
        this.news=news;
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
