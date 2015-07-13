package cn.nanyin.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class Booksort {
    private long id;
    private int level;      //分类级别
    private String name;    //分类名
    private long upperid;   //该分类所属大分类
    private int showorder;  //？
    private int state;      //？
    private Set<Book> books=new HashSet<Book>();

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @OneToMany (cascade= CascadeType.ALL,mappedBy = "booksort")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBook(Set<Book> books) {
        this.books = books;
    }
}
