package com.dmtd.hanfu.forum.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dmtd.hanfu.forum.config.Config;
import com.dmtd.hanfu.forum.entity.Article;
import com.dmtd.hanfu.forum.entity.User;
import com.dmtd.hanfu.forum.service.ArticleService;
import com.dmtd.hanfu.forum.service.UserService;
import com.dmtd.hanfu.forum.util.LogUtils;
import com.dmtd.hanfu.forum.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author LJC 用户登录注册Controller
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> regist(@RequestParam("username") String username, @RequestParam("password") String password) {

		Map<String, String> map = new HashMap<String, String>();
		// 保证用户名唯一
		if (userService.findUser(username, null) != null) {
			map.put("data", "用户名已存在！");
			return map;
		}
		// 默认头像
		String headimg = Config.DEFAULT_HEADIMG_ADDRESS;
		int result = userService.addUser(username, StringUtils.MD5(password), headimg);
		if (result > 0) {
			LogUtils.info("注册成功！用户名:{},密码：{}", username, password);
			map.put("data", "注册成功,请重新登录！");
		} else {
			LogUtils.info("注册失败!");
			map.put("data", "注册失败！");
		}
		return map;
	}

	/**
	 * 登录
	 * 
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(HttpSession session, @RequestParam("username") String username,
                                     @RequestParam("password") String password) {
		User user = userService.findUser(username, StringUtils.MD5(password));
		Map<String, String> map = new HashMap<String, String>();
		if (user != null) {
			userService.updateUserLoginTime(user.getUid(), new Date());
			LogUtils.info("登录成功！用户名:{},密码：{}", username, password);
			map.put("data", "登录成功！");
			session.setAttribute("user", user);
		} else {
			LogUtils.info("登录失败!");
			map.put("data", "登录失败！");
		}
		return map;
	}

	/**
	 * 根据id获得用户信息及发表过的帖子
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public ModelAndView getUserManager(@RequestParam("uid") Integer uid) {
		ModelAndView mav = new ModelAndView();
		// 用户信息
		User user = userService.getUserByID(uid);
		// 用户帖子数据
		List<Article> articleList = articleService.getArticleByUID(uid);
		mav.addObject("u", user);
		mav.addObject("userArticle", articleList);
		mav.setViewName("user/userManager");
		return mav;
	}

	/**
	 * 根据id获得用户信息
	 * 
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public User getUserInfo(@RequestParam("uid") Integer uid) {
		// 用户信息
		return userService.getUserByID(uid);
	}

	/**
	 * 更新头像
	 * 
	 * @param request
	 * @param file
	 * @param uid
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/headimg", method = RequestMethod.POST)
	public String updateHeadImg(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("uid") Integer uid) throws IllegalStateException, IOException {
		// 防止空白头像的情况
		if (file.isEmpty())
			return null;
		// 进行上传操作
		String fileName = uid + "-" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getOriginalFilename();
		String filePath = request.getServletContext().getRealPath(Config.DEFAULT_UPLOAD_ADDRESS) + "/" + fileName;
		File dest = new File(filePath);
		dest.getParentFile().mkdirs();
		// transferto实现文件上传
		file.transferTo(dest);
		// 头像地址并更根据uid插入对应数据库
		String address = Config.DEFAULT_UPLOAD_ADDRESS + "/" + fileName;
		int result = userService.updateHeadImg(address, uid);
		if (result > 0) {
			LogUtils.info("成功更新uid为{}的用户头像,文件名{}", uid, address);
		} else {
			LogUtils.info("更新头像失败！");
		}
		// 刷新session
		request.getSession().setAttribute("user", userService.getUserByID(uid));
		return "redirect:/index.jsp";
	}

	/**
	 * 根据uid更新用户密码
	 * 
	 * @param session
	 * @param uid
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateUserInfo(HttpSession session, @RequestParam("uid") Integer uid,
                                              @RequestParam("password") String password) {
		Map<String, String> map = new HashMap<>();
		// 身份检测
		User user = (User) session.getAttribute("user"); // 当前登录用户
		if (user.getUid() != uid) {
			map.put("data", "只能修改自己的信息！");
			return map;
		}
		// 更新用户信息
		int result = userService.updateUserInfo(uid, StringUtils.MD5(password));
		if (result > 0) {
			LogUtils.info("成功更新id为{}的用户信息！新密码:{}", uid, password);
			map.put("data", "信息修改成功！");
		} else {
			map.put("data", "信息修改失败！");
		}
		return map;
	}

	/**
	 * 安全退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		// 销毁session
		session.invalidate();
		return "redirect:/index.jsp";
	}

}
