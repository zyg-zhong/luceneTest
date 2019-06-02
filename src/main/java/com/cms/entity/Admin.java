package com.cms.entity;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Table;

/**
 * 用户实体.
 */
@Data
@Table(name = "c_admin")
public class Admin {
    /**
     * adminID.
     */
    private int adminID;
    /**
     * username.
     */
    private String username;
    /**
     * password.
     */
    private String password;
    /**
     * level.
     */
    private int level;
    /**
     * logindate.
     */
    @JSONField(format = "yyyy-MM-dd")//ajax输出
    @DateTimeFormat(pattern = "yyyy-MM-dd")//输入
    private Date logindate;


}
