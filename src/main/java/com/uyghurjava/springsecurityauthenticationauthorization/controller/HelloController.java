package com.uyghurjava.springsecurityauthenticationauthorization.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String welcome(){
        return "Welcome ! Spring Security";
    }

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "loginPage";
    }

    @RequestMapping("/fail")
    @ResponseBody
    public String fail(){
        return "Bad Credential";
    }
}
