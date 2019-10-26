package com.dmtd.hanfu.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "/home")
public class HomeController {

    @GetMapping(value = "/")
    public String homePage() {
        return "index";
    }
}
