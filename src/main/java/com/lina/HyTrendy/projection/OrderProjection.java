package com.lina.HyTrendy.projection;

import java.time.LocalDate;
import java.util.List;

import com.lina.HyTrendy.dto.PersonDto;
import com.lina.HyTrendy.dto.ProductDto;
import com.lina.HyTrendy.entity.OrderEntity;
import com.lina.HyTrendy.entity.PersonEntity;

import lombok.Data;

@Data
public class OrderProjection {
	
	private Long id;
	
	private String status;
	
	private LocalDate date;
	
	private PersonEntity person;
	
	
	private List<ProductOrderProjection> products;
	
	

}
