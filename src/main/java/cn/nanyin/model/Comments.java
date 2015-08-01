package cn.nanyin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

/**
 * Created by 玮晟 on 2015/7/30.
 */
@Entity
public class Comments {

    @Id
    @GeneratedValue
    private long id;

    @Lob
    private String content;

    private String articleid;

    private String section;

    private Date addDate;

    private boolean varify;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }


    public boolean isVarify() {
        return varify;
    }

    public void setVarify(boolean varify) {
        this.varify = varify;
    }
}
