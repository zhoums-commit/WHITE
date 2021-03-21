package com.evan.wj.controller;

import com.evan.wj.pojo.Book;
import com.evan.wj.service.BookService;
import com.evan.wj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {
    private final static Logger logger = LoggerFactory.getLogger(LibraryController.class);
    /**
     * 注入service
     */
    @Autowired
    BookService bookService;

    /**
     * 调service查询书籍
     */
    @CrossOrigin
    @GetMapping("/api/books")
    public List<Book> list() throws Exception{
       return bookService.list();
    }
    /**
     * 修改
     */
    @CrossOrigin
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
         bookService.addOrUpdate(book);
         return book;
    }
    /**
     * 删除
     */
    @CrossOrigin
    @PostMapping("/api/delete")
    public void deleteById(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }
    /**
     * 通过类别查书籍
     */
    @CrossOrigin
    @GetMapping("/api/categories/{cid}/books")
     public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception{

            if (0!=cid){
                  return bookService.listByCategory(cid);
            }else {
                return list();
            }
     }
    /**
     * 通过关键字查询
     */

    @CrossOrigin
    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keywords){
        if("".equals(keywords)){
            return bookService.list();
        }else {
          return bookService.search(keywords);
        }
    }

    /**
     * 上传图片
     *限制png,jpg,500k
     */
    @CrossOrigin
    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file)throws Exception{
        String folder = "E:/workspace/img";
        File imgFolder= new File(folder);
        File f = new File(imgFolder, StringUtils.getRandomString(6) +
                file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4));
        if(!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imgURl ="http://localhost:8443/api/file/" + f.getName();
            return imgURl;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

}
