package com.lina.HyTrendy.dto;

import java.util.List;

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
	private List<String> size;
	private String tags;
	private String origin;
	private String description;
	private List<String> image;
	private String material;
	
	
}
