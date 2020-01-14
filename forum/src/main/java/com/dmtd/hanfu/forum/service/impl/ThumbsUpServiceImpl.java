package com.dmtd.hanfu.forum.service.impl;

import com.dmtd.hanfu.forum.dao.ThumbsUpDao;
import com.dmtd.hanfu.forum.entity.ThumbsUp;
import com.dmtd.hanfu.forum.service.ThumbsUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>
 * Copyright (c) 2020/1/9 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.service.impl <br>
 * FileName: ThumbsUpServiceImpl.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/9-10:12 PM
 * @last Modified
 * @history
 */
@Service("thumbsUpService")
public class ThumbsUpServiceImpl implements ThumbsUpService {
    @Autowired
    ThumbsUpDao thumbsUpDao;

    @Override
    public void addThumbsUpByUserIdAndArticleId(Integer articleId, Integer userId) {
        //首先根据用户id和帖子id看是否已点赞。
        ThumbsUp hasThumbsUp = thumbsUpDao.getThumbsUpByUserIdAndArticleId(articleId, userId);
        //如果未点赞则添加，已点赞则忽略
        if (hasThumbsUp == null) {
            thumbsUpDao.addThumbsUpByUserId(articleId, userId);
        }
    }

    @Override
    public ThumbsUp getThumbsUpByUserIdAndArticleId(Integer articleId, Integer userId) {
        //首先根据用户id和帖子id看是否已点赞。
        ThumbsUp hasThumbsUp = thumbsUpDao.getThumbsUpByUserIdAndArticleId(articleId, userId);
        return hasThumbsUp;
    }
}
