package com.dmtd.hanfu.forum.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 积分
 */

public class Integral implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String userId;
	private Integer integral;
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
