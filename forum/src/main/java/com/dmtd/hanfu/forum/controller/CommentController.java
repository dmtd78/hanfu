package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.entity.User;
import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.service.CommentService;
import com.dmtd.hanfu.forum.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
     *
     * @param content
     * @param articleId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResultData commentList(@RequestParam(value = "content", required = false) String content,
                                      @RequestParam(value = "articleId" ,required = false) Integer articleId,
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
    @RequestMapping(value = "/floorCommentList", method = RequestMethod.GET)
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
     *
     * @param aid
     * @param cid
     * @param uid
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/floor/add", method = RequestMethod.POST)
    public Map<String, String> findFloorComment(HttpSession session, @RequestParam("aid") Integer aid, @RequestParam("cid") Integer cid, @RequestParam("uid") Integer uid, @RequestParam("content") String content) {
        Map<String, String> map = new HashMap<String, String>();
        // 身份检测
        User user = (User) session.getAttribute("user"); // 当前登录用户
        if (user.getUid() != uid) {
            map.put("data", "回复失败！");
            return map;
        }
        // 楼中楼评论数据
        int result = commentService.addFloorComment(aid, cid, uid, content);
        if (result > 0) {
            LogUtils.info("楼中楼评论成功!内容=>" + content);
            // map.put("data", "回复成功！");
        } else {
            LogUtils.info("楼中楼评论失败!");
            // map.put("data", "回复失败！");
        }
        return map;
    }

}
