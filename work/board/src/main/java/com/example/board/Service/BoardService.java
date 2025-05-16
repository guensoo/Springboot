package com.example.board.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.board.DTO.BoardDTO;
import com.example.board.Entity.BoardEntity;
import com.example.board.Repository.BoardRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository repository;
	
	public List<BoardDTO> getAll() {
		List<BoardEntity> board = repository.findAll();
		return board.stream().map(BoardDTO::new).collect(Collectors.toList());
	}

	public List<BoardDTO> addPost(BoardDTO dto) {
		BoardEntity board = BoardDTO.toEntity(dto);  
		repository.save(board);
		return repository.findAll().stream().map(BoardDTO::new).collect(Collectors.toList());
	}

	public List<BoardDTO> editPost(BoardDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BoardDTO> deletePost(Long id) {
		repository.findById(id).ifPresent(repository::delete);
		return repository.findAll().stream().map(BoardDTO::new).collect(Collectors.toList());
	}

}
