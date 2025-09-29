package com.luv2code.springboot.demo.myCoolApp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // expose "/" that returns "Hello world!"

    @GetMapping("/")
    public String sayHello(){
        return coachName;
    }

    @Value("${coach.name}")
    private String coachName;
}
