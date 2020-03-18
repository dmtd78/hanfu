package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.filter.AuthCheck;
import com.dmtd.hanfu.forum.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <br>
 * Copyright (c) 12/28/2019 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.controller <br>
 * FileName: IntegralController <br>
 * <br>
 *
 * @author duanmin
 * @created 12/28/2019-7:03 PM
 * @last Modified
 * @history
 */
@Controller
@RequestMapping("integral")
public class IntegralController {
    @Autowired
    private IntegralService integralService;

    /**
     * 根据用户id添加积分
     * @param userId
     * @return
     */
    @PostMapping("/addIntegralByUserId")
    @ResponseBody
    @AuthCheck
    public JsonResult addIntegralByUserId(@RequestParam("userId") Integer userId) {
        JsonResult jsonResult = new JsonResult();
        integralService.addIntegralByUserId(userId);
        return jsonResult;
    }

    /**
     * 根据userId获取当前
     * @param userId
     * @return
     */
    @GetMapping("/getIntegralByUserId")
    @ResponseBody
    @AuthCheck
    public JsonResultData getIntegralByUserId(@RequestParam("userId") int userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(integralService.getIntegralByUserId(userId));
        return jsonResultData;
    }

    /**
     * 根据用户id获取积分列表
     * @param userId
     * @return
     */
    @GetMapping("/getIntegralListByUserId")
    @ResponseBody
    @AuthCheck
    public JsonResultData getIntegralListByUserId(@RequestParam("userId") Integer userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(integralService.getIntegralListByUserId(userId));
        return jsonResultData;
    }

    /**
     * 看今天是否已签到
     * @param userId
     * @return
     */
    @GetMapping("/hasIntegralByUserId")
    @ResponseBody
    @AuthCheck
    public JsonResultData hasIntegralByUserId(@RequestParam("userId") Integer userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(integralService.hasIntegralByUserId(userId));
        return jsonResultData;
    }

    /**
     * 积分情况
     * @param userId
     * @return
     */
    @GetMapping("/getIntegralDays")
    @ResponseBody
    @AuthCheck
    public JsonResultData getIntegralDays(@RequestParam("userId") Integer userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(integralService.getIntegralDays(userId));
        return jsonResultData;
    }

    /**
     * 连续签到天数，取最近一条签到记录，如果最近签到日期等于昨天，则连续签到天数为昨天的积分数，
     * 如果最近签到日期等于今天，则连续签到天数为今天的积分数，
     * 如果最近签到积分等于7，则6+7的个数就是连续签到数。
     * @param userId
     * @return
     */
    @GetMapping("/getSignDayCount")
    @ResponseBody
    @AuthCheck
    public JsonResultData getSignDayCount(@RequestParam("userId") Integer userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(integralService.getSignDayCount(userId));
        return jsonResultData;
    }
}
