package cn.nanyin.dao;

import cn.nanyin.model.Book;
import cn.nanyin.model.BookSort;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
@Repository
@Transactional
public class ArticleDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Book getBookById(long id)
    {
        return (Book)sessionFactory.getCurrentSession().get(Book.class,id);
    }

    public BookSort getBookSortById(long id)
    {
        return (BookSort)sessionFactory.getCurrentSession().get(BookSort.class,id);
    }


    public List<Book> getBookList(Integer start, Integer max) {
        //from News as news order by addDate desc
        Query query = sessionFactory.getCurrentSession().createQuery("from Book order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<Book> getBookList(String typeName, Integer start, Integer max,int flag) {
        //from News as news order by addDate desc
        if(flag==1){
            Query query = sessionFactory.getCurrentSession().createQuery("from Book where bookSort.upperBookSort.name='"+typeName+"' order by addDate desc");
            query.setFirstResult(start);
            query.setMaxResults(max);
            return query.list();
        }else{
            Query query = sessionFactory.getCurrentSession().createQuery("from Book where bookSort.name='"+typeName+"' order by addDate desc");
            query.setFirstResult(start);
            query.setMaxResults(max);
            return query.list();
        }
    }

    public List<Book> findBooks(String typeName,String text) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Book where bookSort.upperBookSort.name='"+typeName+"'and title like '%"+text+"%'");
        return query.list();
    }

    public List<Book> getGalleryImages(Integer start, Integer max){
        String typeName="南音图谱";
        Query query = sessionFactory.getCurrentSession().createQuery("from Book where bookSort.upperBookSort.name='" + typeName + "' and  file !='' order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }


    public int getNum(String typeName,int flag){
        int num=0;
        if(flag==1){
            Query query = sessionFactory.getCurrentSession().createQuery("from Book where bookSort.upperBookSort.name='" + typeName + "'");
            num=query.list().size();
        }else{
            Query query = sessionFactory.getCurrentSession().createQuery("from Book where bookSort.name='" + typeName + "'");
            num=query.list().size();
        }
        return num;
    }


    public List<BookSort> getNewsSortList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from BookSort");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addBookSort(BookSort bookSort) {
        Serializable result = sessionFactory.getCurrentSession().save(bookSort);
        return result;
    }

    public Serializable addBook(Book book) {
        Serializable result = sessionFactory.getCurrentSession().save(book);
        return result;
    }

    public void deleteBook(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }

    public void deleteBookSort(BookSort bookSort) {
        sessionFactory.getCurrentSession().delete(bookSort);
    }

    public void updateBook(Book book)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
    }

    public void updateBookSort(BookSort bookSort)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(bookSort);
    }
}
