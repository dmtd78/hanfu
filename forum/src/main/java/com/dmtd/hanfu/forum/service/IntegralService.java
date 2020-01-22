package com.dmtd.hanfu.forum.service;

import com.dmtd.hanfu.forum.dto.IntegralDto;
import com.dmtd.hanfu.forum.entity.Integral;

import java.util.List;

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

    List<Integral> getIntegralListByUserId(Integer userId);

    void addIntegralByUserId(Integer userId);

    Integral hasIntegralByUserId(Integer userId);

    Integral getRecentlyIntegralByUserId(Integer userId);

    List<Integral> getIntegralDays(Integer userId);

    IntegralDto getSignDayCount(Integer userId);
}
