package com.sunwin.dubbozkpdemo.service;

import com.sunwin.dubbozkpdemo.dto.DemoDTO;

/**
 * <br>
 * Copyright (c) 2019/9/18 All Rights Reserved By SUNWIN<br>
 * <br>
 * Package: com.sunwin.dubbozkpdemo.service <br>
 * FileName: DemoService <br>
 * <br>
 *
 * @author duanmin
 * @created 2019/9/18 18:13
 * @last Modified
 * @history
 */
public interface DemoService {
    public DemoDTO test(String name);

    public DemoDTO printHelloWorld();
}
