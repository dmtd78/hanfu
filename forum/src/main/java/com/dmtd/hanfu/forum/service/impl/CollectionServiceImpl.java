package com.dmtd.hanfu.forum.service.impl;

import com.dmtd.hanfu.forum.dao.CollectionDao;
import com.dmtd.hanfu.forum.entity.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dmtd.hanfu.forum.service.CollectionService;

import java.util.List;

/**
 * <br>
 * Copyright (c) 2020/1/7 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.service.impl <br>
 * FileName: CollectServiceImpl.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/7-10:42 PM
 * @last Modified
 * @history
 */
@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionDao collectionDao;

    @Override
    public Collect getCollectByUserId(Integer userId) {
        return collectionDao.getCollectByUserId(userId);
    }

    @Override
    public List<Collect> getCollectListByUserId(Integer userId) {
        return collectionDao.getCollectListByUserId(userId);
    }

    @Override
    public void addCollectByUserIdAndArticleId(Integer articleId, Integer userId) {
        //首先根据用户id和帖子id看是否已收藏。
        Collect hasCollect = collectionDao.getCollectByUserIdAndArticleId(articleId, userId);
        //如果未收藏则添加，已收藏则忽略
        if (hasCollect == null) {
            collectionDao.addCollectByUserId(articleId,userId);
        }
    }

    @Override
    public int deleteCollectByArticleId(Integer articleId, Integer userId) {
        return collectionDao.deleteCollectByArticleId(articleId, userId);
    }
}
