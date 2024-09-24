package com.kdigital.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping({"/", ""})
	public String index(Model model) {
		String username = "유지민";
		
		model.addAttribute("username", username);
		return "index";
	}
	
}
