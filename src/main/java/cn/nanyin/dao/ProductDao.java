package cn.nanyin.dao;

import cn.nanyin.model.Product;
import cn.nanyin.model.ProductSort;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gg on 2015/7/20.
 */
@Repository
@Transactional
public class ProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Product getProductById(long id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }

    public ProductSort getProductSortById(long id) {
        return (ProductSort) sessionFactory.getCurrentSession().get(ProductSort.class, id);
    }

    public List<Product> getProductList(Integer start, Integer max) {
        //from News as news order by addDate desc
        Query query = sessionFactory.getCurrentSession().createQuery("from Product as product order by addDate desc");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public List<ProductSort> getProductSortList(Integer start, Integer max) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ProductSort");
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.list();
    }

    public Serializable addProductSort(ProductSort productSort) {
        Serializable result = null;
        result = sessionFactory.getCurrentSession().save(productSort);
        return result;
    }

    public Serializable addProduct(Product product) {
        Serializable result = null;
        result = sessionFactory.getCurrentSession().save(product);
        return result;
    }

    public void deleteProduct(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

    public void deleteProductSort(ProductSort productSort) {
        sessionFactory.getCurrentSession().delete(productSort);
    }

    public void updateProduct(Product product)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    public void updateProductSort(ProductSort productSort)
    {
        sessionFactory.getCurrentSession().saveOrUpdate(productSort);
    }
}
