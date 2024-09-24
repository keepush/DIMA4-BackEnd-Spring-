package com.kdigital.score.dto;

import com.kdigital.score.entity.ScoreEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ScoreDTO {
	private String stid;
	private String stname;
	private Integer korean;
	private Integer english;
	private Integer math;
	private Integer seq;
	
	public static ScoreDTO toDTO(ScoreEntity scoreEntity) {
		return ScoreDTO.builder()
				.stid(scoreEntity.getStid())
				.stname(scoreEntity.getStname())
				.korean(scoreEntity.getKorean())
				.english(scoreEntity.getEnglish())
				.math(scoreEntity.getMath())
				.build();
	}
}
