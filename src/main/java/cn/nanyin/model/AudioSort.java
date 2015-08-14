package cn.nanyin.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/12.
 */
@Entity                                            /********************音频的类别***************************/
public class AudioSort {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade= CascadeType.ALL,mappedBy = "audioSort")
    private List<Audio> audios;

    @ManyToOne
    private AudioSort upperAudioSort;
    @OneToMany(mappedBy = "upperAudioSort")
    private List<AudioSort> lowerAudioSortList;

    private int level;
    private String name;

    public void removeAudio(Audio n)
    {
        for(int i=0;i<audios.size();i++)
        {
            if(audios.get(i).getId()==n.getId())
                audios.remove(i);
        }
    }

    public void removeAudioSort(AudioSort ns)
    {
        for(int i=0;i<lowerAudioSortList.size();i++)
        {
            if(lowerAudioSortList.get(i).getId()==ns.getId())
                lowerAudioSortList.remove(i);
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
    public  List<Audio> getAudios(){
        return this.audios;
    }
    public void setAudios( List<Audio> audios){
        this.audios=audios;
    }
    public AudioSort getUpperAudioSort(){
        return this.upperAudioSort;
    }
    public void setUpperAudioSort(AudioSort upperAudioSort){
        this.upperAudioSort=upperAudioSort;
    }
    public  List<AudioSort> getLowerAudioSortList(){
        return this.lowerAudioSortList;
    }
    public void setLowerAudioSortList( List<AudioSort> lowerAudioSortList){
        this.lowerAudioSortList=lowerAudioSortList;
    }


}
