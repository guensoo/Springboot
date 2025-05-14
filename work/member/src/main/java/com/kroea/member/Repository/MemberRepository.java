package com.kroea.member.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kroea.member.model.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{
	Optional<MemberEntity> findByEmail(String email);
}
