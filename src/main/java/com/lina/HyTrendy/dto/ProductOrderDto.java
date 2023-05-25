package com.lina.HyTrendy.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductOrderDto {
	private Long productId;
	private String name;
	private int price;
	private int stock;
	private String tags;
	private String origin;
	private String description;
	private List<String> image;
	private String material;
	private int quantity;
}
