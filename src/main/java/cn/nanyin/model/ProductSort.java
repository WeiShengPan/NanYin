package cn.nanyin.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class ProductSort {
    @Id
    @GeneratedValue
    private long id;
    private int level;       //���༶��
    private String name;    //������

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "productSort")
    private List<Product> products;

    @ManyToOne
    private ProductSort upperProductSort;
    @OneToMany(mappedBy = "upperProductSort")
    private List<ProductSort> lowerProductSortList;

    private int priority;  //?
    private String pic;     //����ͼ��ַ
    private String intro;   //?
    private int state;      //?

    public void removeProduct(Product p) {
        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).getId()==p.getId())
                products.remove(i);
        }
    }

    public void removeProductSort(ProductSort ps) {
        for(int i=0;i<lowerProductSortList.size();i++)
        {
            if(lowerProductSortList.get(i).getId()==ps.getId())
                lowerProductSortList.remove(i);
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ProductSort getUpperProductSort() {
        return upperProductSort;
    }

    public void setUpperProductSort(ProductSort upperProductSort) {
        this.upperProductSort = upperProductSort;
    }

    public List<ProductSort> getLowerProductSortList() {
        return lowerProductSortList;
    }

    public void setLowerProductSortList(List<ProductSort> lowerProductSortList) {
        this.lowerProductSortList = lowerProductSortList;
    }
}
