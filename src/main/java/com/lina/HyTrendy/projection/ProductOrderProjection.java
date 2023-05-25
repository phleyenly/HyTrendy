package com.lina.HyTrendy.projection;

import java.util.List;

import lombok.Data;

@Data
public class ProductOrderProjection {
	private Long idProduct;
	private String name;
	private int price;
	private String description;
	private List<String> image;
	private String sizeBuy;
	private int quantity;
	
}
