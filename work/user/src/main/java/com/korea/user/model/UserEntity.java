package com.korea.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Users")
public class UserEntity {
	
	@Id // 엔티티의 기본키(Primary key)필드를 나타낸다.
	@GeneratedValue(strategy = GenerationType.AUTO) // 기본키 값을 자동 생성하도록 지시한다.
	private Long id;
	private String name;
	private String email;
}
