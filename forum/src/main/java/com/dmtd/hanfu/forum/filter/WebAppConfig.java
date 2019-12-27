package com.dmtd.hanfu.forum.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <br>
 * Copyright (c) 12/27/2019 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.filter <br>
 * FileName: WebAppConfig <br>
 * <br>
 *
 * @author duanmin
 * @created 12/27/2019-10:23 AM
 * @last Modified
 * @history
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())//添加拦截器
                .addPathPatterns("/**") //拦截所有请求
                .excludePathPatterns("/article/**", "/comment/**", "/user/**");//对应的不拦截的请求
    }
}
