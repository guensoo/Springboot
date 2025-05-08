package com.korea.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product.dto.ProductDTO;
import com.korea.product.model.ProductEntity;
import com.korea.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repository;
	   //public UserService(UserRepository repositroy){
	   //   this.repository = repository
	   //} 
	   //와 같다.
	   
	   //상품 추가
	   public List<ProductDTO> addProduct(ProductDTO dto){
		   ProductEntity entity = ProductDTO.toEntity(dto);
		   // jpa로 데이터베이스에 CRUD할 때 만드시 entity로만 해야한다.
	      repository.save(entity); //데이터베이스에 저장
	      
	      return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	   }
	   // 넘어온 데이터를 db에 추가하고 전체를 조회해서 반환을 해야 한다. -> 왜? db에 있는 데이터를 조회해야 하기 때문
	   // 반환형을 List<ProductDTO>로 해야겠다. -> 반환형 왜????
	   // jpa메서드를 이용해서 데이터베이스에 추가 -> 레파지토리에 있는 jpa?
	   // save에 들어갈 수 있는 타입이 Entity다 -> 그럼 어떻게 함? -> DTO를 Entity로 바꾼다. toEntity를 스태틱으로 올려놓았음.
	   // toEntity메서드가 ProductDTO에 있으니 꺼내서 쓴다.
	   // findAll() : 모든것을 꺼낸다.
	   // 반환형이 List<ProductDTO> 이다. 근데 지금 Entity 타입으로 변경되어있음.
	   // Entity -> DTO로 다시 변환시켜야 한다.
	   // 
	   
	   //모든 상품 조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
	   public List<ProductDTO> getFilteredProducts(Double minPrice){
		   // 일단 전체 조회를 한다.
		   List<ProductEntity> products = repository.findAll();
		   // 가격 필터링(minPrice가 있을 경우)
		   if(minPrice != null) {
			   products = products
					   		.stream()
					   		.filter(product -> product.getPrice() >= minPrice)
					   		.collect(Collectors.toList());
		   }
		   
		   return products.stream().map(ProductDTO::new).collect(Collectors.toList());
	   }
	   
//	   public List<ProductDTO> getAllProducts(){
//	      return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
//	   }
	   
	   //상품 id로 상품이름, 설명, 가격 수정하기
	   
	   // 매개변수를 Integer타입의 id로 받고, 리스트로 반환한다. 리스트 타입은 ProductDTO타입이다.
	   // 매개변수로 ProductDTO dto는 사용자가 입력한 JSON을 가져오는 것이다.
//	   public List<ProductDTO> editProducts(Integer id, ProductDTO dto){
//		   // 받아온 id를 기준으로 같은 것이 있는지 찾아본다.
//		   List<ProductEntity> products = repository.findAll();
//		   // 받아온 id가 있다면! 해당 아이디의 이름, 설명, 가격을 수정한다.
//	   }
	   
	   public List<ProductDTO> updateProduct(int id, ProductDTO dto){
		   // id를 통해서 데이터베이스에 저장되어 있는 데이터를 꺼내온다.
		   Optional<ProductEntity> optionalEntity = repository.findById(id);
		   // 데이터가 존재한다면
		   if(optionalEntity.isPresent()) {
			   // Optional에 들어있는 데이터를 꺼낸다.
			   ProductEntity entity = optionalEntity.get();
			   // 수정하려고 하는 데이터로 다시 세팅한다.
			   entity.setName(dto.getName());
			   entity.setDescription(dto.getDescription());
			   entity.setPrice(dto.getPrice());
			   repository.save(entity);
		   }
		   return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	   }
	   
	   public List<ProductDTO> deleteProduct(int id){
		// 데이터가 존재한다면
		   repository.findById(id).ifPresent(repository::delete);
		   // 이 한줄이 => 아래의 긴 코드가 줄여진 것.
//		   Optional<ProductEntity> optionalEntity = repository.findById(id);
//		   if(optionalEntity.isPresent()) {
//			   // Optional에 들어있는 데이터를 꺼낸다.
//			   ProductEntity entity = optionalEntity.get();
//			   repository.deleteById(id);
//		   }
		   return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	   }
}
