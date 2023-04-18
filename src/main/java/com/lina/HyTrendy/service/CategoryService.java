package com.lina.HyTrendy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lina.HyTrendy.dto.CategoryDto;
import com.lina.HyTrendy.entity.CategoryEntity;
import com.lina.HyTrendy.reponsitory.CategoryReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryReponsitory categoryReponsitory;
	private final ModelMapper mapper;
	
	public List<CategoryDto> getAll(){
		List<CategoryDto> cateDto = new ArrayList<>();
		List<CategoryEntity> cateEntity = categoryReponsitory.getAllCategory();
		for(CategoryEntity c: cateEntity) {
			cateDto.add(mapper.map(c, CategoryDto.class));
		}
		return cateDto;
	}
}
