package com.example.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.Entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

}
