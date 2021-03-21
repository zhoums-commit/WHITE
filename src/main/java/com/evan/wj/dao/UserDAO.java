package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Integer>{
    /**
     *
     * @param username z
     * @return  返回用户实例
     * 通过用户名查询用户信息
     */
    User findByUsername(String username);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    User getByUsernameAndPassword(String username,String password);
}
