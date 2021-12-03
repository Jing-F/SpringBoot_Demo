package com.jfeng.springboot.controller;

import com.jfeng.springboot.bean.Type;
import com.jfeng.springboot.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author JFeng
 * @date 2021/11/30
 */

@Controller
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/getBookType")
    @ResponseBody
    public List<Type> getBookType() {
        return typeService.getBookType();
    }
}
