package com.dmtd.hanfu.forum.service;

import com.dmtd.hanfu.forum.entity.ThumbsUp;

/**
 * <br>
 * Copyright (c) 2020/1/9 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.service <br>
 * FileName: ThumbsUpService.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/9-10:11 PM
 * @last Modified
 * @history
 */
public interface ThumbsUpService {
    void addThumbsUpByUserIdAndArticleId(Integer articleId, Integer userId);

    ThumbsUp getThumbsUpByUserIdAndArticleId(Integer articleId, Integer userId);
}
