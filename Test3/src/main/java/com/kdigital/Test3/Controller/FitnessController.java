package com.kdigital.Test3.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdigital.Test3.DTO.FitnessDTO;
import com.kdigital.Test3.service.FitnessService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor //생성자 주입방식, setter 주입방식, annotation 주입방식
public class FitnessController {
	
	final FitnessService service; //주입받음 injection = IOC, bean(spring이 관리하는)객체를 주입받음
	//autowired 쓰지마셈
	
	
	//정보저장을 위한 화면을 요청 
	@GetMapping("/insertFitness")
	public String insertFitness() {
		return "insertFitness";
	}
	@PostMapping("/insertFitness")
	public String insertFitness(
			@ModelAttribute FitnessDTO fitness
			){
	// 저장할 수 있도록 service에 데이터를 넘김
		log.info("Controller");
		service.insertMember(fitness);
		
		return "redirect:/"; // 브라우저 한테 / 요청을 하도록 함 =>redirect
	}
	// 친구목록 요청 db에서 데이터 목록을 가져와야 함
	@GetMapping("/listFitness")
	public String listFitnes(Model model) {
		
		List<FitnessDTO> list = service.list();
		model.addAttribute("list",list);
		return"listFitness";
	}
	// 파라미터로 전송받은 fseq값을 이용해 db에서 데이터를 삭제하도록 전달함
	@GetMapping("/deleteMember")
	public String deleteFitness(@RequestParam(name="fseq") Integer fseq) {
		log.info("전달된 번호 : {} ==> ",fseq);
		service.deleteMember(fseq);
		return("redirect:/listFitness"); // redirect는 get요청이다!
		
	}
	// 데이터 수정하기 전 수정할 데이터를 db에서 조회하는 기능
	@GetMapping("/updateMember")
	public String updateFriend(@RequestParam(name="fseq") Integer fseq, Model model) {
		log.info("전달된 번호 : {} ==> ",fseq);
		FitnessDTO fitness = service.selectMember(fseq);
		model.addAttribute("fitness",fitness); // 데이터 집어넣기
		return("updateFitness"); 		
		
	}
	// 전달받은 데이터를 수정하기 위한 요청 DB단까지 전달해야함
	@PostMapping("/updateMemeber")
	public String updateFitness(@ModelAttribute FitnessDTO fitness) {
		
		service.updateMember(fitness);
		
		return("redirect:/listFitness"); // 브라우저에게 목록을 다시 요청하도록 지시 		
		
	}
	
	
}