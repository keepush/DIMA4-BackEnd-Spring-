package com.kdigital.ajaxtest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdigital.ajaxtest.dto.CustomerDTO;

@Controller
public class SecondController {
	@GetMapping("/secondpage")
	public String secondpage() {
		return "second";
	}
	
	@GetMapping("/receive")
	@ResponseBody
	public List<CustomerDTO> receive() {
	
		List<CustomerDTO> list = Arrays.asList(
				new CustomerDTO("조민근", "sosad@mad.scientist", "010-4444-4444")
				, new CustomerDTO("조인영", "angry@too.warning", "010-4444-8282")
				, new CustomerDTO("조유정", "justired@sleep.plz", "010-5353-9999")
				, new CustomerDTO("유지민", "sireo@sireo.fuck", "010-1111-2222")
				, new CustomerDTO("백수", "want@really.alot", "010-1000-1000")
				);
		return list;
	}
}
