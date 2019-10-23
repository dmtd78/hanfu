package com.dmtd.hanfu.forum.config;

/**
 * @author LJC 存储静态数据
 */
public class Config {

	/**
	 * 默认头像地址
	 */
	public static final String DEFAULT_HEADIMG_ADDRESS = "/resources/imgs/head.png";

	/**
	 * 默认文件上传地址
	 */
	public static final String DEFAULT_UPLOAD_ADDRESS = "/resources/upload";

	/**
	 * 默认分页大小
	 */
	public static final int DEFAULT_PAGESIZE = 20;

	/**
	 * 精品帖status
	 */
	public static final int STATUS_HOT = 1;
	/**
	 * 置顶帖status
	 */
	public static final int STATUS_TOP = 2;

	/**
	 * 置顶且加精品status
	 */
	public static final int STATUS_TOP_HOT = 3;

}
