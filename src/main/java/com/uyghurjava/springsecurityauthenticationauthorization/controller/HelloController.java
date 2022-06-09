package com.uyghurjava.springsecurityauthenticationauthorization.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String welcome(){
        return "Accueil";
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

    @RequestMapping("/adminPage")
    @ResponseBody
    public String adminPage(){
        return "Welcome to admin page !";
    }

    @RequestMapping("/employeePage")
    @ResponseBody
    public String employeePage(){
        return "Welcome to employee page !";
    }

    @RequestMapping("/managerPage")
    @ResponseBody
    public String managerPage(){
        return "Welcome to manager page !";
    }


    //Retourner la page SansAutorisation.html quand la permission n'est pas remplie
    @RequestMapping("/SansAutorisation")
    public String sansAuth(){
        return "SansAutorisation";
    }


}
