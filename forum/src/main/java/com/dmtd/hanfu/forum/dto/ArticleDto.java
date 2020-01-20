package com.dmtd.hanfu.forum.dto;

import com.dmtd.hanfu.forum.entity.Article;

/**
 * <br>
 * Copyright (c) 1/20/2020 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.dto <br>
 * FileName: ArticleDto <br>
 * <br>
 *
 * @author duanmin
 * @created 1/20/2020-11:27 AM
 * @last Modified
 * @history
 */
public class ArticleDto extends Article {
    private Integer isBigContent;

    public Integer getIsBigContent() {
        return isBigContent;
    }

    public void setIsBigContent(Integer isBigContent) {
        this.isBigContent = isBigContent;
    }
}
