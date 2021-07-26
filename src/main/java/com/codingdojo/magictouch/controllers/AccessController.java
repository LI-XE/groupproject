package com.codingdojo.magictouch.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.magictouch.models.User;
import com.codingdojo.magictouch.services.UserService;
import com.codingdojo.magictouch.validators.UserValidator;



@Controller
public class AccessController {
	@Autowired
	private UserService UServ;
	@Autowired
	private UserValidator validator;
	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "register.jsp";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		return "register.jsp";
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute("user") User user) {
		return "/login.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return  "register.jsp";
		}
		
		User NewUser = this.UServ.RegisterUser(user);
		session.setAttribute("user_id", NewUser.getId());
		return "redirect:/Display";
	}
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirect, HttpSession session) {
		if (!this.UServ.authenticateUser(email, password)) {
			redirect.addFlashAttribute("loginError", "Invalid");
				return "redirect:/";
		}
		
		User user = this.UServ.FindByEmail(email);
		session.setAttribute("user_id", user.getId());
		return "redirect:/Display";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	
	
	

}



