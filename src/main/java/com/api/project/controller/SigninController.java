package com.api.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.project.service.UploaderService;

@Controller
@RequestMapping("")
public class SigninController {
	@Autowired
	private UploaderService userService;
	
    @GetMapping("/signin")
    public String getSignin() {
        return "signin";
//    	return "Hello World!";
    }
}