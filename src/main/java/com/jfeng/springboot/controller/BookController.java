package com.jfeng.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfeng.springboot.bean.Book;
import com.jfeng.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JFeng
 * @date 2021/11/30
 */

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 获取全部书籍信息
     * @param pn
     * @return
     */
    @RequestMapping("/getAllBook")
    @ResponseBody
    public PageInfo<Book> getAllBook(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<Book> books = bookService.getAllBook();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括我们查询出来的数据，传入连续显示的页数
        return new PageInfo(books, 5);
    }

    /**
     * 添加书籍功能
     * @param bookPic
     * @param bookName
     * @param bookType
     * @param bookIntro
     * @param model
     * @return
     */
    @RequestMapping("/addBook")
    @ResponseBody
    public String addBook(@RequestPart("bookPic") MultipartFile bookPic,
                          @RequestParam("bookName") String bookName,
                          @RequestParam("bookType") Integer bookType,
                          @RequestParam("bookIntro") String bookIntro,
                          Model model) {
        Book book = new Book();
        try {
            //根据创建时间对文件进行重命名
            String fileName = System.currentTimeMillis() + bookPic.getOriginalFilename();
            //上传文件存储的位置
            String destFileName = "D://BookPic/images/" + File.separator + fileName;
            //防止改文件夹不存在，创建一个新文件夹
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            //将文件存储到该位置
            bookPic.transferTo(destFile);

            //传递文件
            model.addAttribute("fileName", fileName);
            //将文件名存储到数据库中，以便查询调用
            book.setBookPic(fileName);
            String pic = book.getBookPic();
            bookService.addBook(bookName, bookType, bookIntro, pic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }
        return "success";
    }

    /**
     * 删除书籍功能
     * @param bookId
     * @return
     */
    @RequestMapping("/delBook")
    @ResponseBody
    public int delBook(@RequestParam(value = "bookId") Integer bookId) {
        int result = bookService.delBook(bookId);
        int flag = 0;
        if (result > 0) {
            flag = 1;
        }
        return flag;
    }

    /**
     * 根据id查询书籍信息
     * @param bookId
     * @return
     */
    @RequestMapping("/queryBookById")
    @ResponseBody
    public Book updateBook(@RequestParam(value = "bookId") Integer bookId) {
        return bookService.queryBookById(bookId);
    }

    /**
     * 批量删除书籍功能
     * @param ids
     * @return
     */
    @RequestMapping("/delAllBook")
    @ResponseBody
    public int delAllBook(@RequestParam(value = "ids") String ids) {
        int result = 0;
        //批量删除
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            result = bookService.deleteBatch(del_ids);
        } else{
            Integer id = Integer.parseInt(ids);
            result = bookService.delBook(id);
        }
        int flag = 0;
        if (result > 0) {
            flag = 1;
        }
        return flag;
    }

    /**
     * 修改书籍功能
     * @param book
     * @return
     */
    @RequestMapping("/updateBookById/{bookId}")
    @ResponseBody
    public int updateBookById(Book book) {
        int result = bookService.updateBookById(book);
        int flag = 0;
        if (result > 0) {
            flag = 1;
        }
        return flag;
    }

    /**
     * 根据输入信息查询书籍功能
     * @param pn
     * @param request
     * @return
     */
    @RequestMapping("/searchBook")
    @ResponseBody
    public PageInfo<Book> searchBook(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        String searchBook = request.getParameter("searchBook");
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<Book> books = bookService.searchBook(searchBook);
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括我们查询出来的数据，传入连续显示的页数
        return new PageInfo(books, 5);
    }

}
