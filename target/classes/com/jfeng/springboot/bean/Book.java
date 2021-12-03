package com.jfeng.springboot.bean;

import lombok.Data;

/**
 * @author JFeng
 * @date 2021/11/28
 */

@Data
public class Book {

    private Integer bookId;
    private String bookName;
    private String bookIntro;
    private String bookPic;
    private Integer bookType;

    private Type typeBook;
}
