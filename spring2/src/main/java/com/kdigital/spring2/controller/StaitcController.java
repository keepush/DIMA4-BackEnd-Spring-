package com.kdigital.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaitcController {

	@GetMapping("/image")		// localhost:9000/image   받을 때는 / 붙여야 함
	public String image() {
		return "image";
	}
	
	@GetMapping("/css")		
	public String css() {
		return "css";
	}
	
	@GetMapping("/javascript")
	public String javascript() {
		return "javascript";
	}
}
