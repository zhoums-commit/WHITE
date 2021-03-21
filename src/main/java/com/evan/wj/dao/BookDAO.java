package com.evan.wj.dao;

import com.evan.wj.pojo.Book;
import com.evan.wj.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookDAO extends JpaRepository<Book,Integer> {
   /**
    * 查询所有类别书籍
    * @param category
    * @return
    */
   List<Book> findAllByCategory(Category category);

   /**
    * 关键字模糊查询
    * @param keyword1
    * @param keyword2
    * @return
    */
   List<Book> findAllByTitleLikeOrAuthorLike(String keyword1,String keyword2);
}
