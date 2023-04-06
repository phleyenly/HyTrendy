package com.lina.HyTrendy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.lina.HyTrendy.dto.ProductDto;
import com.lina.HyTrendy.entity.ProductEntity;
import com.lina.HyTrendy.reponsitory.ProductReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductReponsitory productReponsitory;
	
	private final ModelMapper mapper;
	
	public List<ProductDto> findAll(){
//		return productReponsitory.findAll();
		List<ProductDto> pdto = new ArrayList<>();
		List<ProductEntity> productEntity = productReponsitory.findAll();
		for(ProductEntity p:productEntity ) {
			pdto.add(mapper.map(p, ProductDto.class));
		}
		
		return pdto;
	}
}
