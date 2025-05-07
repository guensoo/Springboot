package com.korea.user.dto;

import com.korea.user.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String name;
	private String email;
	
	// 생성자(UserEntity -> UserDTO)
		public UserDTO(UserEntity entity) {
			this.id = entity.getId();
			this.name = entity.getName();
			this.email = entity.getEmail();
		}
		
		// UserDTO -> UserEntity
		public static UserEntity toEntity(UserDTO dto) {
			return UserEntity.builder()
								.id(dto.getId())
								.name(dto.getName())
								.email(dto.getEmail())
								.build();
		}
}
