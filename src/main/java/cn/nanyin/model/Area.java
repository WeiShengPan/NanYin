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

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "area")
    private List<User> users;//地区用户列表

    private int level;//地区等级

    private String name;//地区名

    @ManyToOne
    private Area upperArea;//上一级地区ID
    @OneToMany(mappedBy = "upperArea")
    private List<Area> lowerAreaList;

    private int priority;//优先级

    private int state;//状态

    public void removeUser(User u)
    {
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).getId()==u.getId())
                users.remove(i);
        }
    }

    public void removeArea(Area a)
    {
        for(int i=0;i<lowerAreaList.size();i++)
        {
            if(lowerAreaList.get(i).getId()==a.getId())
                lowerAreaList.remove(i);
        }
    }

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

    public Area getUpperArea() {
        return upperArea;
    }

    public void setUpperArea(Area upperArea) {
        this.upperArea = upperArea;
    }

    public List<Area> getLowerAreaList() {
        return lowerAreaList;
    }

    public void setLowerAreaList(List<Area> lowerAreaList) {
        this.lowerAreaList = lowerAreaList;
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
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
