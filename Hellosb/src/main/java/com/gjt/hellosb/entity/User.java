package com.gjt.hellosb.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


public class User {
    private Integer id;
    private String userName;
    
    //@JsonIgnore可以在返回数据的时候不序列化一些属性,但导致输入时接收不到数据,可使用@JsonProperty
    @JsonProperty(access=Access.WRITE_ONLY)
    private String userPwd;
    private String email;
    private String mobilePhone;
    //使用@DateTimeFormat后,页面上用th:field得到的是格式化后的日期,JsonFormat似乎不行
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date birthday;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    
    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", userPwd=" + userPwd + ", email=" + email 
                + ", mobilePhone = "+mobilePhone+", birthday="
                + birthday + ", updatedAt=" + updatedAt + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
