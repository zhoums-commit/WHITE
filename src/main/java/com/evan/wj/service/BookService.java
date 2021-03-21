package com.evan.wj.service;

import com.evan.wj.dao.BookDAO;
import com.evan.wj.pojo.Book;
import com.evan.wj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    /**
     * 书籍增删改查
     */
    @Autowired
    BookDAO bookDAO;
    @Autowired
    CategoryService categoryService;

    /**
     * 全查书籍
     * @return
     */
    public List<Book> list(){
    Sort sort =new Sort(Sort.Direction.DESC,"id");
        List<Book> bookList = bookDAO.findAll(sort);
        return bookList;
    }

    /**
     * 添加与修改书籍
     */
    public void addOrUpdate(Book book){
        bookDAO.save(book);
    }
    /**
     * 删除书籍
     */
    public void deleteById(int id){

        bookDAO.deleteById(id);
    }

    /**
     * 通过书籍类别查询书籍
     */

    public List<Book> listByCategory(int cid){
        Category category = categoryService.get(cid);
        List<Book> allByCategory = bookDAO.findAllByCategory(category);
        return allByCategory;
    }

    /**
     * 模糊查询
     */
    public List<Book> search(String keywords){
         return   bookDAO.findAllByTitleLikeOrAuthorLike('%'+keywords+'%','%'+keywords+'%');
    }
}

