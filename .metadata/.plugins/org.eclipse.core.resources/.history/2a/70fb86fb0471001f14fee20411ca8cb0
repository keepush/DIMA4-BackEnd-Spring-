package com.kdigital.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping({"/", ""})
	public String index(Model model) {
		String username = "자기이름을 넣으시오";
		
		model.addAttribute("username", username);
		return "index";
	}
}
