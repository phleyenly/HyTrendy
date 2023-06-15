package com.lina.HyTrendy.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartItemDto {
	private Integer quantity;
	private Integer price;
	private String name;
	private String size;
	private Long idProduct;
	private Long IdCart;
	private List<String> image;
}
