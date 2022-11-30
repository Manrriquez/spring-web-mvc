package com.registerPerson.controllers;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EntityScan(basePackages = "com.registerPerson.models")
public class PersonController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
