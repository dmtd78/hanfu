package com.dmtd.hanfu.forum.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.dmtd.hanfu.forum.dao.CommentDao;
import com.dmtd.hanfu.forum.entity.Comment;
import com.dmtd.hanfu.forum.entity.Floor;
import com.dmtd.hanfu.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public int addComment(String content, Integer aid, Integer uid,Timestamp timestamp) {
		return commentDao.addComment(content, aid, uid, timestamp);
	}

	@Override
	public List<Comment> findComment(Integer aid, Integer uid) {
		return commentDao.findComment(aid, uid);
	}

	@Override
	public int getCommentCount(Integer aid) {
		return commentDao.getCommentCount(aid);
	}

	@Override
	public List<Floor> findFloorComment(Integer aid, Integer cid) {
		return commentDao.findFloorComment(aid, cid);
	}

	@Override
	public int addFloorComment(Integer aid, Integer cid, Integer uid,
			String content) {
		return commentDao.addFloorComment(aid,cid,uid,content);
	}

}
