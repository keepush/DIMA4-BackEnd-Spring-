package com.kdigital.test2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kdigital.test2.dto.Fitness;

@Controller
public class FitnessController {
	@PostMapping("/fitness")
	public String BmiResult(
			@ModelAttribute Fitness fitness,
			Model model
			) {
		
			fitness.calcBMI();
			fitness.calcstdWeight();
			fitness.calcBmiResult();
			
			model.addAttribute("fitness", fitness);
			
			return "BmiResult";
			
	}
}
