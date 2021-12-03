package com.jfeng.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JFeng
 * @date 2021/11/23
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/login.do")
    public String login() {
        return "login/login";
    }

    @RequestMapping("/findPassword.do")
    public String findPwd() {
        return "login/findPassword";
    }

    @RequestMapping("/register.do")
    public String regis() {
        return "login/register";
    }
}
