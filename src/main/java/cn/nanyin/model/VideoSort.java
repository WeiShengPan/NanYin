package cn.nanyin.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2015/7/13.
 */
@Entity
public class VideoSort {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "videoSort")
    private List<Video> videos;

    @ManyToOne
    private VideoSort upperVideoSort;
    @OneToMany(mappedBy = "upperVideoSort")
    private List<VideoSort> lowerVideoSortList;

    private String name;

    private int level;

    public void removeVideo(Video v)
    {
        for(int i=0;i<videos.size();i++)
        {
            if(videos.get(i).getId()==v.getId())
                videos.remove(i);
        }
    }

    public void removeVideoSort(VideoSort vs)
    {
        for(int i=0;i<lowerVideoSortList.size();i++)
        {
            if(lowerVideoSortList.get(i).getId()==vs.getId())
                lowerVideoSortList.remove(i);
        }
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }



    public VideoSort getUpperVideoSort() {
        return upperVideoSort;
    }

    public void setUpperVideoSort(VideoSort upperVideoSort) {
        this.upperVideoSort = upperVideoSort;
    }

    public List<VideoSort> getLowerVideoSortList() {
        return lowerVideoSortList;
    }

    public void setLowerVideoSortList(List<VideoSort> lowerVideoSortList) {
        this.lowerVideoSortList = lowerVideoSortList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
