package com.lina.HyTrendy.projection;

import java.util.List;

import lombok.Data;

@Data
public class ProductOrderProjection {
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
