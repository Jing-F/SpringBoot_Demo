package com.jfeng.springboot.bean;

import lombok.Data;

/**
 * @author JFeng
 * @date 2021/11/23
 */
@Data
public class User {

    //用户id
    private Integer id;

    //用户名
    private String userName;

    //用户密码
    private String password;

    //用户邮箱
    private String email;
}
