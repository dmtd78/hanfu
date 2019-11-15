package com.dmtd.hanfu.forum.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dmtd.hanfu.forum.entity.Comment;
import com.dmtd.hanfu.forum.entity.Floor;
import com.dmtd.hanfu.forum.entity.User;
import com.dmtd.hanfu.forum.service.CommentService;
import com.dmtd.hanfu.forum.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * @author LJC 帖子评论Controller
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发表评论
     *
     * @param content
     * @param aid
     * @param uid
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addComment(@RequestParam(value = "content", required = false) String content, @RequestParam(value = "aid") Integer aid, @RequestParam(value = "uid") Integer uid) {
        Map<String, String> map = new HashMap<>();
        // 插入评论
        int result = commentService.addComment(content, aid, uid, new Timestamp(new Date().getTime()));
        if (result > 0) {
            LogUtils.info("回复成功！内容为:" + content);
            map.put("data", "回复成功！");
        } else {
            LogUtils.info("回复失败！");
            map.put("data", "回复失败！");
        }
        return map;
    }

	/**
	 *
	 * @param content
	 * @param aid
	 * @param uid
	 * @return
	 */
    @RequestMapping(value = "/commentList", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> commentList(@RequestParam(value = "content", required = false) String content,
									 @RequestParam(value = "aid") Integer aid,
									 @RequestParam(value = "uid") Integer uid) {
        // 评论列表
        List<Comment> commentList = commentService.findComment(aid, uid);
        return commentList;
    }

	/**
	 *
	 * @param content
	 * @param aid
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/floorCommentList", method = RequestMethod.GET)
	@ResponseBody
	public List<Floor> floorCommentList(@RequestParam(value = "content", required = false) String content,
									 @RequestParam(value = "aid") Integer aid,
									 @RequestParam(value = "uid") Integer uid) {
		// 评论列表
		// 楼中楼评论数据
		List<Floor> floorCommentList = commentService.findFloorComment(aid, null);
		return floorCommentList;
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
