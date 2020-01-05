package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
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
    public JsonResultData getIntegralListByUserId(@RequestParam("userId") Integer userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(integralService.getIntegralListByUserId(userId));
        return jsonResultData;
    }
}
