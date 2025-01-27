package com.kdigital.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdigital.score.dto.ScoreDTO;
import com.kdigital.score.service.ScoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor // 생성자 주입 방식, setter 주입 방식, Annotation 주입 방식
@Slf4j
public class ScoreController {
	
	final ScoreService service;
	
	/**
	 * 성적 입력 화면 요청
	 * @return
	 */
	@GetMapping("/insert")
	public String insert() {
		return "InsertForm";
	}
	
	/**
	 * DB에 성적 등록 처리
	 * @param score
	 * @return
	 */
	@PostMapping("/insertForm")
	public String insertForm(ScoreDTO score) {
		service.insert(score);
		
		return "redirect:/";
	}
	
	/**
	 * 성적 조회 위해 전체 성적 DB를 list로 전송
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model) {
			
		List<ScoreDTO> list = service.list();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	/**
	 * 특정 학번의 성적 삭제 처리
	 * @param stid
	 * @return
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("stid") String stid) {
		service.delete(stid);
		
		return "redirect:/list";
	}

	
	/**
	 * 수정 화면으로 이동
	 * @return
	 */
	@GetMapping("/update")
	public String update() {
		
		return "updateForm";
	}
	
}
