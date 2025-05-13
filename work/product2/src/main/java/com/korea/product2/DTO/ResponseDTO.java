package com.korea.product2.DTO;

import java.util.List;

import com.korea.product2.Controller.ProductController;
import com.korea.product2.Service.ProductService;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {
	private String error;
	private List<T> data;
}
