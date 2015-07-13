package cn.nanyin.model;

import javax.persistence.*;
import java.util.HashSet;
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
    private long upperId;   //���������
    private int priority;  //?
    private String pic;     //����ͼ��ַ
    private String intro;   //?
    private int state;      //?

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "productSort")
    private Set<Product> products=new HashSet<Product>();

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

    public long getUpperId() {
        return upperId;
    }

    public void setUpperId(long upperId) {
        this.upperId = upperId;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}