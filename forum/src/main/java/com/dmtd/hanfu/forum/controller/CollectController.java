package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <br>
 * Copyright (c) 1/8/2020 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.controller <br>
 * FileName: CollectController <br>
 * <br>
 *
 * @author duanmin
 * @created 1/8/2020-10:29 AM
 * @last Modified
 * @history
 */
@Controller
@RequestMapping("collect")
public class CollectController {
    @Autowired
    private CollectionService collectionService;


    /**
     * 根据用户id添加收藏
     * @param userId
     * @return
     */
    @PostMapping("/addCollectByUserId")
    @ResponseBody
    public JsonResult addCollectByUserId(@RequestParam("articleId") Integer articleId, @RequestParam("userId") Integer userId) {
        JsonResult jsonResult = new JsonResult();
        collectionService.addCollectByUserIdAndArticleId(articleId, userId);
        return jsonResult;
    }

    /**
     * 根据userId获取当前
     * @param userId
     * @return
     */
    @GetMapping("/getCollectByUserId")
    @ResponseBody
    public JsonResultData getCollectByUserId(@RequestParam("userId") int userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(collectionService.getCollectByUserId(userId));
        return jsonResultData;
    }

    /**
     * 根据userId获取当前用户收藏的所以帖子
     * @param userId
     * @return
     */
    @GetMapping("/getCollectListByUserId")
    @ResponseBody
    public JsonResultData getCollectListByUserId(@RequestParam("userId") int userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(collectionService.getCollectListByUserId(userId));
        return jsonResultData;
    }
}
