package com.jfeng.springboot.service.impl;

import com.jfeng.springboot.bean.Book;
import com.jfeng.springboot.mapper.BookMapper;
import com.jfeng.springboot.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JFeng
 * @date 2021/11/30
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;


    @Override
    public List<Book> getAllBook() {

        return bookMapper.getAllBook();
    }

    public void addBook(@Param("bookName") String bookName, @Param("bookType") Integer bookType, @Param("bookIntro") String bookIntro, @Param("bookPic") String bookPic) {
        bookMapper.addBook(bookName, bookType, bookIntro, bookPic);
    }

    @Override
    public int delBook(Integer bookId) {
        return bookMapper.delBook(bookId);
    }

    @Override
    public Book queryBookById(Integer bookId) {
        return bookMapper.queryBookById(bookId);
    }

    @Override
    public int deleteBatch(List<Integer> ids) {
        return bookMapper.deleteBatch(ids);
    }

    @Override
    public int updateBookById(Book book) {
        return bookMapper.updateBookById(book);
    }

    @Override
    public List<Book> searchBook(String searchBook) {
        return bookMapper.searchBook(searchBook);
    }
}
