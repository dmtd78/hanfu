package com.dmtd.hanfu.forum.dto;

import com.dmtd.hanfu.forum.entity.Integral;

/**
 * <br>
 * Copyright (c) 1/22/2020 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.dto <br>
 * FileName: IntegralDto <br>
 * <br>
 *
 * @author duanmin
 * @created 1/22/2020-4:17 PM
 * @last Modified
 * @history
 */
public class IntegralDto extends Integral {
    private Integer signDay;

    public Integer getSignDay() {
        return signDay;
    }

    public void setSignDay(Integer signDay) {
        this.signDay = signDay;
    }
}
