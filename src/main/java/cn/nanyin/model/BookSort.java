package cn.nanyin.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class BookSort {
    @Id
    @GeneratedValue
    private long id;
    private int level;      //���༶��
    private String name;    //������

    @ManyToOne
    private BookSort upperBookSort;
    @OneToMany(mappedBy = "upperBookSort")
    private List<BookSort> lowerBookSortList;
    @OneToMany (cascade= CascadeType.ALL,mappedBy = "bookSort")
    private List<Book> books;

    public void removeBook(Book b)
    {
        for(int i=0;i<books.size();i++)
        {
            if(books.get(i).getId()==b.getId())
                books.remove(i);
        }
    }

    public void removeBookSort(BookSort bs)
    {
        for(int i=0;i<lowerBookSortList.size();i++)
        {
            if(lowerBookSortList.get(i).getId()==bs.getId())
                lowerBookSortList.remove(i);
        }
    }

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

    public BookSort getUpperBookSort() {
        return upperBookSort;
    }

    public void setUpperBookSort(BookSort upperBookSort) {
        this.upperBookSort = upperBookSort;
    }

    public List<BookSort> getLowerBookSortList() {
        return lowerBookSortList;
    }

    public void setLowerBookSortList(List<BookSort> lowerBookSortList) {
        this.lowerBookSortList = lowerBookSortList;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
