package cn.nanyin.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gg on 2015/7/9.
 */
@Entity
public class MediaSort {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "mediaSort")
    private List<Media> mediaList;

    @ManyToOne
    private MediaSort upperMediaSort;
    @OneToMany(mappedBy = "upperMediaSort")
    private List<MediaSort> lowerMediaSortList;

    private int level;
    private String name;

    public void removeMedia(Media m)
    {
        for(int i=0;i<mediaList.size();i++)
        {
            if(mediaList.get(i).getId()==m.getId())
                mediaList.remove(i);
        }
    }

    public void removeMediaSort(MediaSort ms)
    {
        for(int i=0;i<lowerMediaSortList.size();i++)
        {
            if(lowerMediaSortList.get(i).getId()==ms.getId())
                lowerMediaSortList.remove(i);
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


    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public MediaSort getUpperMediaSort() {
        return upperMediaSort;
    }

    public void setUpperMediaSort(MediaSort upperMediaSort) {
        this.upperMediaSort = upperMediaSort;
    }

    public List<MediaSort> getLowerMediaSortList() {
        return lowerMediaSortList;
    }

    public void setLowerMediaSortList(List<MediaSort> lowerMediaSortList) {
        this.lowerMediaSortList = lowerMediaSortList;
    }
}
