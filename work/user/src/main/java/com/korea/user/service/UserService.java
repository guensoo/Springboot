package com.korea.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.user.model.UserEntity;
import com.korea.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository repository; // = new UserRepository();
	
	// 추가하고 email를 기준으로 목록을 반환
		public List<UserEntity> create(UserEntity entity){
			
			validate(entity);
			
			// 데이터베이스에 추가
			// insert into user values(....)
			repository.save(entity);
			
			log.info("Entity Id : {} is saved", entity.getId());
			
			// 엔티티를 데이터베이스에 추가하고, 전체 조회를 한다.
			return repository.findByEmail(entity.getEmail());
		}
		
		private void validate(UserEntity entity) {
			if(entity == null) {
				log.warn("Entity cannot be null");
				throw new RuntimeException("Entity cannot be null");
			}
			if(entity.getEmail() == null) {
				log.warn("Unknown user");
				throw new RuntimeException("Unknown user");
			}
		}
		
		// 조회하는 retrive메서드
		public List<UserEntity> retriveUserList(String email) {
			return repository.findByEmail(email);
		}
		
		public List<UserEntity> update(UserEntity entity){
			// 저장할 엔티티가 유효한지 확인
			validate(entity);
			
			// 넘겨받은 엔티티 Email를 이용해 UserEntity를 가져온다.
			// 존재하지 않는 엔티티는 수정할 수 없으니까.
			List<UserEntity> original = repository.findByEmail(entity.getEmail());
			
			for (UserEntity user : original) {
		        user.setName(entity.getName());
		        user.setEmail(entity.getEmail());
		        repository.save(user);
		    }
			
			// 전체 내용을 조회해서 반환
			return retriveUserList(entity.getEmail());
		}
}
