package com.dmtd.hanfu.forum.controller;

import com.dmtd.hanfu.forum.config.Config;
import com.dmtd.hanfu.forum.dto.FileDTO;
import com.dmtd.hanfu.forum.entity.Article;
import com.dmtd.hanfu.forum.entity.User;
import com.dmtd.hanfu.forum.exception.JsonResult;
import com.dmtd.hanfu.forum.exception.JsonResultData;
import com.dmtd.hanfu.forum.filter.AuthCheck;
import com.dmtd.hanfu.forum.service.ArticleService;
import com.dmtd.hanfu.forum.service.UserService;
import com.dmtd.hanfu.forum.util.FileUtils;
import com.dmtd.hanfu.forum.util.LogUtils;
import com.dmtd.hanfu.forum.util.StringUtils;
import com.dmtd.hanfu.forum.util.TokenUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;


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

    private final String SESSION_TOKEN = "userToken";

    /**
     * 注册用户
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult regist(@RequestParam("username") String username, @RequestParam("password") String password) {
        JsonResult jsonResult = new JsonResult();
        // 保证用户名唯一
        if (userService.findUser(username, null) != null) {
            jsonResult.setResultInfo("用户名已存在！");
            return jsonResult;
        }
        // 默认头像
        String headimg = Config.DEFAULT_HEADIMG_ADDRESS;
        int result = userService.addUser(username, StringUtils.MD5(password), headimg);
        if (result > 0) {
            jsonResult.setResultInfo("注册成功！");
            return jsonResult;
        } else {
            jsonResult.setResultInfo("注册失败！");
        }
        return jsonResult;
    }

    /**
     * 登录
     *
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResultData login(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password) {
        JsonResultData jsonResult = new JsonResultData();
        User user = userService.findUser(username, StringUtils.MD5(password));
        if (user != null) {
            userService.updateUserLoginTime(user.getUid(), new Date());
            jsonResult.setResultInfo("登录成功！");
            request.getSession().setAttribute("user", user);
            String token = TokenUtils.getToken(username);
            request.getSession().setAttribute(SESSION_TOKEN, token);
            Cookie cookie = new Cookie(SESSION_TOKEN, token);
            cookie.setPath("/");
            response.addCookie(cookie);
            jsonResult.setData(user);
        } else {
            jsonResult.setResultInfo("登录失败！");
            return jsonResult;
        }
        return jsonResult;
    }


    /**
     * 根据id获得用户信息及发表过的帖子
     *
     * @return
     */
    @RequestMapping("/manager")
    @AuthCheck
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
    public JsonResultData getUserInfo(@RequestParam("uid") Integer uid) {
        JsonResultData jsonResultData = new JsonResultData();
        // 用户信息
        User user = userService.getUserByID(uid);
        jsonResultData.setData(user);
        return jsonResultData;
    }

    /**
     * 更新头像
     *
     * @param file
     * @param uid
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = "/headimg", method = RequestMethod.POST)
    public JsonResult updateHeadImg(@RequestParam("file") MultipartFile file,
                                @RequestParam("uid") Integer uid) throws IllegalStateException {
        JsonResult jsonResult = new JsonResult();
        String filePath = FileUtils.getFilePath(file);

        int result = userService.updateHeadImg(filePath, uid);
        return jsonResult;
    }

    /**
     * 根据uid更新用户密码
     *
     * @param uid
     * @param password
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @AuthCheck
    public Map<String, String> updateUserInfo(HttpServletRequest request, @RequestParam("uid") Integer uid,
                                              @RequestParam("password") String password) {
        Map<String, String> map = new HashMap<>();
        // 身份检测
        User user = (User) request.getSession().getAttribute("user"); // 当前登录用户
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
     * @return
     */
    @RequestMapping("/exit")
    @ResponseBody
    public JsonResult exit(HttpServletRequest request, @RequestParam("uid") Integer uid) {
        JsonResult jsonResult = new JsonResult();
        // 销毁session
        request.getSession().invalidate();
        return jsonResult;
    }

}
