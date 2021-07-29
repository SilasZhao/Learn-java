package com.example.demo.domain.dto;
import com.example.demo.util.InsertValidationGroup;
import com.example.demo.util.UpdateValidationGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;



import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 2424668597436453629L;

    @NotBlank(message = "用户名不能为空",
    groups = {InsertValidationGroup.class})
    private String username;

    @NotBlank(message = "密码不能为空",
            groups = {InsertValidationGroup.class})
    @Length(min = 6,max = 18,
            message = "密码长度大于6小于18")
    private String password;

    @NotEmpty(message = "邮箱不能为空",
    groups = {InsertValidationGroup.class})
    @Email(message = "必须为有效邮箱")
    private String email;

    @NotNull(message = "年龄不能为空",
    groups = {InsertValidationGroup.class})
    @Max(value = 60, message = "年龄不能大于60岁！")
    @Min(value = 18, message = "年龄不能小于18岁！")
    private Integer age;

    @NotBlank(message = "phone不能为空",
            groups = {InsertValidationGroup.class})
    private String phone;

    @NotNull(message = "版本号不能为空！",
            groups = {UpdateValidationGroup.class})
    private Long version;
    /**
     * created time
     */
    private LocalDateTime created;

}
