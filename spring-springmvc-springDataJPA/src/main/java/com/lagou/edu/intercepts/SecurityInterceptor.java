package com.lagou.edu.intercepts;

import com.lagou.edu.common.UserCommonInfo;
import com.lagou.edu.exception.NoAuthorityException;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SecurityInterceptor
 * @Description TODO
 * @Author xsq
 * @Date 2020/4/16 15:52
 **/
public class SecurityInterceptor implements HandlerInterceptor {
    /**
     * 登录接口直接跳过过滤
     */
    private final static String LOGIN_URL = "/user/login.do";

    /**
     * controler访问执行业务逻辑前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取访问地址后缀
        String requestURL = request.getRequestURI();
        //如果访问登录页面直接跳过
        if (LOGIN_URL.equals(requestURL)) {
            return true;
        } else {
            //如果访问登录页面直接跳过
            String username = (String) request.getSession().getAttribute(UserCommonInfo.USER_INFO);
            if (username == null) {
                throw new NoAuthorityException("没有权限访问该页面");
            }
            //刷新存活时间
            request.getSession().setMaxInactiveInterval(60 * 30);
        }
        return true;
    }

    /**
     * controler访问执行业务逻辑后执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    /**
     * 返回视图后执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
