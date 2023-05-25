package com.lina.HyTrendy.dto;

import java.time.LocalDate;
import java.util.List;

import com.lina.HyTrendy.entity.OrderEntity;
import com.lina.HyTrendy.entity.PersonEntity;
import com.lina.HyTrendy.projection.ProductOrderProjection;

import lombok.Data;

@Data
public class OrderExtendDto {
	
	private Long id;
	
	private String status;
	
	private LocalDate date;
	
	private PersonDto person;
	
	
	private List<ProductOrderProjection> products;
	
	
}
