package com.kdigital.spring6.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdigital.spring6.dto.Friend;
import com.kdigital.spring6.service.FriendService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller 
@RequiredArgsConstructor // 생성자 주입 방식, setter 주입 방식, Annotation 주입 방식
@Slf4j
public class FriendController {
	
	final FriendService service; // Bean(Spring이 관리하는) 객체를 주입 받음
	
	/**
	 * 화면을 요청
	 * @return
	 */
	@GetMapping("/insertFriend")
	public String insertFriend() {
		return "insertFriend";
	}
	
	/**
	 * 
	 * @param friend
	 * @return
	 */
	@PostMapping("/insertFriend")
	public String insertFriend(
			@ModelAttribute Friend friend
			) {
		
		// 저장할 수 있도록 service에 데이터를 넘김
		log.info("Controller");
		
		service.insert(friend);
		
		return "redirect:/";	// 브라우저한테 / 요청을 함	==> redirect
	}
	
	@GetMapping("/listFriend")
	public String listFriend(Model model) {
		
		List<Friend> list = service.list();
		model.addAttribute("list", list);
		
		return "listFriend";
	}
	
	@GetMapping("/deleteOne")
	public String deleteFriend(@RequestParam(name="fseq") Integer fseq) {
		log.info("전달된 번호:{} ==> ", fseq);
		
		service.deleteOne(fseq);
		
		return("redirect:/listFriend"); // get 요청
	}
	
	/**
	 * 데이터를 수정하기 전 수정할 데이터를 DB에서 조회하는 기능
	 * @param fseq
	 * @return
	 */
	@GetMapping("/updateOne")
	public String updateFriend(@RequestParam(name="fseq") Integer fseq, Model model) {
		log.info("전달된 번호:{} ==> ", fseq);
		
		Friend friend = service.selectOne(fseq);
		model.addAttribute("friend", friend);
		return "updateFriend";
	}
	/**
	 * 전달받은 데이터를 수정하기 위한 요청(DB까지 전잘해야 함)
	 * @param fseq
	 * @param model
	 * @return
	 */
	@PostMapping("/updateOne")
	public String updateFriend(@ModelAttribute Friend friend) {
		
		service.updateOne(friend);
		
		return "redirect:/listFriend"; // 브라우저에게 목록을 다시 요청하도록 지시
	}
	
}
