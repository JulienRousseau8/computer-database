package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.MyUserDTO;
import com.excilys.service.UserService;

@Controller
@RequestMapping(value = "")
public class LoginController {

	@Autowired
	UserService userService;
	
	@GetMapping(value = "/login")
	public ModelAndView loginPage() {
		ModelAndView modelAndView = new ModelAndView("login");
		
		return modelAndView;
	}
	
	@GetMapping(value = "/register")
	public ModelAndView registerPage() {
		ModelAndView modelAndView = new ModelAndView("register");
		
		return modelAndView;
	}
	
	@PostMapping(value = "/register")
	public ModelAndView createUser(MyUserDTO user) {
		ModelAndView modelAndView = new ModelAndView("redirect:/login");
		userService.createUser(user);
		return modelAndView;
	}

}
