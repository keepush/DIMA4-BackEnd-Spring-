package com.kdigital.spring7.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kdigital.spring7.dto.UserDTO;
import com.kdigital.spring7.entity.UserEntity;
import com.kdigital.spring7.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	final UserRepository userRepository;
	final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * 전달 받은 userDTO를 userEntity로 변경한 후 DB save();
	 * @param userDTO
	 */
	public boolean join(UserDTO userDTO) {
		// 가입하려는 id가 이미 사용중인지 확인 : 사용중인 아이디이면 true
		boolean isExistUser = userRepository.existsById(userDTO.getUserId());
		if(isExistUser) return false;		// 이미 사용중인 아이디이므로 가입 실패
		
		// 비밀번호 암호화
		userDTO.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
		
		UserEntity userEntity = UserEntity.toEntity(userDTO);
		userRepository.save(userEntity);  	// 가입 성공
		
		return true;
	}

	
	/**
	 * useId에 해당하는 사용자 존재 여부 확인
	 * 
	 * @param userId
	 * @return
	 */
	public boolean existId(String userId) {
		boolean result = userRepository.existsById(userId);	// userId가 존재하면 true
		return result;
	}

	
	/**
	 * 개인정보 수정을 위해 아이디와 비밀번호 확인 처리 요청
	 * @param userId
	 * @param userPwd
	 */
	public UserDTO pwdCheck(String userId, String userPwd) {
		// 비밀번호 암호화
		String encodedPwd = bCryptPasswordEncoder.encode(userPwd);
		
		Optional <UserEntity> userEntity = userRepository.findById(userId);		// findById는 Optional로 반환하므로 Optional로 받음
		
		if(userEntity.isPresent()) {
			UserEntity temp = userEntity.get();
			String pwd = temp.getUserPwd();			// DB에 저장된 비밀번호
			
			// 전달인자 1 - 암호화 되지 않은 비밀번호, 2 - DB에 저장된 비밀번호 
			boolean result = bCryptPasswordEncoder.matches(userPwd, pwd);
			
			if(result) 
				return UserDTO.toDTO(temp);		// 수정하고자 하는 정보가 넘어가는 것 (수정 가능 상태)
		}
		
		return null;
	}

	
	/**
	 * 개인 정보 수정
	 * 수정하려고 하는 정보를 setter를 통해 수정한다!
	 * JPA의 save() 메소드 : 저장 + 수정도 가능
	 * 		저장 : 동일한 PK 없으면 insert
	 * 		수정 : 동일한 PK 있으면 update
	 * @param userDTO
	 */
	@Transactional
	public boolean update(UserDTO userDTO) {
		// roles, enabled
		String userId = userDTO.getUserId();
		
		Optional<UserEntity> userEntity = userRepository.findById(userId);
		if(userEntity.isPresent()) {
			
			UserEntity entity = userEntity.get();
			
			// 비밀번호 암호화
			entity.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
			entity.setEmail(userDTO.getEmail());
			
			return true;
		}
		
		return false;
		
//		boolean isExist = userRepository.existsById(userId);		// 위를 사용하려면 findById(userId)를 사용해야 가능	
//		log.info("{}", isExist);		// true가 반환되어야 수정 가능
		
//		UserEntity userEntity = UserEntity.toEntity(userDTO);
		
		
		// 수정 1)
				// userRepository.save(userEntity);		// PK가 있으므로 수정
		
	}

}
