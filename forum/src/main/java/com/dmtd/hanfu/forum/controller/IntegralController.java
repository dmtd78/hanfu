package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.entity.Integral;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/getIntegralByUserId")
    @ResponseBody
    public JsonResultData getIntegralByUserId(@RequestParam("userId") int userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(integralService.getIntegralByUserId(userId));
        return jsonResultData;
    }
}
