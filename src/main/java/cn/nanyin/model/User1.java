package cn.nanyin.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 张一平 on 2015/8/9.
 */
@Entity                                  /********************用户***************************/
public class User1 {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private  String userName;   //用户名
    @Column(nullable=false)
    private String password;   //密码
    @Column(nullable=true)
    private String name;        //真实姓名
    @Column(nullable=false)
    private boolean gender;      //性别
    @Column(nullable=true)
    private String tel;            //电话
    @Column(nullable=true)
    private String email;          //邮箱
    @Column(nullable=true)
    private String question;     //密保问题
    @Column(nullable=true)
    private String answer;     //密保答案
    @Column(nullable=false)
    private boolean level;       //用户的级别
    @Column(nullable=false)
    private  boolean  state;      //0代表未激活用户，1代表激活用户，
    @Column(nullable=false)
    private Date registerDate;       //注册日期

    public long getId(){
        return this.id;
    }
    public  void setId(long id){
        this.id=id;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public  String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public  String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public  boolean isGender(){
        return this.gender;
    }
    public void setGender( boolean gender){
        this.gender=gender;
    }
    public String getTel(){
        return this.tel;
    }
    public void  setTel(String tel){
        this.tel=tel;
    }
    public String getEmail(){
        return  this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getQuestion(){
        return  this.question;
    }
    public void setQuestion(String question){
        this.question=question;
    }
    public  String getAnswer(){
        return this.answer;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }
    public boolean getLevel(){
        return this.level;
    }
    public  void setLevel(boolean level){
        this.level=level;
    }
    public boolean getState(){
        return  this.state;
    }
    public void setState(boolean state){
        this.state=state;
    }
    public  Date getRegisterDate(){
        return this.registerDate;
    }
    public  void setRegisterDate(Date registerDate){
        this.registerDate=registerDate;
    }

}
