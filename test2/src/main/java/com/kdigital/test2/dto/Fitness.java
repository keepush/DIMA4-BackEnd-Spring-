package com.kdigital.test2.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Fitness {
	private String username;
	private String sex;
	private double height;
	private double weight;
	private double stdWeight;
	private double bmi;
	private String BmiResult;


public void calcstdWeight() {
	double temp = height / 100;
	if(sex.equals("남")) {
		stdWeight = temp * temp * 22;
	} else {
		stdWeight = temp * temp * 21;
	}
}

public void calcBMI() {
	double temp = height / 100;
	bmi = weight / (temp * temp);
	}

public void calcBmiResult() {
	if (bmi >= 35) {
		BmiResult = "고도 비만";
	} else if (bmi >= 30 ) {
		BmiResult = "중도 비만 (2단계 비만)";
	} else if (bmi >= 25) {
		BmiResult = "경도 비만 (1단계 비만)";
	} else if (bmi >= 23 ) {
		BmiResult = "과체중";
	} else if (bmi >= 18.5) {
		BmiResult  = "정상";
	} else {
		BmiResult = "저체중";
	}
 }
}


