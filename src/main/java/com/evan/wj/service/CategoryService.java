package com.evan.wj.service;


import com.evan.wj.dao.CategoryDAO;
import com.evan.wj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list(){
        //降序
        Sort sort =new Sort(Sort.Direction.DESC,"id");
        List<Category> list = categoryDAO.findAll(sort);
        return list;
    }

    public Category get(int id){
        Category category = categoryDAO.findById(id).orElse(null);
        return category;
    }
}
