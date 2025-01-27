package com.kdigital.cookietest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SessionController {
	@GetMapping("/sessionSave")
	public String sessionSave(HttpSession session) {
		// 세션 객체 생성 불필요 - HttpSession 사용
		
		session.setAttribute("loginId", "hong");
		session.setAttribute("loginName", "홍길동");
		session.setAttribute("loginAge", 25);
		
		return "sessionResult";
	}
	
	@GetMapping("/sessionRead")
	public String sessionRead(HttpSession session) {
		// isNew() : Cookie base의 session 사용을 막음
		if(session.getAttributeNames().hasMoreElements()) {
			String id = (String)session.getAttribute("loginId");		// 반환 타입 - Object / 저장 타입 - 문자/숫자열 => 변환 
			String name = (String)session.getAttribute("loginName");
			int age = (Integer)session.getAttribute("loginAge");		// 다운캐스팅
			
			log.info("로그인 아이디: {}", id);
			log.info("로그인 이름: {}", name);
			log.info("로그인 나이: {}", age);
		} else {
			log.info("세션 정보가 저장되지 않았습니다.");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/sessionDel")
	public String sessionDel(HttpSession session) {
//		session.removeAttribute("loginId");		// 반환 타입 - Object / 저장 타입 - 문자/숫자열 => 변환 
//		session.removeAttribute("loginName");
//		session.removeAttribute("loginAge");		// 다운캐스팅
		
		session.invalidate();
		
		
		return "redirect:/";
	}
}
