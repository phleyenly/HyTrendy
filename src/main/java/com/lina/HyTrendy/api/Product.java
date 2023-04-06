package com.lina.HyTrendy.api;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.dto.ProductDto;
import com.lina.HyTrendy.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Product {
	
	private final ProductService productService;
	
	@GetMapping("/product")
	public List<ProductDto> findAllProduct(){
		return productService.findAll();
	}
	
	

}
