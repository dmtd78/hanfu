package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.filter.AuthCheck;
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
     *
     * @param userId
     * @return
     */
    @PostMapping("/addCollectByUserId")
    @ResponseBody
    @AuthCheck
    public JsonResult addCollectByUserId(@RequestParam("articleId") Integer articleId, @RequestParam("userId") Integer userId) {
        JsonResult jsonResult = new JsonResult();
        collectionService.addCollectByUserIdAndArticleId(articleId, userId);
        return jsonResult;
    }

    /**
     * 根据userId获取当前
     *
     * @param userId
     * @return
     */
    @GetMapping("/getCollectByUserId")
    @ResponseBody
    @AuthCheck
    public JsonResultData getCollectByUserId(@RequestParam("userId") int userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(collectionService.getCollectByUserId(userId));
        return jsonResultData;
    }

    /**
     * 根据userId获取当前用户收藏的所以帖子
     *
     * @param userId
     * @return
     */
    @GetMapping("/getCollectListByUserId")
    @ResponseBody
    @AuthCheck
    public JsonResultData getCollectListByUserId(@RequestParam("userId") int userId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(collectionService.getCollectListByUserId(userId));
        return jsonResultData;
    }


    /**
     * 根据id删除收藏数据
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/deleteCollectByArticleId")
    @ResponseBody
    @AuthCheck
    public JsonResult deleteCollectByArticleId(@RequestParam("articleId") Integer articleId,
                                               @RequestParam("userId") Integer userId) {
        JsonResult jsonResult = new JsonResult();
        int result = collectionService.deleteCollectByArticleId(articleId, userId);
        if (result > 0) {
            jsonResult.setResultInfo("删除成功！");
        } else {
            jsonResult.setResultInfo("删除失败！");
        }
        return jsonResult;
    }

}
