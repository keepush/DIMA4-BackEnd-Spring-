package com.kdigital.spring1.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContoller {

	@GetMapping({"", "/"})
	public String index(){
		System.out.println("첫 요청이 도착함!");
		// 뭔가 작업을 함!
		return "index";		// src/main/resources/templetes/index.html
	}
}