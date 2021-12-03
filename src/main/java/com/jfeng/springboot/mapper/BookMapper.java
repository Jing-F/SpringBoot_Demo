package com.jfeng.springboot.mapper;

import com.jfeng.springboot.bean.Book;
import com.jfeng.springboot.bean.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JFeng
 * @date 2021/11/30
 */

@Mapper
public interface BookMapper {

    //根据id查询书籍类型
    public Type getTypeById(Integer id);

    //查询全部书籍信息
    public List<Book> getAllBook();

    //添加书籍信息
    public void addBook(@Param("bookName") String bookName,
                        @Param("bookType") Integer bookType,
                        @Param("bookIntro") String bookIntro,
                        @Param("bookPic") String bookPic);

    //根据书籍id删除书籍
    public int delBook(Integer bookId);

    //根据id查询
    public Book queryBookById(Integer bookId);

    //批量删除书籍
    public int deleteBatch(List<Integer> ids);

    //根据id修改书籍信息
    public int updateBookById(Book book);

    //条件搜索
    public List<Book> searchBook(String searchBook);
}
