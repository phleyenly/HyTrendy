package com.lina.HyTrendy.dto;

import java.util.List;


import com.lina.HyTrendy.entity.TypeEntity;

import lombok.Data;

@Data
public class CategoryDto {
	private Long id;
	private String name;
	private List<TypeDto> types;
}
