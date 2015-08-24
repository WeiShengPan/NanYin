package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 张一平 on 2015/8/21.
 */
@Entity
public class Directory {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH })
    @JoinColumn(name = "sort_id")
    private DirectorySort directorySort;
    private String title;
    private String author;
    private String content;
    private String file;
    private int hits;
    private Date addDate;
    private String source;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public DirectorySort getDirectorySort(){
        return this.directorySort;
    }
    public void setDirectorySort(DirectorySort directorySort){
        this.directorySort=directorySort;
    }
}
