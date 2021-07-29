package com.example.demo.domain.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = -6648293161078900093L;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private String phone;
}
