package cn.nanyin.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/21.
 */
@Entity
public class DirectorySort {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "directorySort")
    private List<Directory> directories;

    @ManyToOne
    private DirectorySort upperDirectorySort;
    @OneToMany(mappedBy = "upperDirectorySort")
    private List<DirectorySort> lowerDirectorySortList;

    private int level;
    private String name;


    public void removeDirectory(Directory n)
    {
        for(int i=0;i<directories.size();i++)
        {
            if(directories.get(i).getId()==n.getId())
                directories.remove(i);
        }
    }

    public void removeDirectorySort(DirectorySort ns)
    {
        for(int i=0;i<lowerDirectorySortList.size();i++)
        {
            if(lowerDirectorySortList.get(i).getId()==ns.getId())
                lowerDirectorySortList.remove(i);
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

    public  List<Directory> getDirectories(){
        return this.directories;
    }
    public void setDirectories( List<Directory> directories){
        this.directories=directories;
    }
    public DirectorySort getUpperDirectorySort(){
        return this.upperDirectorySort;
    }
    public  void setUpperDirectorySort(DirectorySort upperDirectorySort){
        this.upperDirectorySort=upperDirectorySort;
    }
    public List<DirectorySort> getLowerDirectorySortList(){
        return this.lowerDirectorySortList;
    }
    public void setLowerDirectorySortList(List<DirectorySort> lowerDirectorySortList){
        this.lowerDirectorySortList=lowerDirectorySortList;
    }

}
