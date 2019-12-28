package com.dmtd.hanfu.forum.service.impl;

import com.dmtd.hanfu.forum.dao.IntegralDao;
import com.dmtd.hanfu.forum.entity.Integral;
import com.dmtd.hanfu.forum.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>
 * Copyright (c) 12/28/2019 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.service.impl <br>
 * FileName: IntegralServiceImpl <br>
 * <br>
 *
 * @author duanmin
 * @created 12/28/2019-7:06 PM
 * @last Modified
 * @history
 */
@Service("integralService")
public class IntegralServiceImpl implements IntegralService {
    @Autowired
    private IntegralDao integralDao;

    @Override
    public Integral getIntegralByUserId(Integer userId) {
        return integralDao.getIntegralByUserId(userId);
    }
}
