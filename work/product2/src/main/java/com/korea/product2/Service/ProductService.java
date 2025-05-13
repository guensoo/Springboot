package com.korea.product2.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product2.DTO.ProductDTO;
import com.korea.product2.DTO.ResponseDTO;
import com.korea.product2.Entity.ProductEntity;
import com.korea.product2.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository repository;
	
	public List<ProductDTO> createProduct(ProductDTO dto) {
		ProductEntity entity = ProductDTO.toEntity(dto);
		repository.save(entity);
		return repository.findAll().stream().map(ProductDTO :: new).collect(Collectors.toList());
	}
	
	public List<ProductDTO> findAllProduct() {
		List<ProductEntity> products = repository.findAll();
		return products.stream().map(ProductDTO::new).collect(Collectors.toList());
	}
}
