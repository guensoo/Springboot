package com.korea.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product.dto.ProductDTO;
import com.korea.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	// 서비스 주입
	private final ProductService productService;
	
	// 상품추가
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO dto){
		List<ProductDTO> response = productService.addProduct(dto);
		return ResponseEntity.ok(response);
		// TODO: 왤케 짧아지냐 아 다시 공부해야겠다...
	}
	
	// 사용자가 인터페이스(리액트)에서 데이터가 넘어오겠다. -> 매개변수로 받아야겠다.
	// JSON으로 넘어오는걸 자바객체로 받아야 한다! -> @RequestBody
	// 사용자로부터 데이터를 받을 때는 DTO로 받아야 한다.
	// 매개변수로 넘어온 데이터를 넘겨야 한다. -> service로 넘거야 겠다.
	// 추가를 하고 전체 조회를 하려 한다. -> ProductDTO타입의 리스트에 담아야 겠다.?
	
	// TODO: 상품 조회 기능 만들기
	// 모든 상품 조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
//	@GetMapping("/{price}")
//	public Integer getId(@PathVariable("price")int price){
//		return "Product : " + price;
//	} // -> 말도 안되는 방식
	@GetMapping
	public ResponseEntity<?> getAllProducts(@RequestParam(required=false) Double minPrice){
		List<ProductDTO> products = productService.getFilteredProducts(minPrice);
		return ResponseEntity.ok(products);
	}
//	@GetMapping
//	public Integer getId(){
//		return "Product : " + price;
//	}
//	
	
	// 상품수정(id를 가지고 이름, 설명, 가격 수정)
	// 사용자가 인터페이스(리액트)에서 id데이터가 넘어오겠다. -> id를 매개변수로 받아야겠다.
	// 쿼리로 넘어오는걸 자바객체로 받아야 한다! -> @RequestParam
	// 사용자로부터 데이터를 받을 때는 DTO로 받아야 한다.
	// 매개변수로 넘어온 데이터를 넘겨야 한다. -> service로 넘거야 겠다.
	// 추가를 하고 전체 조회를 하려 한다. -> ProductDTO타입의 리스트에 담아야 겠다.?
	
//	@PutMapping
//	public ResponseEntity<?> editProducts(@RequestParam Integer id){
//		List<ProductDTO> products = productService.editProducts(id);
//	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDTO dto){
		List<ProductDTO> products = productService.updateProduct(id, dto);
		return ResponseEntity.ok(products);
	}
	
	// 삭제(id를 가지고 상품 삭제)
	// @PathVariable을 이용하여 삭제하기
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		List<ProductDTO> products = productService.deleteProduct(id);
		return ResponseEntity.ok(products);
	}
	
//	public ResponseEntity<?> deleteProduct(@PathVariable int id){
//		boolean isDeleted = productService.deleteProduct(id);
//		
//		// 삭제가 정상 처리되면 본문 없이 204 No Content 응답
//		if(isDeleted) {
//			return ResponseEntity.noContent().build();
//		}
//		// 삭제 실패시 404 Not Found 응답
//		return ResponseEntity.notFound().build();
//	}
}
