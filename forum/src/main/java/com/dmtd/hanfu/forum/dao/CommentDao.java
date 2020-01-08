package com.dmtd.hanfu.forum.dao;

import com.dmtd.hanfu.forum.entity.Comment;
import com.dmtd.hanfu.forum.entity.Floor;
import com.dmtd.hanfu.forum.entity.TypeText;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "commentDao")
public interface CommentDao {

	public int addComment(@Param("content")String content, 
			@Param("articleId")Integer articleId,
			@Param("userId")Integer userId);

	public List<Comment> findComment(@Param("articleId")Integer articleId, @Param("userId")Integer userId);

	public int getCommentCount(@Param("aid")Integer aid);

	public List<Floor> findFloorComment(@Param("aid")Integer aid, @Param("cid")Integer cid);

	public int addFloorComment(@Param("aid")Integer aid, @Param("cid")Integer cid,
			@Param("uid")Integer uid, @Param("content")String content);

    TypeText getTypeTextByArticleId(@Param("articleId")Integer articleId);
}
