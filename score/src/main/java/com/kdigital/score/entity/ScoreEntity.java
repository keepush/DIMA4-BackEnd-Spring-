package com.kdigital.score.entity;


import com.kdigital.score.dto.ScoreDTO;

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
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="student")
public class ScoreEntity {
	@Id
	@Column(name="stid")
	private String stid;
	
	@Column(name="stname", nullable = false)
	private String stname;
	
	@Column(name="korean")
	private Integer korean;
	
	@Column(name="english")
	private Integer english;
	
	@Column(name="math")
	private Integer math;
	
	public static ScoreEntity toEntity(ScoreDTO scoreDTO) {
		return ScoreEntity.builder()
				.stid(scoreDTO.getStid())
				.stname(scoreDTO.getStname())
				.korean(scoreDTO.getKorean())
				.english(scoreDTO.getEnglish())
				.math(scoreDTO.getMath())
				.build();
	}
}
