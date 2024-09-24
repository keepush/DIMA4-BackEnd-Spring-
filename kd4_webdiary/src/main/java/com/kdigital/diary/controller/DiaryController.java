package com.kdigital.diary.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdigital.diary.dto.Diary;
import com.kdigital.diary.service.DiaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class DiaryController {
	
	final DiaryService service; // Bean(Spring이 관리하는) 객체를 주입 받음
	
	@GetMapping("/diary")
	public String diary(Model model){
		String username = "유지민";
		model.addAttribute("username", username);
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
		
		model.addAttribute("today", formattedDate);
		
		// 일기 목록 가져오기
	    List<Diary> list = service.list();
	    model.addAttribute("list", list);
		
		return "diaryForm";
	}
	
	@PostMapping("/diarywrite")
	public String diarywrite(@ModelAttribute Diary diary) {
		log.info("Diary submitted: {}", diary);
		
//		diary.setRegdate(LocalDate.now());
	    
		service.insert(diary);
		
		return "redirect:/diary";
	}
	
	
	@GetMapping("/deleteOne")
	public String deleteFriend(@RequestParam(name="diary_seq") Integer diary_seq) {
		
		service.deleteOne(diary_seq);
		
		return "redirect:/diary"; // get 요청
	}
	
}

