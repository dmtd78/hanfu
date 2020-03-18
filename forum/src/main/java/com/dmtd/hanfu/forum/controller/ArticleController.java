package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.config.Config;
import com.dmtd.hanfu.forum.entity.Article;
import com.dmtd.hanfu.forum.entity.PageBean;
import com.dmtd.hanfu.forum.entity.TypeText;
import com.dmtd.hanfu.forum.exception.ForumBizException;
import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.filter.AuthCheck;
import com.dmtd.hanfu.forum.service.ArticleService;
import com.dmtd.hanfu.forum.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


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
                                             @RequestParam(value = "type", required = false) Integer type,
                                             @RequestParam(value = "userId", required = false) String userId) {
        int pageSize = Config.DEFAULT_PAGESIZE;// 每页记录数
        if (currentPage == 0) {
            currentPage = 1;
        }
        JsonResultData jsonResultData = new JsonResultData();
        PageBean pageBean = articleService.getArticlePageList(currentPage, pageSize, type, Integer.getInteger(userId));
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
    public JsonResultData getArticleActions(@RequestParam(value = "articleId", required = true) String articleId)
            throws ForumBizException {
        if (StringUtils.isBlank(articleId)) {
            throw new ForumBizException("参数不能为空！");
        }
        JsonResultData jsonResultData = new JsonResultData();
        List<TypeText> typeTexts = articleService.getArticleActions(Integer.parseInt(articleId));
        jsonResultData.setData(typeTexts);
        return jsonResultData;
    }

    /**
     * 获取我的收藏等跟我相关等帖子信息
     *
     * @return
     */
    @GetMapping("/iCollectArticles")
    @ResponseBody
    @AuthCheck
    public JsonResultData iCollectArticles(@RequestParam("currentPage") int currentPage,
                                           @RequestParam(value = "type", required = false) Integer type,
                                           @RequestParam(value = "userId", required = false) Integer userId) {
        int pageSize = Config.DEFAULT_PAGESIZE;// 每页记录数
        if (currentPage == 0) {
            currentPage = 1;
        }
        JsonResultData jsonResultData = new JsonResultData();
        List<Article> articles = articleService.iCollectArticles(currentPage, pageSize, type, userId);
        jsonResultData.setData(articles);
        return jsonResultData;
    }

    /**
     * 根据id获取帖子数据
     *
     * @param articleId
     * @return
     */
    @GetMapping("/details")
    @ResponseBody
    public JsonResultData getArticleByID(@RequestParam("articleId") Integer articleId) {
        JsonResultData jsonResultData = new JsonResultData();
        jsonResultData.setData(articleService.getArticleById(articleId));
        // 帖子数据
        return jsonResultData;
    }

    /**
     * 根据id删除帖子数据
     *
     * @param articleId
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public JsonResult deleteArticleByID(@RequestParam("articleId") Integer articleId) {
        JsonResult jsonResult = new JsonResult();
        int result = articleService.deleteArticleByID(articleId);
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
    @PostMapping(value = "/add")
    @ResponseBody
    @AuthCheck
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
    @GetMapping("/search")
    @ResponseBody
    public List<Article> searchArticle(@RequestParam("key") String key) {
        return articleService.searchArticleByKey(key);
    }

}
