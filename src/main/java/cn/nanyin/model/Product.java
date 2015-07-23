package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH })
    @JoinColumn(name = "sort_id")
    private ProductSort productSort;    //产品所属等级

    private String name;    //产品名称
    private String price;   //产品价格
    private String pic; //产品缩略图
    @Lob
    private String content; //说明
    private int recommendation; //推荐指数
    private Date addDate;   //上架时间
    private int hits;   //点击量
    private int state;  //状态

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductSort getProductSort() {
        return productSort;
    }

    public void setProductSort(ProductSort productSort) {
        this.productSort = productSort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(int recommendation) {
        this.recommendation = recommendation;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
