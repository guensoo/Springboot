package com.kroea.member.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kroea.member.DTO.MemberDTO;
import com.kroea.member.DTO.ResponseDTO;
import com.kroea.member.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {
	private final MemberService service;
	
	@GetMapping
	public ResponseEntity<?> findByEmail(){
		try {
			List<MemberDTO> member = service.showMember();
			return ResponseEntity.ok(member);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email){
		try {
			List<MemberDTO> member = service.filterMember(email);
			return ResponseEntity.ok(member);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addMember(@RequestBody MemberDTO dto) {
		
		try {
			List<MemberDTO> member = service.addMember(dto);
			return ResponseEntity.ok(member);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping("/{email}")
	public ResponseEntity<?> editMember(@PathVariable String email, @RequestBody MemberDTO dto) {
		try {
			List<MemberDTO> member = service.editMember(email, dto);
			return ResponseEntity.ok(member);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
		
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable int id){
		try {
			List<MemberDTO> member = service.deleteMember(id);
			return ResponseEntity.ok(member);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
