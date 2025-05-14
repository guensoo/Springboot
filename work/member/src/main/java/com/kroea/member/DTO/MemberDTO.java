package com.kroea.member.DTO;

import com.kroea.member.model.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private int id;
	private String name;
	private String email;
	private String password;
	
	public MemberDTO(MemberEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
	}
	
	public static MemberEntity toEntity(MemberDTO dto) {
		return MemberEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.email(dto.getEmail())
				.password(dto.getPassword())
				.build();
	}
	
}
