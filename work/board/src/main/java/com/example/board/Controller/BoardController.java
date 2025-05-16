package com.example.board.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.DTO.BoardDTO;
import com.example.board.Service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/board")
public class BoardController {
	private final BoardService service;
	
	// 게시물 전체 조회 기능 만들기
	// 포스트맨으로 기능 확인하기
	// board-react-app에서 우리의 백엔드로 요청하여 결과 받아오기
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		List<BoardDTO> board = service.getAll();
		return ResponseEntity.ok(board);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addPosts(@RequestBody BoardDTO dto){
		List<BoardDTO> board = service.addPost(dto);
		return ResponseEntity.ok(board);
	}
	
	@DeleteMapping("/post/{id}")
	public ResponseEntity<?> deletePost(@PathVariable("id") Long id){
		List<BoardDTO> board = service.deletePost(id);
		return ResponseEntity.ok(board);
	}
}
