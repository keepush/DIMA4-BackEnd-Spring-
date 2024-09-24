package com.kdigital.Test3.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kdigital.Test3.DTO.FitnessDTO;
import com.kdigital.Test3.Repository.FitnessRepository;
import com.kdigital.Test3.entity.FitnessEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor

public class FitnessService {
	
		final FitnessRepository repository;
		
		
		// DB에 데이터를 저장하기 위한 메소드 
		public void insertMember(FitnessDTO fitness) {
			log.info("{}",fitness.toString());
			// DTO를 ENTITY로 변환
			FitnessEntity fitnessEntity = FitnessEntity.toEntity(fitness);
			repository.save(fitnessEntity);
		}
		// db에서 friend 목록을 조회
		public List<FitnessDTO> list() {
			List<FitnessDTO> list = new ArrayList<>();
			List<FitnessEntity> entityList = repository.findAll();
			
			// entity들을 dto로 변환한다
			entityList.forEach((entity)-> list.add(FitnessDTO.toDTO(entity)));
			log.info("{}", list.get(0).toString());
			return list;
		}
		//DB에 fseq번호에 해당하는 데이터를 삭제함
		public void deleteMember(Integer seq) {
			log.info("삭제할 번호 ==> {}", seq);
			repository.deleteById(seq);
		}
		
		// db에서 fseq번호에 해당하는 데이터를 조회
		public FitnessDTO selectMember(Integer seq) {
			//null로 인한 오류로 FriendEntity를 반환하지 않고 Optional 이라는 wrapper 클래스로 감싸서 반환한다
			Optional <FitnessEntity> entity = repository.findById(seq);
			if (entity.isPresent()) {
				FitnessEntity FitnessEntity = entity.get();
				return FitnessDTO.toDTO(FitnessEntity);
			}
			return null;
		}
		@Transactional
		public void updateMember(FitnessDTO fitness) {
			// dto를 entity로 수정하는 작업함
			// set을 하면 바뀜
			log.info("updateOne ============> {}",fitness.toString());
			// PK를 이용해서 그 데이터가 있는지 조회먼저 한다. --> findById()
			Optional <FitnessEntity> entity = repository.findById(fitness.getSeq());
			
			// 조회된 데이터에서 수정할 정보를 setting 한다.
			if (entity.isPresent()) {
				FitnessEntity fitnessEntity = entity.get(); // 바뀌기전 데이터
				
				// 나이, 전화번호, 성향, 생일 꺼내서 엎어치기
				fitnessEntity.setWeight(fitness.getWeight());
				fitnessEntity.setHeight(fitness.getHeight());
				fitnessEntity.setGender(fitness.isGender());
				
			}	
		}
		
}
