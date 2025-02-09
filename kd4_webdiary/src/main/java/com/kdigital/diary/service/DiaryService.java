package com.kdigital.diary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kdigital.diary.dto.Diary;
import com.kdigital.diary.entity.DiaryEntity;
import com.kdigital.diary.repository.DiaryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
	
	final DiaryRepository repository;

	public void insert(Diary diary) {
		DiaryEntity diaryEntity = DiaryEntity.toEntity(diary);
		
		repository.save(diaryEntity);
	}

	public List<Diary> list() {
		List<Diary> list = new ArrayList<>();
		
		List<DiaryEntity> entityList = repository.findAll();
		
		entityList.forEach((entity) -> list.add(Diary.toDTO(entity)));
		
		return list;
	}

	public void deleteOne(Integer diary_seq) {
		repository.deleteById(diary_seq);
	}

}
