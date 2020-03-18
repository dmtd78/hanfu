package com.dmtd.hanfu.forum.filter;

import com.dmtd.hanfu.forum.exception.ForumBizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Component
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = Logger.getLogger(this.getClass().getName());
    private final String SESSION_TOKEN = "userToken";

    /**
     * 进入controller层之前拦截请求
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler)
            throws Exception {
        logger.info("---------------开始进入地址拦截器-------------------");
        //token 校验
        AuthCheck authCheck;
        if (handler instanceof HandlerMethod) {
            authCheck = ((HandlerMethod) handler).getMethodAnnotation(AuthCheck.class);
        } else {
            return true;
        }
        //这里处理一些业务逻辑，比如
        //有authCheck注解的方法需要进行登录校验
        String authToken = "";
        if (authCheck != null) {
            authToken = (String)httpServletRequest.getSession().getAttribute(SESSION_TOKEN);
            if (StringUtils.isBlank(authToken)) {
                //这里自定义的一个异常，与全局异常捕获配合
                throw new ForumBizException("权限受限");
            }
        }
        return true;
    }

    //访问视图之后被调用
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        logger.info("---------------视图渲染之后的操作-------------------------0");
    }
}
