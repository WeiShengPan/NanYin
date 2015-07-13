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
    private ProductSorts productSorts;   //����
    private String name;        //��Ʒ��
    private String price;       //�۸�
    private String pic;         //����ͼ��ַ
    private String content;     //����
    private int recommendation;        //�Ƽ�ָ��
    private Date addDate;       //�ϼ�ʱ��
    private int hits;           //�����
    private int state;          //?

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductSorts getProductSorts() {
        return productSorts;
    }

    public void setProductSorts(ProductSorts productSorts) {
        this.productSorts = productSorts;
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
