package com.dmtd.hanfu.forum.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br>
 * Copyright (c) 2020/1/10 All Rights Reserved By dmtd<br>
 * <br>
 * Package: com.dmtd.hanfu.forum.util <br>
 * FileName: TimeUtils.java <br>
 * <br>
 *
 * @author duanmin
 * @created 2020/1/10-9:51 PM
 * @last Modified
 * @history
 */
public class TimeUtils {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";


    /**
     * 根据格式返回字符串时间
     * @param date
     * @param format
     * @return
     */
    public static String getDate(Date date, String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return format1.format(date);
    }
}
