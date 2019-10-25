package com.dmtd.hanfu.forum.dao;

import java.sql.Timestamp;
import java.util.List;

import com.dmtd.hanfu.forum.entity.Comment;
import com.dmtd.hanfu.forum.entity.Floor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "commentDao")
public interface CommentDao {

	public int addComment(@Param("content")String content, 
			@Param("aid")Integer aid, 
			@Param("uid")Integer uid, 
			@Param("date")Timestamp timestamp);

	public List<Comment> findComment(@Param("aid")Integer aid, @Param("uid")Integer uid);

	public int getCommentCount(@Param("aid")Integer aid);

	public List<Floor> findFloorComment(@Param("aid")Integer aid, @Param("cid")Integer cid);

	public int addFloorComment(@Param("aid")Integer aid, @Param("cid")Integer cid,
			@Param("uid")Integer uid, @Param("content")String content);

}
