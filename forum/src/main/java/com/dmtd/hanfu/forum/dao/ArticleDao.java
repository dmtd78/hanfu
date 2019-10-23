package com.dmtd.hanfu.forum.dao;

import com.dmtd.hanfu.forum.entity.Article;

import java.sql.Timestamp;
import java.util.List;


public interface ArticleDao {

	public List<Article> getArticleList();

	public Article getArticleByID(@Param("aid")Integer aid);

	public int addArticle(@Param("title") String title,
			@Param("content") String content,
			@Param("date") Timestamp timestamp,
			@Param("uid") Integer uid,
			@Param("lable") String lable);

	public List<Article> getArticleByUID(@Param("uid")Integer uid);

	public int deleteArticleByID(@Param("aid")Integer aid);

	public List<Article> getArticlePageList(@Param("currentPage") int currentPage,@Param("pageSize") int pageSize);

	public List<Article> searchArticleByKey(@Param("key")String key);
	
	public List<Article> getArticleListByStatus(List<Integer> statusList);
	
	public int getArticleCount();
}
