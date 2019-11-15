package com.dmtd.hanfu.forum.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.dmtd.hanfu.forum.config.Config;
import com.dmtd.hanfu.forum.entity.*;
import com.dmtd.hanfu.forum.service.ArticleService;
import com.dmtd.hanfu.forum.service.CommentService;
import com.dmtd.hanfu.forum.service.UserService;
import com.dmtd.hanfu.forum.util.LogUtils;
import com.dmtd.hanfu.forum.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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

	@Autowired
	private CommentService commentService;

	/**
	 * 获取帖子分页数据
	 * 
	 * @return
	 */
	@GetMapping("/list/{currentPage}")
    @ResponseBody
	public PageBean getArticlePageList(HttpSession session, @PathVariable("currentPage") int currentPage) {
		int pageSize = Config.DEFAULT_PAGESIZE;// 每页记录数
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
	@RequestMapping("/details/{aid}")
	public Article getArticleByID(@PathVariable("aid") Integer aid) {
		// 帖子数据
		Article article = articleService.getArticleByID(aid);
		return article;
	}

	/**
	 * 根据id删除帖子数据
	 * 
	 * @param aid
	 * @return
	 */
	@RequestMapping("/delete/{aid}")
	@ResponseBody
	public Map<String, String> deleteArticleByID(HttpSession session, @PathVariable("aid") Integer aid) {
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
	public Map<String, String> addArticle(HttpSession session, @RequestParam(value = "title") String title, @RequestParam(value = "content") String content, @RequestParam(value = "uid") Integer uid, @RequestParam(value = "lable", required = false) String lable) {
		Map<String, String> map = new HashMap<>();
		// 身份检测
		User user = (User) session.getAttribute("user");
		if (user == null) {
			map.put("data", "请登录后在发帖！");
			return map;
		}
		if (StringUtils.isEmpty(title) || StringUtils.isBlank(title)) {
			map.put("data", "标题不能为空！");
			return map;
		}
		int result = articleService.addArticle(title, content, new Timestamp(new Date().getTime()), uid, lable);
		if (result > 0) {
			LogUtils.info("发帖成功,标题:{},内容长度:{}", title, content.length());
			map.put("data", "发帖成功！");
		} else {
			LogUtils.info("发帖失败！");
		}
		return map;
	}

	/**
	 * 关键字查询帖子标题
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView SearchArticle(@RequestParam("key") String key) {
		ModelAndView mav = new ModelAndView();
		List<Article> list = articleService.searchArticleByKey(key);
		LogUtils.info("查询关键字:{},共查询到{}条记录", key, list.size());
		mav.addObject("key", key);
		mav.addObject("resultList", list);
		mav.setViewName("search");
		return mav;
	}

}
