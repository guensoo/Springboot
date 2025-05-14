package com.korea.product2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.korea.product2.Entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
	// 모든 주문 내역을 조회하는 쿼리 만들기
	// 총 결제 내역(주문개수 X 상품의 가격) 도 조회를 해보자
	// @ManyToOne으로 설정된 관계 매핑 덕분에, OrderEntity에서 product필드를 통해
	// ProductEntity에 접근할 수 있다.
	// JPA는 JPQL 쿼리를 분석하고, OrderEntity에서 ProductEntity와의 관계가 설정된 것을 감지하면
	// 자동으로 SQL 조인을 생성한다.
	@Query("SELECT o.orderId,"
			+ " o.product.name,"
			+ "o.productCount, "
			+ "o.product.price, "
			+ "(o.productCount * o.product.price) AS totalPrice "
			+ "from OrderEntity o")
	List<Object[]> findAllOrderTotalPrices();
	// 제네릭이 오브젝트 배열인 이유
	// JPQL 쿼리에서 여러 개의 값을 선택(SELECT)하면, 한 행(row)에 대응하는 결과가
	// Object배열 형태로 반환되기 때문이다.
	// o.orderId -> Object[0]
	// o.product.productName -> Object[1]
	// ..
}
