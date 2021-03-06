package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/10.
 */
@Entity
public class College {
    @Id
    @GeneratedValue
    private long id;

    private String mainMembers;//主要成员

    private String name;//社团名

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "collegeArea_id")
    private CollegeArea collegeArea;//用户所属地区

    private String leader;//现任社团领导

    private String exLeader;//历届领导

    private String email;//邮箱

    private String address;//联系地址

    private String contact;//联系人

    private String telephone;//联系电话

    private String formDate;//成立时间

    private String memberNum;//会员数

    private String activityDate;//活动时间

    @Lob
    private String introduction;//简介

    private boolean vip;//是否为vip社团

    private int state;//状态
    private String file;//视频文件

    @Lob
    private String organizationStructure;//组织结构
    @Lob
    private String activeImages; //活动剪影

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrganizationStructure(){
        return this.organizationStructure;
    }
    public void setOrganizationStructure(String organizationStructure){
        this.organizationStructure=organizationStructure;
    }
    public String getActiveImages() {
        return this.activeImages;
    }
    public void setActiveImages(String activeImages){
        this.activeImages=activeImages;
    }

    public String getMainMembers() {
        return mainMembers;
    }

    public void setMainMembers(String mainMembers) {
        this.mainMembers = mainMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollegeArea getCollegeArea() {
        return collegeArea;
    }

    public void setCollegeArea(CollegeArea collegeArea) {
        this.collegeArea = collegeArea;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getExLeader() {
        return exLeader;
    }

    public void setExLeader(String exLeader) {
        this.exLeader = exLeader;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }
}
