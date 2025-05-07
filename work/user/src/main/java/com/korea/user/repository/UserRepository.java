package com.korea.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korea.user.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	List<UserEntity> findByEmail(String email);
}
