package com.dmtd.hanfu.forum.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 * Copyright (c) 2020/1/9 All Rights Reserved By dmtd<br>
 * <br>点赞
 * Package: com.dmtd.hanfu.forum.entity <br>
 * FileName: ThumbsUp.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/9-10:08 PM
 * @last Modified
 * @history
 */
public class ThumbsUp implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userId;
    private String articleId;
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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
