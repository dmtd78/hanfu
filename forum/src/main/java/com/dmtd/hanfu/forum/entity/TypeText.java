package com.dmtd.hanfu.forum.entity;

import java.io.Serializable;

/**
 * <br>
 * Copyright (c) 1/8/2020 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.entity <br>
 * FileName: TypeText <br>
 * <br>
 *
 * @author duanmin
 * @created 1/8/2020-1:59 PM
 * @last Modified
 * @history
 */
public class TypeText implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
