package com.dmtd.hanfu.forum.dao;

import com.dmtd.hanfu.forum.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <br>
 * Copyright (c) 2020/1/7 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.dao <br>
 * FileName: CollectDao.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/7-10:38 PM
 * @last Modified
 * @history
 */
@Mapper
@Component(value = "collectionDao")
public interface CollectionDao {

    public Collect getCollectByUserId(@Param("userId")Integer userId);

    List<Collect> getCollectListByUserId(@Param("userId")Integer userId);

    void addCollectByUserId(@Param("articleId")Integer articleId, @Param("userId")Integer userId);

    Collect getCollectByUserIdAndArticleId(@Param("articleId")Integer articleId, @Param("userId")Integer userId);
}
