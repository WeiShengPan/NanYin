package cn.nanyin.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/20.
 */
@Entity
public class TeachingSort {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "teachingSort")
    private List<Teaching> teachings;

    @ManyToOne
    private TeachingSort upperTeachingSort;
    @OneToMany(mappedBy = "upperTeachingSort")
    private List<TeachingSort> lowerTeachingSortList;

    private int level;
    private String name;

    public void removeTeaching(Teaching n)
    {
        for(int i=0;i<teachings.size();i++)
        {
            if(teachings.get(i).getId()==n.getId())
                teachings.remove(i);
        }
    }

    public void removeTeachingSort(TeachingSort ns)
    {
        for(int i=0;i<lowerTeachingSortList.size();i++)
        {
            if(lowerTeachingSortList.get(i).getId()==ns.getId())
                lowerTeachingSortList.remove(i);
        }
    }

    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getLevel(){
        return this.level;
    }
    public void setLevel(int level){
        this.level=level;
    }
    public List<Teaching> getTeachings(){
        return this.teachings;
    }
    public void setTeachings(List<Teaching> teachings){
        this.teachings=teachings;
    }
    public TeachingSort getUpperTeachingSort(){
        return this.upperTeachingSort;
    }
    public void setUpperTeachingSort(TeachingSort upperTeachingSort){
        this.upperTeachingSort=upperTeachingSort;
    }
    public List<TeachingSort> getLowerTeachingSortList(){
        return this.lowerTeachingSortList;
    }
    public void setLowerTeachingSortList(List<TeachingSort> lowerTeachingSortList){
        this.lowerTeachingSortList=lowerTeachingSortList;
    }

}
