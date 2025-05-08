package com.korea.product.dto;

import com.korea.product.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Integer id;
	private String name;
	private String description;
	private int price;
	
	// 생성자(UserEntity -> UserDTO)
	public ProductDTO(ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
	}
			
	// UserDTO -> UserEntity
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
							.id(dto.getId())
							.name(dto.getName())
							.description(dto.getDescription())
							.price(dto.getPrice())
							.build();
	}
}
