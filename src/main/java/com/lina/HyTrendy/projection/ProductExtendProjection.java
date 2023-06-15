package com.lina.HyTrendy.projection;

import java.util.List;

import lombok.Data;

@Data
public class ProductExtendProjection {
	private Long id;
	private String name;
	private int price;
	private int stock;
	private List<String> size;
	private String tags;
	private String origin;
	private String description;
	private List<String> image;
	private String material;
	private Long categoryId;
	private Long typeId;

}
