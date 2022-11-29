package com.homepage.home.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class redirectorController {
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {

        return "index";
    }

    @GetMapping(value = "/main")
    public String mainPage(){
        return "main";
    }
    @GetMapping(value = "/access-denied")
    public ResponseEntity<String> accessDenied(){
        return new ResponseEntity<>("access-denial", HttpStatus.OK);
    }
}
