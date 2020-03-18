package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.filter.AuthCheck;
import com.dmtd.hanfu.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * <br>
 * Copyright (c) 1/8/2020 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.controller <br>
 * FileName: CommentController <br>
 * <br>
 *
 * @author duanmin
 * @created 1/8/2020-10:29 AM
 * @last Modified
 * @history
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * @param content
     * @param articleId
     * @param userId
     * @return
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @AuthCheck
    public JsonResult addComment(@RequestParam(value = "content", required = false) String content,
                                 @RequestParam(value = "articleId") Integer articleId,
                                 @RequestParam(value = "userId") Integer userId) {
        JsonResult jsonResult = new JsonResult();
        // 插入评论
        int result = commentService.addComment(content, articleId, userId);
        if (result == 0) {
            jsonResult.setResultInfo("评论失败！");
        }
        return jsonResult;
    }

    /**
     * @param content
     * @param articleId
     * @param userId
     * @return
     */
    @GetMapping(value = "/list")
    @ResponseBody
    @AuthCheck
    public JsonResultData commentList(@RequestParam(value = "content", required = false) String content,
                                      @RequestParam(value = "articleId", required = false) Integer articleId,
                                      @RequestParam(value = "userId", required = false) Integer userId) {
        JsonResultData jsonResultData = new JsonResultData();
        // 评论列表
        jsonResultData.setData(commentService.findComment(articleId, userId));
        return jsonResultData;
    }

    /**
     * @param content
     * @param aid
     * @param uid
     * @return
     */
    @GetMapping(value = "/floorCommentList")
    @ResponseBody
    public JsonResultData floorCommentList(@RequestParam(value = "content", required = false) String content,
                                           @RequestParam(value = "aid") Integer aid,
                                           @RequestParam(value = "uid") Integer uid) {
        JsonResultData jsonResultData = new JsonResultData();
        // 评论列表
        // 楼中楼评论数据
        jsonResultData.setData(commentService.findFloorComment(aid, null));
        return jsonResultData;
    }

    /**
     * 添加楼中楼评论
     * @param articleId
     * @param commentId
     * @param userId
     * @param content
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/floor/add")
    @AuthCheck
    public JsonResult findFloorComment(@RequestParam("articleId") Integer articleId,
                                       @RequestParam("commentId") Integer commentId,
                                       @RequestParam("userId") Integer userId,
                                       @RequestParam("content") String content) {
        JsonResult jsonResult = new JsonResult();
        // 楼中楼评论数据
        int result = commentService.addFloorComment(articleId, commentId, userId, content);
        if (result > 0) {
            jsonResult.setResultInfo("评论成功!内容=>" + content);
        } else {
            jsonResult.setResultInfo("评论失败!");
        }
        return jsonResult;
    }

}
