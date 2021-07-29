package com.validation;

import java.util.Calendar;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.swing.*;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.List;

/**
 * 待验证对象
 */
public class UserInfo {
    //登陆场景
    public interface LoginGroup{}
    //注册场景
    public interface RegisterGroup{}

    //组排序场景
    @GroupSequence({
            LoginGroup.class,
            RegisterGroup.class,
            Default.class
    })
    public interface Group{}

    @NotNull(message = "ID cannot be empty", groups= LoginGroup.class)
    private String userId;
    @NotEmpty(message = "name cannot be empty")
    private String userName;
    //会自动去掉空格
    @NotBlank(message = "password cannot be blank")
    @Length(min = 6, max = 20, message  = "length of password should between 6-20 digits")
    private String passWord;
    @Email(message= "please enter a valid email address")
    @NotNull(message = "email cannot be empty", groups = RegisterGroup.class)
    private String email;
    @Min(value = 18, message = "age cannot be smaller than 18")
    @Max(value = 60, message = "you cannot be older than 60")
    private Integer age;

    private Data birthday;
    @Size(min = 1, message = "cannot have less than 1 friend")
    private List<@Valid UserInfo> friends;
    @Phone(message = "手机号不是158开头")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Data getBirthday() {
        return birthday;
    }

    public void setBirthday(Data birthday) {
        this.birthday = birthday;
    }

    public List<UserInfo> getFriends() {
        return friends;
    }

    public void setFriends(List<UserInfo> friends) {
        this.friends = friends;
    }
}
