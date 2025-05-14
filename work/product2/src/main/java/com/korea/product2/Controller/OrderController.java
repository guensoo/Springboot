package com.korea.product2.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.DTO.OrderDTO;
import com.korea.product2.DTO.ProductDTO;
import com.korea.product2.DTO.ResponseDTO;
import com.korea.product2.Service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService service;
	
	// 주문 조회 기능 만들기
	@GetMapping
	public ResponseEntity<?> findAll(OrderDTO dto){
		List<OrderDTO> list = service.getAllOrderTotalPrices();
		ResponseDTO<OrderDTO> response = ResponseDTO.<OrderDTO>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
	// 주문하기 기능 만들기
	@PostMapping
	public ResponseEntity<?> orderProduct(@RequestBody OrderDTO dto){
		List<ProductDTO> list = service.save(dto);
	    ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(list).build();
	    return ResponseEntity.ok(response);
	}
}
