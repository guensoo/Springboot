package com.korea.product2.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.DTO.ProductDTO;
import com.korea.product2.DTO.ResponseDTO;
import com.korea.product2.Service.ProductService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService service;
	@GetMapping
	public ResponseEntity<?> findAll(){
		try {
			List<ProductDTO> dto = service.findAllProduct();
			ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(dto).build();
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			String error = e.getMessage();
			
			ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO dto){
		List<ProductDTO> response = service.createProduct(dto);
		return ResponseEntity.ok(response);
	}
}
