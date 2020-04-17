package com.lagou.edu.controller;

import com.lagou.edu.common.ResultPack;
import com.lagou.edu.common.UserCommonInfo;
import com.lagou.edu.exception.LoginException;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ResumeController
 * @Description TODO
 * @Author xsq
 * @Date 2020/4/16 15:39
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    private final static String USERNAME = "admin";

    private final static String PASSWORD = "admin";

    @Autowired
    private ResumeService resumeService;

    @RequestMapping("/login.do")
    @ResponseBody
    public ResultPack login(String username, String password, HttpServletRequest request) {
        if (!USERNAME.equals(username)) {
            throw new LoginException("用户名错误");
        }
        if (!PASSWORD.equals(password)) {
            throw new LoginException("密码错误");
        }
        //在session中保存登录的用户信息,在拦截器拦截的时候验证是否可以访问
        request.getSession().setAttribute(UserCommonInfo.USER_INFO, username);
        //刷新存活时间
        request.getSession().setMaxInactiveInterval(60 * 30);
        return ResultPack.success();
    }
}
