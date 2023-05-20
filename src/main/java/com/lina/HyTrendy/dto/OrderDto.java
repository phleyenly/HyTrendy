package com.lina.HyTrendy.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class OrderDto {
	private Long id;
	
	private String status;
	
	private LocalDate date;

}
