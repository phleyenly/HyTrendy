package com.lina.HyTrendy.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lina.HyTrendy.dto.CategoryAndTypeNameDto;
import com.lina.HyTrendy.projection.CategoryAndTypeNameProjection;
import com.lina.HyTrendy.reponsitory.CategoryReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryAndTypeNameService {
	private final CategoryReponsitory categoryReponsitory;
	private final ModelMapper mapper;
	
	public CategoryAndTypeNameDto getCategoryAndTypeById (Long id) {
		CategoryAndTypeNameDto cateAndTypeName = new CategoryAndTypeNameDto();
		CategoryAndTypeNameProjection ctnp = categoryReponsitory.getCategoryAndTypeNameById(id);
		cateAndTypeName = mapper.map(ctnp, CategoryAndTypeNameDto.class );
		return cateAndTypeName;
	}
	

}
