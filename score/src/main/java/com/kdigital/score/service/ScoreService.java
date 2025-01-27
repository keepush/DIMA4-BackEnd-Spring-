package com.kdigital.score.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kdigital.score.dto.ScoreDTO;
import com.kdigital.score.entity.ScoreEntity;
import com.kdigital.score.repository.ScoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScoreService {
	
	final ScoreRepository repository;
	
	/**
	 * DB에 성적 저장하기 위한 메소드
	 * @param score
	 */
	public void insert(ScoreDTO score) {
	log.info("{}", score.toString());
	
	// DTO를 Entity로 변환
	ScoreEntity scoreEntity = ScoreEntity.toEntity(score);
	repository.save(scoreEntity);
		
	}
	
	
	/**
	 * DB에서 성적 목록 조회
	 * @return
	 */
	public List<ScoreDTO> list() {
		List<ScoreDTO> list = new ArrayList<>();
		
		List<ScoreEntity> entitylist = repository.findAll();
		
		entitylist.forEach((entity) -> list.add(ScoreDTO.toDTO(entity)));
		
		log.info("{}", list.get(0).toString());
		
		return list;
	}

	/**
	 * 전달받은 학번의 성적 삭제
	 * @param stid
	 */
	public void delete(String stid) {
		log.info("삭제할 학번: {}", stid);
		
		repository.deleteById(stid);
	}

}
