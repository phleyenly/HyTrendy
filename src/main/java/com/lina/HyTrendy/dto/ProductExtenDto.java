package com.lina.HyTrendy.dto;

import com.lina.HyTrendy.projection.ProductExtendProjection;

import lombok.Data;

@Data
public class ProductExtenDto {
	private Long id;
	private String name;
	private int price;
	private int stock;
	private String[] size;
	private String tags;
	private String origin;
	private String description;
	private String[] image;
	private String material;
	private Long categoryId;
	private Long typeId;
}
