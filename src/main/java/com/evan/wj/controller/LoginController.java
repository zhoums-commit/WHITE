package com.evan.wj.controller;

import com.evan.wj.pojo.User;
import com.evan.wj.result.Result;
import com.evan.wj.result.ResultFactory;
import com.evan.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;


    @CrossOrigin
    @RequestMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser){
        logger.info("开始登录");

        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();

        Subject subject = SecurityUtils.getSubject();
        //username = HtmlUtils.htmlEscape(username);
        //获取用户登录信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        try {
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(username);
        }catch (AuthenticationException e){
            String message = "用户名或者密码错误";
            return ResultFactory.buildFailResult(message);
        }

    }
    /**
     * 注册+密码加密
     *
     */
    @CrossOrigin
    @PostMapping("api/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();

        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        Boolean exist =userService.isExist(username);
        if (exist){
            String message ="用户名已占用";
            logger.info(message);
            return ResultFactory.buildFailResult(message);
        }
        //生成盐，默认十六位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        //设置hash ,算法迭代次数
        int times = 2;
        String md5 = new SimpleHash("md5", password, salt, times).toString();

        //储存用户密码，盐salt,

        user.setSalt(salt);
        user.setPassword(md5);
        userService.add(user);
        return ResultFactory.buildSuccessResult(user);
    }
}
