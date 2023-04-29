package com.lina.HyTrendy.dto;

import lombok.Data;

@Data
public class CartItemDto {
	private Integer quantity;
	private Integer price;
	private String name;
}
