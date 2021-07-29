package com.example.demo.com.example.demo.service.impl;

import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.entity.UserDo;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void saveTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("u1");
        userDTO.setPassword("u1");
        userDTO.setEmail("mail@email.com");
        userDTO.setAge(1);
        userDTO.setPhone("000000000000");
        userDTO.setVersion(1L);

        int save = userService.save(userDTO);
        log.info("{}",save );
    }
    @Test
    public void updateTest(){
        Long id = 1419952290499776513L;
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password100");
        userDTO.setAge(1000);
        userDTO.setVersion(4L);
        int update = userService.update(id,userDTO);

        log.info("{}",update);
    }
    @Test
    public void deleteTest(){
        int delete = userService.delete(1419952290499776513L);
        log.info("{}",delete);
    }

}
