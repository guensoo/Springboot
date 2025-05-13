package com.korea.product2.DTO;

import java.time.LocalDateTime;

import com.korea.product2.Entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private int id;
	private String name;
	private int inventory;
	private int price;
	private LocalDateTime localTime;
	private LocalDateTime editTime;

	public ProductDTO(ProductEntity entity){
		this.id = entity.getId();
		this.name = entity.getName();
		this.inventory = entity.getInventory();
		this.price = entity.getPrice();
		this.localTime = entity.getLocalTime();
		this.editTime = entity.getEditTime();
	}
	
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
							.id(dto.getId())
							.name(dto.getName())
							.inventory(dto.getInventory())
							.price(dto.getPrice())
							.build();
	}
}
