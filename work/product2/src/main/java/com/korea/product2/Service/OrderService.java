package com.korea.product2.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product2.DTO.OrderDTO;
import com.korea.product2.DTO.ProductDTO;
import com.korea.product2.Entity.OrderEntity;
import com.korea.product2.Entity.ProductEntity;
import com.korea.product2.Repository.OrderRepository;
import com.korea.product2.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository repository;
	private final ProductRepository p_repository;
	
	// 주문내역 조회하기
	public List<OrderDTO> getAllOrderTotalPrices(){
		// JPQL 쿼리로 반환된 List<Object[]> 데이터를 받아온다.
		List<Object[]> results = repository.findAllOrderTotalPrices();
		// Object[]데이터를 OrderDTO로 변환
		return OrderDTO.toListOrderDTO(results);
	}
	
	// {productId : 1, productCount: 10}
	
	// 주문하기 기능 만들기
	// 1. 상품의 존재 여부를 확인
	// 2. 재고 확인 (재고 < 주문개수) 예외 발생시킨다. throw new RuntimeException("재고가 부족합니다...")
	// 3. 주문하기(주문내역을 저장)
	// 4. 재고 감소시키기
	// 5. 수정된 재고로 다시 데이터베이스에 저장하기
	// 6. 전체 내용을 반환하기
	public List<ProductDTO> save(OrderDTO dto){
		Optional<ProductEntity> option = p_repository.findById(dto.getProductId());
		
		ProductEntity productEntity;
		// 상품 조회가 된다면
		if(option.isPresent()) {
			productEntity = option.get(); // option객체에서 데이터를 꺼내온다.
		} else {
			throw new RuntimeException("상품을 찾을 수 없다.");
		}
		
		// 재고 확인을 한다.
		if(productEntity.getStock() < dto.getProductCount()) {
			throw new RuntimeException("재고가 부족합니다. 현재 재고 : " + productEntity.getStock());
		} 
		
		OrderEntity order = OrderEntity.builder()
										.product(productEntity)
										.productCount(dto.getProductCount())
										.build();
		
		// 주문내역 저장하기
		repository.save(order);
		// 재고감소
		productEntity.setStock(productEntity.getStock() - dto.getProductCount());

		
		// db에 수정된 재고로 업데이터
		p_repository.save(productEntity);
		
		return p_repository.findAll().stream().map(entity -> new ProductDTO(entity)).collect(Collectors.toList());
				
//		List<Object[]> results = repository.findAllOrderTotalPrices();
//		return OrderDTO.toListOrderDTO(results);
	}
}
