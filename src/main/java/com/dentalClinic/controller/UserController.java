package com.dentalClinic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/home")
    public String home(){
        return "<h1> Welcome </h1>";
    }

    @GetMapping("/users/user")
    public String user(){
        return "<h1> Welcome User </h1>";
    }

    @GetMapping("/users/admin")
    public String admin(){
        return "<h1> Welcome Admin </h1>";
    }

}
