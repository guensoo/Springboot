package com.korea.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.ResponseDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO dto){
		try {
			String temporaryUserId = "temporary-user"; // 임시 유저 id
			
			// UserDTO객체를 UserEntity객체로 변환한다.
			UserEntity entity = dto.toEntity(dto);
			
			// 서비스 계층에 있는 create메서드를 호출하여, TodoEntity를
			// 데이터베이스에 저장하는 작업을 수행한다.
			// 이 메서드는 추가만 하는 것이 아니라 저장된 TodoEntity 객체들을 저장한 리스트를 반환한다.
			List<UserEntity> entities = service.create(entity);
			
			// 자바스트림을 이용해 반환된 엔티티 리스트를 TodoDTO리스트로 변환한다.
			List<UserDTO> dtos = entities.stream().map(UserDTO::new).collect(Collectors.toList());
			
			// 변환된 TodoDTO리스트를 이용해 responseDTO를 초기화 한다.
			ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// 혹시나 예외가 발생하는 경우 dto대신 error에 메시지를 넣어 반환한다.
			String error = e.getMessage();
			
			ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().error(error).build();

			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	
	// 이메일로 사용자 검색하기
	@GetMapping
	// retriveUserList 메서드
	public ResponseEntity<?> retriveUserList(@RequestParam("email") String email){
		
		List<UserEntity> entities = service.retriveUserList(email);
		List<UserDTO> dtos = entities.stream().map(UserDTO::new).collect(Collectors.toList());
		ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	
	// ID를 통해 이름과 이메일 수정하기
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody UserDTO dto){
		
		// dto를 entity로 변환
		UserEntity entity = UserDTO.toEntity(dto);
		
		// 서비스레이어의 update메서드를 이용해 entity를 업데이트 한다.
		List<UserEntity> entities = service.update(entity);
		
		// 자바 스트림을 이용해 반환된 엔티티 리스트를 TodoDTO리스트로 변환한다.
		List<UserDTO> dtos = entities.stream().map(UserDTO::new).collect(Collectors.toList());
		
		// 변환된 TodoDTO리스트를 이용해 ResponseDTO를 초기화 한다.
		ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
//	
//	// 삭제하기
//	// 넘어온 entity가 유효한지 확인하고
//	// delete()를 이용해서 db에서 삭제를 하고
//	// findByUserId()를 사용해 조회를 해서 반환
//	@DeleteMapping
//	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
//	   String temporaryUserId = "temporary-user";
//	     
//	   //TodoDTO객체를 TodoEntity객체로 변환한다.
//	   TodoEntity entity = TodoDTO.toEntity(dto);
//	    
//	   //임시유저 아이디 설정
//	   entity.setUserId(temporaryUserId);
//	      
//	   List<TodoEntity> entities = service.delete(entity);
//	   
//	   //스트림을 이용해서 List안에 있는 Entity객체를 DTO로 바꾼다.
//	   List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
//	   
//	   //dtos 리스트를 ResponseDTO의 리스트에 담아서 반환한다.
//	   ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
//	   
//	   return ResponseEntity.ok().body(response);
//	}
}
