package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.config.Config;
import com.dmtd.hanfu.forum.entity.Article;
import com.dmtd.hanfu.forum.entity.PageBean;
import com.dmtd.hanfu.forum.entity.TypeText;
import com.dmtd.hanfu.forum.entity.User;
import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.service.ArticleService;
import com.dmtd.hanfu.forum.service.UserService;
import com.dmtd.hanfu.forum.util.LogUtils;
import com.dmtd.hanfu.forum.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <br>
 * Copyright (c) 1/8/2020 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.controller <br>
 * FileName: ArticleController <br>
 * <br>
 *
 * @author duanmin
 * @created 1/8/2020-10:29 AM
 * @last Modified
 * @history
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    /**
     * 获取帖子分页数据
     *
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public JsonResultData getArticlePageList(@RequestParam("currentPage") int currentPage,
                                             @RequestParam(value = "type",required = false) Integer type,
                                             @RequestParam(value = "userId",required = false) Integer userId) {
        int pageSize = Config.DEFAULT_PAGESIZE;// 每页记录数
        if (currentPage == 0) {
            currentPage = 1;
        }
        JsonResultData jsonResultData = new JsonResultData();
        PageBean pageBean = articleService.getArticlePageList(currentPage, pageSize, type, userId);
        jsonResultData.setData(pageBean);
        return jsonResultData;
    }

    /**
     * 获取帖子收藏/点赞/评论数
     *
     * @return
     */
    @GetMapping("/getArticleActions")
    @ResponseBody
    public JsonResultData getArticleActions(@RequestParam(value = "articleId",required = false) Integer articleId) {
        JsonResultData jsonResultData = new JsonResultData();
        List<TypeText> typeTexts = articleService.getArticleActions(articleId);
        jsonResultData.setData(typeTexts);
        return jsonResultData;
    }


    /**
     * 根据id获取帖子数据
     *
     * @param aid
     * @return
     */
    @RequestMapping("/details")
    @ResponseBody
    public JsonResultData getArticleByID(@RequestParam("aid") Integer aid) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(articleService.getArticleByID(aid));
        // 帖子数据
        return jsonResultData;
    }

    /**
     * 根据id删除帖子数据
     *
     * @param aid
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult deleteArticleByID(HttpServletRequest request, @RequestParam("aid") Integer aid) {
        Map<String, String> map = new HashMap<>();
        JsonResult jsonResult = new JsonResult();
        // 身份检测
        User user = (User) request.getSession().getAttribute("user"); // 当前登录用户
        // Integer uid = articleService.getArticleByID(aid).getUid(); //帖子作者
        Integer uid = articleService.getArticleByID(aid).getAuthor().getUid(); // 帖子作者
        if (user.getUid() != uid) {
            jsonResult.setResultInfo("只能删除自己的帖子！");
            return jsonResult;
        }
        int result = articleService.deleteArticleByID(aid);
        if (result > 0) {
            jsonResult.setResultInfo("删除成功！");
        } else {
            jsonResult.setResultInfo("删除失败！");
        }
        return jsonResult;
    }

    /**
     * 发表新帖
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addArticle(@RequestParam(value = "title") String title,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "userId") Integer userId,
                                 @RequestParam(value = "type") Integer type,
                                 @RequestParam(value = "lable", required = false) String lable) {
        JsonResult result = new JsonResult();
        if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
            result.setResultInfo("标题不能为空！");
            return result;
        }
        articleService.addArticle(title, description, new Timestamp(new Date().getTime()), userId, lable, type);
        result.setResultInfo("发帖成功！");
        return result;
    }

    /**
     * 关键字查询帖子标题
     *
     * @param key
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public List<Article> SearchArticle(@RequestParam("key") String key) {
        return articleService.searchArticleByKey(key);
    }

}
