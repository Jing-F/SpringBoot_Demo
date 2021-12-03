package com.jfeng.springboot.mapper;

import com.jfeng.springboot.bean.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JFeng
 * @date 2021/11/30
 */

@Mapper
public interface TypeMapper {

    public List<Type> getBookType();
}
