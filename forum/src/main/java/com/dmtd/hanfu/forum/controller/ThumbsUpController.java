package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.filter.AuthCheck;
import com.dmtd.hanfu.forum.service.ThumbsUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br>
 * Copyright (c) 2020/1/14 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.controller <br>
 * FileName: null.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/14-9:50 PM
 * @last Modified
 * @history
 */
@Controller
@RequestMapping("thumbsUp")
public class ThumbsUpController {
    @Autowired
    ThumbsUpService thumbsUpService;

    /**
     * 根据用户id添加点赞
     * @param userId
     * @return
     */
    @PostMapping("/addThumbsUpByUserId")
    @ResponseBody
    @AuthCheck
    public JsonResult addThumbsUpByUserId(@RequestParam("articleId") Integer articleId,
                                          @RequestParam("userId") Integer userId) {
        JsonResult jsonResult = new JsonResult();
        thumbsUpService.addThumbsUpByUserIdAndArticleId(articleId, userId);
        return jsonResult;
    }


    /**
     * 根据用户id查看是否已点赞
     * @param userId
     * @return
     */
    @PostMapping("/getThumbsUpByUserIdAndArticleId")
    @ResponseBody
    @AuthCheck
    public JsonResultData getThumbsUpByUserIdAndArticleId(@RequestParam("articleId") Integer articleId,
                                                          @RequestParam("userId") Integer userId) {
        JsonResultData jsonResult = new JsonResultData();
        jsonResult.setData(thumbsUpService.getThumbsUpByUserIdAndArticleId(articleId, userId));
        return jsonResult;
    }
}
