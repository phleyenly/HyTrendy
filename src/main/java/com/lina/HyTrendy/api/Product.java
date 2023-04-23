package com.lina.HyTrendy.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.dto.CategoryDto;
import com.lina.HyTrendy.dto.ProductDto;
import com.lina.HyTrendy.service.CategoryService;
import com.lina.HyTrendy.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Product {

	private final ProductService productService;
	private final CategoryService categoryService;
	
	@GetMapping("/product")
	public List<ProductDto> getAllProduct(){
		return productService.getAll();
	}
	
	@GetMapping("/product/{categoryCode}/{typeCode}")
	public List<ProductDto> getByCategoryAndType(@PathVariable(name = "categoryCode", required = false) String categoryCode, 
			@PathVariable(name = "typeCode", required = false) String typeCode) {
		return productService.getByCategoryAndType(categoryCode, typeCode);
	}

	@GetMapping("/product/{id}")
	public ProductDto getById (@PathVariable (name = "id" , required = false) long id) {
		return productService.getById(id);
	}

////	@GetMapping("/product")
////	public List<ProductDto> findAllProduct(){
////		return productService.findAll();
////	}
//
////	@GetMapping("/product")
////	public List<ProductDto> getAllProduct(){
////		return productService.getAll();
////	}
//
////	@GetMapping("/product")
////	public List<ProductDto> getById(@RequestParam("id") Long id) { đẻe như này thì xem là bắt buộc phâir tuyển pagram
////		if (id != null) {
////			return productService.getById(id);
////		} else {
////			return productService.getAll();
////		}
////	}
//	
////	@GetMapping("/product")
////	public List<ProductDto> getById(@RequestParam(name = "id", required = false) Long id) {
////		if (id != null) {
////			return productService.getById(id);
////		} else {
////			return productService.getAll();
////		}
////	}
//	
//	// không cần truyền id thì dùng cái này PathVariable này
//	
//	@GetMapping("/product/{id}")
//	public List<ProductDto> getById(@PathVariable(name = "id", required = false) Long id) {
//		if (id != null) {
//			return productService.getById(id);
//		} else {
//			return productService.getAll();
//		}
//	}
//	
//	//Phuong thuc Post truyen Json
//	
//	@PostMapping("/post")
//	public ProductDto test (@RequestBody ProductDto item ) {
//		return item;
//	}
}
