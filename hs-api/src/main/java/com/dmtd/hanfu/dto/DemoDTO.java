package com.dmtd.hanfu.dto;

import java.io.Serializable;

/**
 * <br>
 * Copyright (c) 2019/9/19 All Rights Reserved By SUNWIN<br>
 * <br>
 * Package: com.sunwin.dubbozkpdemo.dto <br>
 * FileName: DemoDTO <br>
 * <br>
 *
 * @author duanmin
 * @created 2019/9/19 15:32
 * @last Modified
 * @history
 */
public class DemoDTO implements Serializable {
    private static final long serialVersionUID = -6878053406541100993L;

    public DemoDTO(String name){
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
