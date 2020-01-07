package com.dmtd.hanfu.forum.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 * Copyright (c) 2020/1/7 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.entity <br>
 * FileName: Collect.java <br>
 * <br>
 *收藏
 * @author duanmin
 * @created 2020/1/7-10:34 PM
 * @last Modified
 * @history
 */
public class Collect implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userId;
    private Integer collect;
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

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
