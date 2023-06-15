package com.lina.HyTrendy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import com.lina.HyTrendy.dto.ProductDto;
import com.lina.HyTrendy.dto.ProductExtenDto;
import com.lina.HyTrendy.entity.ProductEntity;
import com.lina.HyTrendy.projection.ProductExtendProjection;
import com.lina.HyTrendy.reponsitory.ProductReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductReponsitory productReponsitory;
	
	private final ModelMapper mapper;
	
//	public List<ProductDto> getAll(){
//		List<ProductDto> pdto = new ArrayList<>();
//		List<ProductEntity> productEntity = productReponsitory.findAll();
//		for(ProductEntity p:productEntity ) {
//			pdto.add(mapper.map(p, ProductDto.class));
//		}
//		
//		return pdto;
//	}
	
	public List<ProductDto> getAll(int page, int limit){
		List<ProductDto> pdto = new ArrayList<>();
		List<ProductEntity> productEntity = productReponsitory.getAll(page-1, limit);
		for(ProductEntity p:productEntity ) {
			pdto.add(mapper.map(p, ProductDto.class));
		}
		
		return pdto;
	}
	
	//ham tim theo category và type
	public List<ProductDto> getByCategoryAndType( String categoryCode, String typeCode){
		List<ProductDto> pdto = new ArrayList<>();
		List<ProductEntity> productEntity = productReponsitory.getByCategoryAndType(categoryCode, typeCode );
		for(ProductEntity p:productEntity ) {
			pdto.add(mapper.map(p, ProductDto.class));
		}
		
		return pdto;
	}
	
	public ProductDto getById ( long id) {
		ProductDto pdto = new ProductDto();
		ProductEntity productEntity = productReponsitory.getById(id);
		pdto = mapper.map(productEntity, ProductDto.class);
		return pdto;
		
	}
	

	
//	public Map<String, String> setById (long id, String description, String material, String name, 
//			String origin, int price, List<String> size, int stock, String tags, List<String> image) {
//		Map<String, String> result = new HashMap<>();
//		Long idRepository = productReponsitory.setByID(id, description, material, name, origin, price, size, stock, tags, image);
//		if (idRepository == null) {
//			result.put("message", "Chỉnh Sửa Thất Bại");
//		} else {
//			result.put("message", "Chỉnh Sửa Thành Công");
//		}
//		return result;
//		
//	}
	
	public Map<String, String> updateByID (long id, String description, String material, String name, 
			String origin, int price, List<String> size, int stock, String tags, List<String> image, long idType) {
		Map<String, String> result = new HashMap<>();
		Long idRepository = productReponsitory.updateByID(id, description, material, name, origin, price, size, stock, tags, image, idType);
		if (idRepository == null) {
			result.put("message", "Chỉnh Sửa Thất Bại");
		} else {
			result.put("message", "Chỉnh Sửa Thành Công");
		}
		return result;
		
	}
	
	public Map<String, String> createProductByID ( String description,  String material,  String name,
			 String origin,   int price,  List<String> size,  int stock, 
			 String tags,  List<String> image,  long typeId,   long categoryId) {
		Map<String, String> result = new HashMap<>();
		Long idRepository = productReponsitory.createProduct(description, material, name, origin, price, size, stock, tags, image, typeId, categoryId);
		if (idRepository == null) {
			result.put("message", "Thêm Thất Bại");
		} else {
			result.put("message", "Thêm Thành Công");
		}
		return result;
		
	}
	
	public Map<String, String> deleteProductById (Long id) {
		Map<String, String> result = new HashMap<>();
		productReponsitory.deleteById(id);
		Long idProduct = productReponsitory.getIdProduct(id);
		if(idProduct == null) {
			result.put("message", "Xoá Thành Công");
		} else {
			result.put("message", "Xoá Thất Bại");
		}
		return result;
	}
	
	public ProductExtenDto getProductExtendById ( Long id) {
		ProductExtenDto pdto = new ProductExtenDto();
		ProductExtendProjection productProjection = productReponsitory.getProductExtendById(id);
		pdto = mapper.map(productProjection, ProductExtenDto.class);
		return pdto;
		
	}
	
	public int totalProduct() {
		return productReponsitory.totalProduct();
	}
	
	
	
	//Hàm Ví dụ
//	public List<ProductDto> findAll(){
////		return productReponsitory.findAll();
//		List<ProductDto> pdto = new ArrayList<>();
//		List<ProductEntity> productEntity = productReponsitory.findAll();
//		for(ProductEntity p:productEntity ) {
//			pdto.add(mapper.map(p, ProductDto.class));
//		}
//		
//		return pdto;
//	}
//	
	
  //Ham ví dụ 
//	public List<ProductDto> getById( Long Id){
//		List<ProductDto> pdto = new ArrayList<>();
//		List<ProductEntity> productEntity = productReponsitory.getById(Id);
//		for(ProductEntity p:productEntity ) {
//			pdto.add(mapper.map(p, ProductDto.class));
//		}
//		
//		return pdto;
//	}
}
