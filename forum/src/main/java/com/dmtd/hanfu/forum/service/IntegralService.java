package com.dmtd.hanfu.forum.service;

import com.dmtd.hanfu.forum.entity.Integral;

/**
 * <br>
 * Copyright (c) 12/28/2019 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.service <br>
 * FileName: IntegralService <br>
 * <br>
 *
 * @author duanmin
 * @created 12/28/2019-7:05 PM
 * @last Modified
 * @history
 */
public interface IntegralService {
    public Integral getIntegralByUserId(Integer userId);
}
