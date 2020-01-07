package com.dmtd.hanfu.forum.service;

import com.dmtd.hanfu.forum.entity.Collect;

import java.util.List;

/**
 * <br>
 * Copyright (c) 2020/1/7 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.service <br>
 * FileName: CollectService.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/7-10:40 PM
 * @last Modified
 * @history
 */
public interface CollectService {

    public Collect getCollectByUserId(Integer userId);

    List<Collect> getCollectListByUserId(Integer userId);

    void addCollectByUserIdAndArticleId(Integer articleId, Integer userId);
}
