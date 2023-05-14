package com.api.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.project.service.UserService;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private UserService userService;
	
    @GetMapping("/login")
    public String getLogin() {
        return "login";
//    	return "Hello World!";
    }
}