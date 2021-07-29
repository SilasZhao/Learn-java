package com.example.demo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
public class UserQueryDTO implements Serializable {
    private static final long serialVersionUID = 612377004648048073L;

    @NotEmpty(message ="名字不能为空")
    private String username;
}
