package com.validation;

import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import lombok.Data;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Set;

public class ValidationTest {
    private Validator validator;

    private UserInfo userInfo;

    private Set<ConstraintViolation<UserInfo>> set;

    public ValidationTest() {
    }

    @Before
    public void init(){
        //初始化验证器
        validator = Validation
                .buildDefaultValidatorFactory().getValidator();
        //初始化待验证对象-用户信息
        userInfo = new UserInfo();
        userInfo.setUserId("zsy");
        userInfo.setUserName("zsy");
        userInfo.setPassWord("1267  ");
        userInfo.setEmail("yeyeye@gmail.com");
        userInfo.setAge(30);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,1,1);
        userInfo.setFriends(new ArrayList<>(){{add(new UserInfo());}});


    }
    @After
    public void print(){
        set.forEach(item ->{
            System.out.println(item.getMessage());
        });
    }
    @Test
    public void nullValidation(){
        //使用验证器对对象进行验证
        set = validator.validate(userInfo);
    }


}
