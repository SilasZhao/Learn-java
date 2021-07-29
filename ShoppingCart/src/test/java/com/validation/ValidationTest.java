package com.validation;

import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Set;

public class ValidationTest {
    private Validator validator;

    private UserInfo userInfo;

    private Set<ConstraintViolation<UserInfo>> set;
    private Set<ConstraintViolation<UserInfoService>> otherSet;

    public ValidationTest() {
    }

    @Before
    public void init() {
        //初始化验证器
        validator = Validation
                .buildDefaultValidatorFactory().getValidator();
        //初始化待验证对象-用户信息
        userInfo = new UserInfo();
        //userInfo.setUserId("zsy");
        userInfo.setUserName("zsy");
        userInfo.setPassWord("1267  ");
        //userInfo.setEmail("yeyeye@gmail.com");
        userInfo.setAge(30);
        userInfo.setPhone("15889885555");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 1, 1);
        UserInfo friend = new UserInfo();
        //friend.setUserId("wang");
        friend.setUserName("xi");
        friend.setPassWord("123");
        friend.setPhone("15889885555");
        //friend.setEmail("aaa@cm.com");
        userInfo.setFriends(new ArrayList<>() {{
            add(friend);
        }});


    }

    @After
    public void print() {
        set.forEach(item -> {
            System.out.println(item.getMessage());
        });
    }

    @Test
    public void nullValidation() {
        //使用验证器对对象进行验证
        set = validator.validate(userInfo);
    }

    @Test
    public void groupValidation() {
        set = validator.validate(userInfo,
                UserInfo.RegisterGroup.class,
                UserInfo.LoginGroup.class);
    }

    @Test
    public void sequenceValidation() {
        set = validator.validate(userInfo,
                UserInfo.Group.class);
    }

    @Test
    public void paramValidation() throws NoSuchMethodException {
        //获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        //待验证对象
        UserInfoService service = new UserInfoService();
        //方法输入参数
        Method method = service.getClass().getMethod("setUserInfo", UserInfo.class);
        //对方法的输入参数进行校验
        Object[] paramObjects = new Object[]{new UserInfo()};
        //对方法的输入参数进行校验
        otherSet = executableValidator.validateParameters(service,
                method,
                paramObjects);
    }

    /*
    对方法返回值新型约束校验
     */
    @Test
    public void returnValueValidation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        //构造要校验的方法对象
        UserInfoService service = new UserInfoService();
        Method method = service.getClass().getMethod("getUserInfo");
        //调用方法得到返回值
        method.invoke(service);
        Object returnValue = method.invoke(service);

        //校验方法返回值是否符合约束
        otherSet = executableValidator.validateReturnValue(service,
                method,
                returnValue);
    }

    /*
    对构造函数输入参数进行校验
     */
    @Test
    public void constructorValidation() throws NoSuchMethodException {
        //获取验证执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        //获取构造函数
        Constructor<UserInfoService> constructor = UserInfoService.class.getConstructor(UserInfo.class);
        Object[] paramObjects = new Object[]{new UserInfo()};
        //校验构造函数
        otherSet = executableValidator.validateConstructorParameters(constructor, paramObjects);
    }


    @Test
    public void phoneValidation() {

        set = validator.validate(userInfo);
    }

}
