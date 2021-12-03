package com.jfeng.springboot.service.impl;

import com.jfeng.springboot.bean.Type;
import com.jfeng.springboot.mapper.TypeMapper;
import com.jfeng.springboot.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JFeng
 * @date 2021/11/30
 */

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    public List<Type> getBookType() {
        return typeMapper.getBookType();
    }
}
