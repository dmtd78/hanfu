package com.dmtd.hanfu.forum.filter;

import com.dmtd.hanfu.forum.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * <br>
 * Copyright (c) 2019/12/27 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.filter <br>
 * FileName: LoginInterceptor <br>
 * <br>
 * 新增登录过滤器
 *
 * @author duanmin
 * @created 2019/12/27 13:56
 * @last Modified
 * @history
 */
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * 进入controller层之前拦截请求
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("---------------开始进入地址拦截器-------------------");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return false;
        }
        return true;
    }

    //访问视图之后被调用
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("---------------视图渲染之后的操作-------------------------0");
    }
}
