package org.spring.restcontrollerex.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/join")
    public String join(){
        return "member/join";
    }
}
