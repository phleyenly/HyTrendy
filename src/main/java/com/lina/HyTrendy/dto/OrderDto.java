package com.lina.HyTrendy.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.lina.HyTrendy.entity.PersonEntity;
import com.lina.HyTrendy.entity.ProductEntity;

import lombok.Data;

@Data
public class OrderDto {
	private Long id;
	
	private String status;
	
	private LocalDate date;
	
	private List<ProductOrderDto> products;

	private Long personId;
	
	private String name;
	
	private String address;
	
	private String phone;

}
