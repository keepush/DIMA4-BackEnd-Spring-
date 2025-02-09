package com.kdigital.spring7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdigital.spring7.dto.UserDTO;
import com.kdigital.spring7.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	final UserService userService;
	
	/**
	 * 회원 가입을 위한 화면 요청
	 * @return
	 */
	@GetMapping("/join")
	public String join() {
		
		return "user/join";
	}
	
	/**
	 * 회원가입 처리
	 * @return
	 */
	@PostMapping("/join")
	public String join(
		@ModelAttribute UserDTO userDTO
			) {
		log.info("UserDTO: {}", userDTO.toString());
		
		userDTO.setRoles("ROLE_USER");
		userDTO.setEnabled(true);
		
		boolean result = userService.join(userDTO);
		log.info("회원가입 성공: {}", result);
		
		return "redirect:/";
	}
	
	/**
	 * 회원 가입 시 사용가능한 아이디인지 비동기를 이용해 처리
	 * @param userId
	 * @return
	 */
	@PostMapping("/confirmId")
	@ResponseBody
	public boolean confirmId(@RequestParam(name="userId") String userId) {
		log.info("회원 가입 아이디: {}", userId);
		
		return !userService.existId(userId);  // 존재하면 true, 아이디가 없으면 false
	}
	
	/**
	 * 로그인 실패 시 처리 화면
	 * @param error
	 * @param errMessage
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String login(
			@RequestParam(value="error", required=false) String error 
			, @RequestParam(value="errMessage", required=false) String errMessage 
			, Model model
			) {
		
		System.out.println(error);
		System.out.println(errMessage);
		
		model.addAttribute("error", error);
		model.addAttribute("errMessage", errMessage);
		
		return "user/login";
	}
	
	/**
	 * 개인 정보 수정을 위한 요청
	 * 비밀번호를 한 번 더 입력하는 페이지로 Forwarding
	 * @return
	 */
	@GetMapping("/mypage")
	public String mypage() {
		return "user/pwdCheck";
	}
	
	
	@PostMapping("/pwdCheck")
	public String pwdCheck(
			@RequestParam(name="userId") String userId,
			@RequestParam(name="userPwd") String userPwd,
			Model model
			) {
		
		log.info("id: {}, pwd: {}", userId, userPwd);
		UserDTO userDTO = userService.pwdCheck(userId, userPwd);			// userDTO에 받은 userId와 userPwd 저장
		
		if(userDTO != null) { // 개인 정보 수정 화면으로 이동
			model.addAttribute("userDTO", userDTO);
			
			return "user/myInfoUpdate";
		} 

		return "redirect:/";
		
	}
	
	/**
	 * 수정 처리 요청
	 * @return
	 */
	@PostMapping("/update")
	public String update(
			@ModelAttribute UserDTO userDTO
			) {
		
		log.info("==== {}", userDTO.toString());
		
		boolean result = userService.update(userDTO);
		
		if(result) {
			return "redirect:/user/logout";		// 개인 정보 수정 시 로그아웃 실시 - secutiryConfig에 설정돼 있음
		}
		
		return "redirect:/";	// ???
	}
}




