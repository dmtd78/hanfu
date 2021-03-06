package com.dmtd.hanfu.forum.exception;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * <br>
 * Copyright (c) 1/21/2020 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.exception <br>
 * FileName: CustomExceptionResolver <br>
 * <br>
 * 自定义异常统一处理
 * @author duanmin
 * @created 1/21/2020-10:29 AM
 * @last Modified
 * @history
 */
@Configuration
public class CustomExceptionResolver implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        httpServletResponse.setStatus(HttpStatus.OK.value());
        Map<String, String> body = this.getBody(ex);
        try {
            JsonResult jsonResult = new JsonResult(body.get("message"));
            httpServletResponse.getWriter().write(JSON.toJSONString(jsonResult));
        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return new ModelAndView();
    }

    /**
     * 获取错误编码
     */
    @SuppressWarnings("unused")
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * <p>
     * <code>getBody</code>根据exception获取返回信息
     * </p>
     */
    private Map<String, String> getBody(Exception ex) {
        Map<String, String> body = new HashMap<String, String>();
        if (ex instanceof ForumBizException) {
            body.put("message", ex.getMessage());
        } else if (ex instanceof IllegalArgumentException) {
            body.put("message", ex.getMessage());
        } else {
            body.put("message", "系统出现未知异常，请联系管理员！");
        }
        return body;
    }
}
