package com.jfeng.springboot.mapper;

import com.jfeng.springboot.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JFeng
 * @date 2021/11/23
 */

@Mapper
public interface UserMapper {

    //根据用户名查询该用户是否存在
    public User getUserByName(String username);

    //添加用户
    public int addUser(@Param("userName") String username, @Param("password") String password, @Param("email") String email);

    //根据用户名和邮箱查询密码
    public User findPwd(@Param("userName") String username, @Param("email") String email);

    //根据用户名和密码查询该用户是否存在
    public User loginGo(@Param("userName") String username, @Param("password") String password);

    //根据id删除用户
    public int delUser(Integer id);

}
