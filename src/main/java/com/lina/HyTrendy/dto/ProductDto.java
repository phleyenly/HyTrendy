package com.lina.HyTrendy.dto;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Data;

@Data
public class ProductDto {
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
	
	
}
