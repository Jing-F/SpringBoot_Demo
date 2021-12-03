package com.jfeng.springboot.controller;

import com.jfeng.springboot.bean.User;
import com.jfeng.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author JFeng
 * @date 2021/11/23
 */

@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户名检查    不能注册相同用户名
     * @param username
     * @return
     */
    @RequestMapping("/checkName.do")
    @ResponseBody
    public String checkName(@RequestParam("username") String username) {
        User user = userService.getUserByName(username);
        if (user == null) {
            return "success";
        }
        return "false";
    }

    /**
     * 注册功能
     * @param username
     * @param password
     * @param email
     * @return
     */
    @RequestMapping("/addUser.do")
    @ResponseBody
    public int addUser(@RequestParam("userName") String username,
                          @RequestParam("password") String password,
                          @RequestParam("email") String email) {
        int result = userService.addUser(username, password, email);
        int flag = 0;
        if (result > 0) {
            flag = 1;
            return flag;
        }
        return flag;
    }

    /**
     * 密码找回功能
     * @param username
     * @param email
     * @return
     */
    @RequestMapping("/findPwd.do")
    @ResponseBody
    public int findPwd(@RequestParam("userName") String username,
                        @RequestParam("email") String email) {
        User user = userService.findPwd(username, email);
        Integer pwd = 0;
        if (user != null) {
            pwd = Integer.parseInt(user.getPassword());
        }
        return pwd;
    }

    /**
     * 登录功能
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/loginGo.do")
    @ResponseBody
    public int loginGo(@RequestParam("userName") String username,
                       @RequestParam("password") String password,
                       HttpSession session) {
        User user = userService.loginGo(username, password);
        session.setAttribute("loginUser", user);
        int flag = 0;
        if(user != null) {
            flag = 1;
        }
        return flag;
    }

    /**
     * 用户注销账号
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/delUser")
    @ResponseBody
    public int delUser(@RequestParam("id") Integer id, HttpSession session) {
        int result = userService.delUser(id);
        int flag = 0;
        if (result > 0) {
            session.invalidate();
            flag = 1;
        }
        return flag;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/exitLogin")
    @ResponseBody
    public int exitLogin(HttpSession session) {
        session.invalidate();
        return 1;
    }
}
