package com.api.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.project.model.Uploader;
import com.api.project.service.UploaderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class UploaderController {
	@Autowired
	private UploaderService uploaderService;
	
	// Redirected to sign in page
	@GetMapping("")
	public String redirectToSignIn() {
		return "redirect:/sign-in";
	}

	// Show sign in page
    @GetMapping("/sign-in")
    public String signInPage(Model model) {
    	model.addAttribute("uploader", new Uploader());
        return "sign-in";
    }
    
    // Authenticate users
    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute("uploader") Uploader uploader, BindingResult bindingResult, HttpSession session, Model model) {
    	Uploader registeredUploader = uploaderService.findUploaderByUsername(uploader.getUsername());
    	if (registeredUploader != null && registeredUploader.getPassword().equals(uploader.getPassword())) {
    		session.setAttribute("registeredUploader", registeredUploader);
    		return "redirect:/uploader/my-films";
    	} else {
    		model.addAttribute("errorMessage", "Invalid username or password. Try again.");
    		return "sign-in";
    	}
    }

    // Sign out
    @GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        session.removeAttribute("registeredUploader");
        return "redirect:/sign-in";
    }
}