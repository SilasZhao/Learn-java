package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class UserDo implements Serializable {
    //option+enter
    private static final long serialVersionUID = 6881791051987658920L;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private String phone;
    /***系统主信息***/
    /**
     * 数据库主键
     */
    @TableId(type = IdType.ASSIGN_ID )
    private Long id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime created;
    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String creator;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modified;
    /**
     * 最后修改者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String operator;
    /**
     * 逻辑删除字段 0：正常 1：逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer status;
    /**
     * 版本号
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;
}



