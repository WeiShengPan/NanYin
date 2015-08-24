package cn.nanyin.adminauth;

/**
 * Created by MYNP on 2015/7/27 0027.
 */
public enum AuthorityType {
    ADMIN_MANAGEMENT("后台用户管理",1),
    USER_MANAGEMENT("用户管理",2),
    NEWS_MANAGEMENT("新闻管理",3),
    COLLEGE_MANAGEMENT("社团管理",4),
    PRODUCT_MANAGEMENT("商城管理",5),
    ARTICLE_MANAGEMENT("图文乐谱",6),
    MEDIA_MANAGEMENT("音频管理",7),
    VIDEO_MANAGEMENT("视频管理",8),
    LINKS_MANAGEMENT("友情链接",9),
    COMMENTS_MANAGEMENT("留言管理",10),
    ;
    private String name;
    private int index;

    private AuthorityType(String name,int index){
        this.name=name;
        this.index=index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
