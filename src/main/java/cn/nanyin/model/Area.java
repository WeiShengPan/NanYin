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
    private List<User> users;//地区用户列表

    private int level;//地区等级

    private String name;//地区名

    private long upperId;//上一级地区ID

    private int Priority;//优先级

    private int state;//状态

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }
}
