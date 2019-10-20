package com.sunwin.dubbozkpdemo.controller;

import com.sunwin.dubbozkpdemo.dto.DemoDTO;
import com.sunwin.dubbozkpdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <br>
 * Copyright (c) 2019/9/19 All Rights Reserved By SUNWIN<br>
 * <br>
 * Package: com.sunwin.dubbozkpdemo.controller <br>
 * FileName: DemoController <br>
 * <br>
 *
 * @author duanmin
 * @created 2019/9/19 15:28
 * @last Modified
 * @history
 */
@RestController
public class DemoController {
    Logger logger = Logger.getLogger(DemoController.class.getName());

    @Autowired
    private DemoService demoService;

    @GetMapping("/getDemo")
    public DemoDTO getDemo(String name){
        logger.log(Level.INFO,"test message "+name);
        return demoService.test(name);
    }

    @GetMapping("/printHelloWorld")
    public DemoDTO printHelloWorld(){
        logger.log(Level.INFO,"print hello world ");
        return demoService.printHelloWorld();
    }
}
