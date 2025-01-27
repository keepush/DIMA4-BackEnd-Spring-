package com.kdigital.diary.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.kdigital.diary.dto.Diary;

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
@Table(name="diary")
public class DiaryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 설정
	private Integer diary_seq;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@Column(name="feeling")
	private String feeling;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name="records", nullable = false)
	private String records;
	
	@CreationTimestamp
	private LocalDate regdate;
	
	public static DiaryEntity toEntity(Diary diary) {
		return DiaryEntity.builder()
				.diary_seq(diary.getDiary_seq())
				.username(diary.getUsername())
				.feeling(diary.getFeeling())
				.title(diary.getTitle())
				.records(diary.getRecords())
				.regdate(diary.getRegdate())
				.build();
	}
	
}
