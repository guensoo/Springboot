package com.kroea.member.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kroea.member.DTO.MemberDTO;
import com.kroea.member.Repository.MemberRepository;
import com.kroea.member.model.MemberEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberRepository repository;
	
	public List<MemberDTO> showMember(){
		List<MemberEntity> member = repository.findAll();
		return member.stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	public List<MemberDTO> filterMember(String email){
		List<MemberEntity> member = repository.findAll();
		if(email != null) {
			member = member.stream().filter(members -> email.equals(members.getEmail())).collect(Collectors.toList());
		}
		return member.stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	public List<MemberDTO> addMember(MemberDTO dto){
		   MemberEntity entity = MemberDTO.toEntity(dto);
		   repository.save(entity);
		return repository.findAll().stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	public List<MemberDTO> editMember(String email, MemberDTO dto){
		Optional<MemberEntity> optionalEntity = repository.findByEmail(email);
		   if(optionalEntity.isPresent()) {
			   MemberEntity entity = optionalEntity.get();
			   entity.setPassword(dto.getPassword());
			   repository.save(entity);
		   }
		   return repository.findAll().stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	public List<MemberDTO> deleteMember(int id){
		repository.findById(id).ifPresent(repository::delete);
		return repository.findAll().stream().map(MemberDTO::new).collect(Collectors.toList());
	}

}
