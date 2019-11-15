package com.dmtd.hanfu.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    @ResponseBody
    public String homePage(Model model) {

        return "欢迎来到大美汉服网站！";
    }
}
