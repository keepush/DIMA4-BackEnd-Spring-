package com.kdigital.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParamController {
	
	@GetMapping("/param/send")
	public String param(
			@RequestParam(name="name", defaultValue="홍길동") String name, 
			@RequestParam(name="age", defaultValue="20") int age
			) {
		System.out.println("name=" + name + ", age=" + age);
		
		return "result";
	}
	
	@GetMapping("/param/sendForm")
	public String sendForm(
			@RequestParam(name="userid", defaultValue="abc") String id,
			@RequestParam(name="userpwd", defaultValue="korea") String userpwd
			) {
		System.out.println("userid= " + id + ", userpwd= " + userpwd);
		
		return "result";
	}
	
	@PostMapping("/param/sendForm")
	public String sendForm2(
			@RequestParam(name="userid", defaultValue="abc") String userid,
			@RequestParam(name="userpwd", defaultValue="korea") String userpwd,
			Model model
			) {
		System.out.println("userid= " + userid + ", userpwd= " + userpwd);
		
		model.addAttribute("id", userid);
		model.addAttribute("pwd", userpwd);
		
		return "recieve";		// forward 방식
	}
}
