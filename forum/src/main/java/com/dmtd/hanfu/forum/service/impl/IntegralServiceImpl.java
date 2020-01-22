package com.dmtd.hanfu.forum.service.impl;

import com.dmtd.hanfu.forum.dao.IntegralDao;
import com.dmtd.hanfu.forum.dto.IntegralDto;
import com.dmtd.hanfu.forum.entity.Integral;
import com.dmtd.hanfu.forum.service.IntegralService;
import com.dmtd.hanfu.forum.util.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<Integral> getIntegralListByUserId(Integer userId) {
        return integralDao.getIntegralListByUserId(userId);
    }

    @Override
    public void addIntegralByUserId(Integer userId) {
        int integralNum = 0;
        //首先看昨天是否签到，如果没有签到，积分加1，如果昨天已签到，昨天签到基础上加1，最大数为7。
        Integral yesterdayIntegral = integralDao.getYesterdayIntegralByUserId(userId);
        if (yesterdayIntegral == null) {
            integralNum = 1;
        } else {
            if (yesterdayIntegral.getIntegral() == 7) {
                integralNum = 7;
            } else {
                integralNum = yesterdayIntegral.getIntegral() + 1;
            }
        }
        //检查今天是否已经签到，如果以签到，则不签到了。
        Integral todayIntegral = integralDao.getTodayIntegralByUserId(userId);
        if (todayIntegral == null) {
            integralDao.addIntegralByUserId(integralNum, userId);
        }
    }

    @Override
    public Integral hasIntegralByUserId(Integer userId) {
        return integralDao.getTodayIntegralByUserId(userId);
    }

    @Override
    public Integral getRecentlyIntegralByUserId(Integer userId) {
        Integral integral = integralDao.getRecentlyIntegralByUserId(userId);
        if (integral == null || !TimeUtils.getDate(integral.getCreateTime(), TimeUtils.YYYY_MM_DD)
                .equals(TimeUtils.getDate(new Date(), TimeUtils.YYYY_MM_DD))) {
            integral = new Integral();
            integral.setIntegral(1);
            return integral;
        } else {
            return integral;
        }
    }

    @Override
    public List<Integral> getIntegralDays(Integer userId) {
        List<Integral> integrals = integralDao.getIntegralDays(userId);
        return integrals;
    }

    /**
     * 连续签到天数，取最近一条签到记录，如果最近签到日期等于昨天，则连续签到天数为昨天的积分数，
     * 如果最近签到日期等于今天，则连续签到天数为今天的积分数，
     * 如果最近签到积分等于7，则6+7的个数就是连续签到数。
     *
     * @param userId
     * @return
     */
    @Override
    public IntegralDto getSignDayCount(Integer userId) {
        Integral todayIntegral = integralDao.getTodayIntegralByUserId(userId);
        if (todayIntegral == null) {
            Integral yesterdayIntegral = integralDao.getYesterdayIntegralByUserId(userId);
            if (yesterdayIntegral == null) {
                IntegralDto integralDto = new IntegralDto();
                integralDto.setSignDay(1);
                return integralDto;
            } else {
                IntegralDto integralDto = new IntegralDto();
                BeanUtils.copyProperties(yesterdayIntegral, integralDto);
                integralDto.setSignDay(yesterdayIntegral.getIntegral());
                return integralDto;
            }
        } else {
            //积分等于7的情况
            if (todayIntegral.getIntegral() == 7) {
                IntegralDto integralDto = new IntegralDto();
                integralDto.setSignDay(6+7);
                return integralDto;
            } else {
                IntegralDto integralDto = new IntegralDto();
                BeanUtils.copyProperties(todayIntegral, integralDto);
                integralDto.setSignDay(todayIntegral.getIntegral());
                return integralDto;
            }

        }

    }
}
