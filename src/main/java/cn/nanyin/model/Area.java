package cn.nanyin.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2015/7/9.
 */
@Entity
public class Area {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "area")
    private List<User> users;

    private int level;

    private String name;

    private long upperId;

    private String showorder;

    private int state;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public String getShoworder() {
        return showorder;
    }

    public void setShoworder(String showorder) {
        this.showorder = showorder;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
