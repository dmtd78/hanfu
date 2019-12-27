package com.dmtd.hanfu.forum.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.dmtd.hanfu.forum.config.Config;
import com.dmtd.hanfu.forum.entity.*;
import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.service.ArticleService;
import com.dmtd.hanfu.forum.service.UserService;
import com.dmtd.hanfu.forum.util.LogUtils;
import com.dmtd.hanfu.forum.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author LJC 帖子列表Controller
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
	public PageBean getArticlePageList(HttpSession session, @RequestParam("currentPage") int currentPage) {
		int pageSize = Config.DEFAULT_PAGESIZE;// 每页记录数
		if(currentPage==0){
			currentPage=1;
		}
		PageBean pageBean = articleService.getArticlePageList(currentPage, pageSize);

		// 刷新session
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			session.setAttribute("user", userService.getUserByID(user.getUid()));
		}
		return pageBean;
	}

	/**
	 * 根据id获取帖子数据
	 * 
	 * @param aid
	 * @return
	 */
	@RequestMapping("/details")
	@ResponseBody
	public Article getArticleByID(@RequestParam("aid") Integer aid) {
		// 帖子数据
		return articleService.getArticleByID(aid);
	}

	/**
	 * 根据id删除帖子数据
	 * 
	 * @param aid
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, String> deleteArticleByID(HttpSession session, @RequestParam("aid") Integer aid) {
		Map<String, String> map = new HashMap<>();
		// 身份检测
		User user = (User) session.getAttribute("user"); // 当前登录用户
		// Integer uid = articleService.getArticleByID(aid).getUid(); //帖子作者
		Integer uid = articleService.getArticleByID(aid).getAuthor().getUid(); // 帖子作者
		if (user.getUid() != uid) {
			map.put("data", "只能删除自己的帖子！");
			return map;
		}
		int result = articleService.deleteArticleByID(aid);
		if (result > 0) {
			LogUtils.info("成功删除id为{}的帖子！", aid);
			map.put("data", "删除成功！");
		} else {
			LogUtils.info("删除失败id为{}的帖子！", aid);
			map.put("data", "删除失败！");
		}
		return map;
	}

	/**
	 * 发表新帖
	 * 
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult addArticle(HttpSession session, @RequestParam(value = "title") String title,
										  @RequestParam(value = "content") String content,
										  @RequestParam(value = "uid") Integer uid,
										  @RequestParam(value = "lable", required = false) String lable) {
		JsonResult result = new JsonResult();
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			result.setResultInfo("标题不能为空！");
			return result;
		}
		articleService.addArticle(title, content, new Timestamp(new Date().getTime()), uid, lable);
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
