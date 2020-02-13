package com.dmtd.hanfu.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.dmtd.hanfu.dto.DemoDTO;
import com.dmtd.hanfu.service.DemoService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <br>
 * Copyright (c) 2019/9/18 All Rights Reserved By SUNWIN<br>
 * <br>
 * Package: com.sunwin.dubbozkpdemo.service.impl <br>
 * FileName: DemoServiceImpl <br>
 * <br>
 *
 * @author duanmin
 * @created 2019/9/18 18:13
 * @last Modified
 * @history
 */
@Service
public class DemoServiceImpl implements DemoService {
    Logger logger = Logger.getLogger(DemoServiceImpl.class.getName());
    @Override
    public DemoDTO test(String name) {
        DemoDTO demoDTO = new DemoDTO(name);
        logger.log(Level.INFO,"name:{0}", demoDTO.getName());
        return demoDTO;
    }

    @Override
    public DemoDTO printHelloWorld() {
        return new DemoDTO("Hello World!!");
    }
}
