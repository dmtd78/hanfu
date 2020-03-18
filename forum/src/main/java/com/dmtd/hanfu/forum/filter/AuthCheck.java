package com.dmtd.hanfu.forum.filter;

import java.lang.annotation.*;

/**
 * Copyright (c) 3/18/2020 All Rights Reserved By SUNWIN
 * <p>
 * Package: com.dmtd.hanfu.forum.filter
 * FileName: AuthCheck
 *
 * @author duanmin
 * @description 权限校验注解,作用于方法之上
 * @created 3/18/2020-3:15 PM
 * @last Modified
 * @history
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthCheck {
}
