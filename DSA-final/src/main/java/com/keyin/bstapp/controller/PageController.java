package com.keyin.bstapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/","/enter-numbers","/previous-trees-page","/app"})
    public String app(){
        return "index";
    }
}
