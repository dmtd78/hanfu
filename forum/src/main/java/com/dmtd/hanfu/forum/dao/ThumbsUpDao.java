package com.dmtd.hanfu.forum.dao;

import com.dmtd.hanfu.forum.entity.ThumbsUp;
import com.dmtd.hanfu.forum.entity.TypeText;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <br>
 * Copyright (c) 2020/1/9 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.dao <br>
 * FileName: ThumbsUpDao.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/9-10:10 PM
 * @last Modified
 * @history
 */
@Mapper
@Component(value = "thumbsUpDao")
public interface ThumbsUpDao {

    TypeText getTypeTextByArticleId(@Param("articleId")Integer articleId);

    ThumbsUp getThumbsUpByUserIdAndArticleId(Integer articleId, Integer userId);

    void addThumbsUpByUserId(Integer articleId, Integer userId);
}
