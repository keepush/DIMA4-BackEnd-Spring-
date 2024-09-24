package com.kdigital.spring5.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdigital.spring5.dto.Friend;

@Controller
public class ThymeleafController {
	@GetMapping("/display/text")
	public String text(Model model) {
		// 문자 출력을 위한 데이터 준비
		String korean = "대한민국 ~~ *♥";
		String english = "Hello, everyone!!!";
		String tag = "<marquee>돌이 굴러가유~</marquee>";
		
		// 숫자 출력을 위한 데이터 준비
		int age = 30;
		double pi = Math.PI;
		
		// URL 출력을 위한 데이터 준비
		String url="https://naver.com";
		
		// 빈 데이터와 NULL 데이터 준비
		String nullData = null;
		String emptyData = "";
		
		model.addAttribute("korean", korean);
		model.addAttribute("english", english);
		model.addAttribute("tag", tag);
		
		model.addAttribute("age", age);
		model.addAttribute("pi", pi);
		model.addAttribute("url", url);
		
		model.addAttribute("nullData", nullData);
		model.addAttribute("emptyData", emptyData);
		
		return "thyme_text";
	}
	
	@GetMapping("/display/condition")
	public String condition(Model model) {
		// 일반 객체
		Friend friend = new Friend("홍길동", 25, "010-1234-1111", LocalDate.of(2003, 12, 5), true);
		
		
		// Iterable 데이터
		List<String> list = Arrays.asList("사과", "배", "딸기", "복숭아", "포도", "오렌지");
		
		List<Integer> nList = new ArrayList<>();
		for(int i=1; i<=20; ++i) 
			nList.add(i*3);
		
		// Map 데이터
		Map<Integer, Friend> map = new HashMap<>();
		map.put(10, new Friend("손오공", 25, "010", LocalDate.of(2000, 12, 25), true));
		map.put(20, new Friend("저팔계", 30, "011", LocalDate.of(1994, 10, 1), false));
		map.put(30, new Friend("사오정", 21, "019", LocalDate.of(2003, 1, 5), true));
		
		model.addAttribute("friend", friend);
		model.addAttribute("list", list);
		model.addAttribute("nList", nList);
		model.addAttribute("map", map);
		
		return "thyme_condition";
	}
	
	@GetMapping("/display/receive")
	public String receive(
			@RequestParam(name="name", defaultValue="모모") String name,
			@RequestParam(name="age", defaultValue="23") int age
			) {
		System.out.println(name+", " + age);
		
		return "index";
	}
	
}
