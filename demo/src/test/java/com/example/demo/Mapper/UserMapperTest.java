package com.example.demo.Mapper;

import com.example.demo.domain.entity.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void find(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("username","username1");
        List<UserDo> userDoList =  userMapper.selectByMap(param);
        log.info("{}", userDoList);
    }

}
