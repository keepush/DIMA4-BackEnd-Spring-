package com.kdigital.Test3.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.kdigital.Test3.DTO.FitnessDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Builder



@Table(name="fitness")
public class FitnessEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq")
	private Integer seq;
	@Column(name="name")
	private String name;
	@Column(name="gender")
	private boolean gender;
	@Column(name="height")
	private Double height;
	@Column(name="weight")
	private Double weight;
	
	@Column(name="join_date")
	@CreationTimestamp //회원의 정보가 처음 생성 될때 자동으로 날짜 세팅
	private LocalDate joinDate;
	@Column(name="stdWeight")
	private Double stdWeight;
	@Column(name="bmi")
	private Double bmi;
	@Column(name="bmiResult")
	private String bmiResult;
	
	// DTO를 전달받아 ENTITY로 반환하는 메소드
	public static FitnessEntity toEntity (FitnessDTO fitness) {
		return FitnessEntity.builder()
				.seq(fitness.getSeq())
				.name(fitness.getName())
				.gender(fitness.isGender())
				.height(fitness.getHeight())
				.weight(fitness.getWeight())
				.joinDate(fitness.getJoinDate())
				.stdWeight(fitness.getStdWeight())
				.bmi(fitness.getBmi())
				.bmiResult(fitness.getBmiResult())
				.build();
	}

}

