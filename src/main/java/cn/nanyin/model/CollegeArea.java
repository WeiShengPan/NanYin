package cn.nanyin.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gg on 2015/7/24.
 */
@Entity
public class CollegeArea {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "collegeArea")
    private List<College> colleges;//地区社团列表

    private int level;//地区等级

    private String name;//地区名

    @ManyToOne
    private CollegeArea upperCollegeArea;//上一级地区ID
    @OneToMany(mappedBy = "upperCollegeArea")
    private List<CollegeArea> lowerCollegeAreaList;

    public void removeCollege(College college) {
        for(int i=0;i<colleges.size();i++)
        {
            if(colleges.get(i).getId()==college.getId())
                colleges.remove(i);
        }
    }

    public void removeCollegeArea(CollegeArea a) {
        for(int i=0;i<lowerCollegeAreaList.size();i++)
        {
            if(lowerCollegeAreaList.get(i).getId()==a.getId())
                lowerCollegeAreaList.remove(i);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<College> getColleges() {
        return colleges;
    }

    public void setColleges(List<College> colleges) {
        this.colleges = colleges;
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

    public CollegeArea getUpperCollegeArea() {
        return upperCollegeArea;
    }

    public void setUpperCollegeArea(CollegeArea upperCollegeArea) {
        this.upperCollegeArea = upperCollegeArea;
    }

    public List<CollegeArea> getLowerCollegeAreaList() {
        return lowerCollegeAreaList;
    }

    public void setLowerCollegeAreaList(List<CollegeArea> lowerCollegeAreaList) {
        this.lowerCollegeAreaList = lowerCollegeAreaList;
    }
}
