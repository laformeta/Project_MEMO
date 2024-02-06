package com.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.user.entity.UserEntity;
import com.memo.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	public Integer addUser(String loginId, String password, String name, String email) {
		UserEntity userEntity = userRepository.save(
					UserEntity.builder()
					.loginId(loginId)
					.password(password)
					.name(name)
					.email(email)
					.build()
				);
		
		return userEntity == null ? null : userEntity.getId();
	}
	
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
}
