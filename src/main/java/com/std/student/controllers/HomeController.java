package com.std.student.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "HomePage/Home";
    }

    @GetMapping("/login")
    public String login() {
        return "HomePage/Login";
    }


}

