package com.kdigital.Test3.DTO;

import java.time.LocalDate;

import com.kdigital.Test3.entity.FitnessEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class FitnessDTO {
	private Integer seq;
	private String name;
	private boolean gender;
	private Double height;
	private Double weight;
	private LocalDate joinDate;
	private Double stdWeight;
	private Double bmi;
	private String bmiResult;
	
	// ENTITY를 전달받아 DTO로 반환하는 메소드
	
	public static FitnessDTO toDTO (FitnessEntity entity) {
		return FitnessDTO.builder()
				.seq(entity.getSeq())
				.name(entity.getName())
				.gender(entity.isGender())
				.height(entity.getHeight())
				.weight(entity.getWeight())
				.joinDate(entity.getJoinDate())
				.stdWeight(entity.getStdWeight())
				.bmi(entity.getBmi())
				.bmiResult(entity.getBmiResult())
				.build();
	}
	
	public void setHeight(double height) {
		this.height = height;
		calcStdWeight();
		calcBMI();
	}

	public void setWeight(double weight) {
		this.weight = weight;
		calcStdWeight();
		calcBMI();
	}
	private void calcStdWeight() {
		double temp = height / 100;   // m로 환산된 키

		if(gender==true) {  // 남자 true 1 여자 false 0
			stdWeight = temp * temp * 22;	
		} else {
			stdWeight = temp * temp * 21;	
		}
	}
	
	private void calcBMI() {
		double temp = height / 100;   // m로 환산된 키
		
		bmi = weight / (temp * temp);
		calcBmiResult();
	}

	private void calcBmiResult() {
		// BMI 결과 처리
		if(bmi >= 35) 		bmiResult = "고도 비만";
		else if(bmi >= 30) 	bmiResult = "중(重)도 비만 (2단계 비만)";
		else if(bmi >= 25) 	bmiResult = "경도 비만 (1단계 비만)";
		else if(bmi >= 23) 	bmiResult = "과체중";
		else if(bmi >= 18.5)bmiResult = "정상";
		else 				bmiResult = "저체중";
	}


}
