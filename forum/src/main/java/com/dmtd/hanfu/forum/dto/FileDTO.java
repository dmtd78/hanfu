package com.dmtd.hanfu.forum.dto;

import java.io.Serializable;

/**
 * Copyright (c) 3/11/2020 All Rights Reserved By dmtd
 * <p>
 * Package: com.dmtd.hanfu.forum.dto
 * FileName: FileDTO
 *
 * @author duanmin
 * @description
 * @created 3/11/2020-4:47 PM
 * @last Modified
 * @history
 */
public class FileDTO implements Serializable {
    private String group;
    private String path;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
