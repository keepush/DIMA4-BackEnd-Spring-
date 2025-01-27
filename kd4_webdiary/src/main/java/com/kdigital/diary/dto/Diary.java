package com.kdigital.diary.dto;

import java.time.LocalDate;

import com.kdigital.diary.entity.DiaryEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Diary {
	private Integer diary_seq;
	private String username;
	private String feeling;
	private String title;
	private String records;
	private LocalDate regdate;
	
	public static Diary toDTO(DiaryEntity diaryEntity) {
		return Diary.builder()
				.diary_seq(diaryEntity.getDiary_seq())
				.username(diaryEntity.getUsername())
				.feeling(diaryEntity.getFeeling())
				.title(diaryEntity.getTitle())
				.records(diaryEntity.getRecords())
				.regdate(diaryEntity.getRegdate())
				.build();
	}
}
