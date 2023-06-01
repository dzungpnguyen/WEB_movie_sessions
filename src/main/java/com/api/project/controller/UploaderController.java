package com.api.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.project.model.Film;
import com.api.project.model.Uploader;
import com.api.project.service.UploaderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class UploaderController {
	@Autowired
	private UploaderService uploaderService;
	
	@GetMapping("")
	public String redirectToSignIn() {
		return "redirect:/sign-in";
	}

    @GetMapping("/sign-in")
    public String signInPage(Model model) {
    	model.addAttribute("uploader", new Uploader());
        return "sign-in";
    }
    
    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("uploader") Uploader uploader, BindingResult bindingResult, HttpSession session, Model model) {
    	Uploader registeredUploader = uploaderService.findUploaderByUsername(uploader.getUsername());
    	if (registeredUploader != null && registeredUploader.getPassword().equals(uploader.getPassword())) {
    		session.setAttribute("registeredUploader", registeredUploader);
    		return "redirect:/uploader/my-films";
    	} else {
    		// handle wrong username and password
//    		bindingResult.rejectValue("username", "error.uploader", "Invalid username or password. Try again.");
    		model.addAttribute("errorMessage", "Invalid username or password. Try again.");
    		return "sign-in";
    	}
    }
    
    @PostMapping("/test-sign-in")
    public String testSignIn(@ModelAttribute("uploader") Uploader uploader) {
    	return "hello-world";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
//        User user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            session.setAttribute("user", user);
//            return "redirect:/films";
//        } else {
//            return "login";
//        }
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.removeAttribute("user");
//        return "redirect:/login";
//    }
}