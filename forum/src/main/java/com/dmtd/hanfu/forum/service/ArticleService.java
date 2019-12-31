package com.dmtd.hanfu.forum.service;

import com.dmtd.hanfu.forum.entity.Article;
import com.dmtd.hanfu.forum.entity.PageBean;

import java.sql.Timestamp;
import java.util.List;


public interface ArticleService {
	
	/**
	 * 获取帖子所有数据
	 * @return
	 */
	public List<Article> getArticleList();

	/**
	 * 根据id获取帖子数据
	 * @param aid 帖子id
	 * @return
	 */
	public Article getArticleByID(Integer aid);

	/**
	 * 发表新帖
	 * @param title
	 * @param content
	 * @param timestamp
	 * @param uid
	 * @param lable 
	 * @return
	 */
	public int addArticle(String title, String content,Timestamp timestamp,Integer uid, String lable,Integer type);

	/**
	 * 根据uid获得用户的帖子数据
	 * @param uid
	 * @return
	 */
	public List<Article> getArticleByUID(Integer uid);

	/**
	 * 根据id删除帖子数据
	 * @param aid
	 * @return
	 */
	public int deleteArticleByID(Integer aid);

	/**
	 * 分页查询帖子数据
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean getArticlePageList(int currentPage, int pageSize,int type);

	/**
	 * 关键字搜索
	 * @param key
	 * @return
	 */
	public List<Article> searchArticleByKey(String key);
	
	/**
	 * 通过staus关键字查询帖子
	 * @param statusList
	 * @return
	 */
	public List<Article> getArticleListByStatus(List<Integer> statusList);

}
