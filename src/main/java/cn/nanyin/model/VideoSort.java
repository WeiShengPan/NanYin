package cn.nanyin.model;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/11.
 */
@Entity                          /****************视频类别***********************************/
public class VideoSort {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "videoSort")
    private List<Video> videos;

    @ManyToOne
    private VideoSort upperVideoSort;
    @OneToMany(mappedBy = "upperVideoSort")
    private List<VideoSort> lowerVideoSortList;

    private int level;
    private String name;

    public void removeVideo(Video n)
    {
        for(int i=0;i<videos.size();i++)
        {
            if(videos.get(i).getId()==n.getId())
                videos.remove(i);
        }
    }

    public void removeVideoSort(VideoSort ns)
    {
        for(int i=0;i<lowerVideoSortList.size();i++)
        {
            if(lowerVideoSortList.get(i).getId()==ns.getId())
                lowerVideoSortList.remove(i);
        }
    }

    private long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id=id;
    }
    public List<Video> getVideos(){
        return this.videos;
    }
    public void setVideos(List<Video> videos){
        this.videos=videos;
    }
    public VideoSort getUpperVideoSort(){
        return this.upperVideoSort;
    }
    public void setUpperVideoSort( VideoSort upperVideoSort){
        this.upperVideoSort=upperVideoSort;
    }
    public List<VideoSort> getLowerVideoSortList(){
        return this.lowerVideoSortList;
    }
    public void setLowerVideoSortList(List<VideoSort> lowerVideoSortList){
        this.lowerVideoSortList=lowerVideoSortList;
    }
    public int getLevel(){
        return this.level;
    }
    public void setLevel(int level){
        this.level=level;
    }
    public String getName(){
       return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

}
