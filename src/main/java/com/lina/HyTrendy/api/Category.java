package com.lina.HyTrendy.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.dto.CategoryDto;
import com.lina.HyTrendy.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Category {
	private final CategoryService categoryService;
	
	@GetMapping("/category")
	public List<CategoryDto> getAllCategory(){
		return categoryService.getAll();
	}
}
