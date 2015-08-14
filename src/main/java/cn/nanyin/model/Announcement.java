package cn.nanyin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by 张一平 on 2015/8/4.
 */
@Entity                        /*******************************公告表**************************************/
public class Announcement {
    @Id
    @GeneratedValue
    private long id;           //id
    private String title;      //标题
    private String content;    //具体内容
    private Date addDate;      //添加日期

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public Date getAddDate(){
        return this.addDate;
    }
    public void setAddDate(Date date){
        this.addDate=date;
    }

}
