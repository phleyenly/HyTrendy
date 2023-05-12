package com.lina.HyTrendy.api;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.dto.CategoryAndTypeNameDto;
import com.lina.HyTrendy.dto.CategoryDto;
import com.lina.HyTrendy.dto.ProductDto;
import com.lina.HyTrendy.dto.ProductExtenDto;
import com.lina.HyTrendy.service.CategoryAndTypeNameService;
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
	private final CategoryAndTypeNameService ctName;
	
	@GetMapping("/product")
	@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('CLIENT') ")
	public List<ProductDto> getAllProduct(){
		return productService.getAll();
	}
	
	@GetMapping("/product/{categoryCode}/{typeCode}")
	public List<ProductDto> getByCategoryAndType(@PathVariable(name = "categoryCode", required = false) String categoryCode, 
			@PathVariable(name = "typeCode", required = false) String typeCode) {
		return productService.getByCategoryAndType(categoryCode, typeCode);
	}

	@GetMapping("/product/{id}")
	public ProductExtenDto getProductExtendById (@PathVariable (name = "id" , required = false) Long id) {
		return productService.getProductExtendById(id);
	
//	public CategoryAndTypeNameDto getNameById (@PathVariable (name = "id" , required = true) long id) {
//		return ctName.getCategoryAndTypeById(id);
		
	}
	
	@PutMapping("/product/{id}")
	public Map<String, String> updateById (@PathVariable (name = "id", required = true) long id, @RequestBody ProductExtenDto p) {
		return productService.updateByID(id, p.getDescription(), p.getMaterial(), p.getName(),
			p.getOrigin(), p.getPrice(), p.getSize(), p.getStock(), p.getTags(), p.getImage(), p.getTypeId());
	}
	
	@PostMapping("/product")
	public Map<String, String> createProduct (@RequestBody ProductExtenDto p) {
		return productService.createProductByID(p.getDescription(), p.getMaterial(), p.getName(),
			p.getOrigin(), p.getPrice(), p.getSize(), p.getStock(), p.getTags(), p.getImage(), p.getTypeId(), p.getCategoryId());
	}
	
	@DeleteMapping("/product")
	public Map<String, String> deleteProduct (@RequestBody Long id) {
		return productService.deleteProductById(id);
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
