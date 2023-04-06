package com.lina.HyTrendy.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Data;

@Node("Product")
@Data
public class ProductEntity {
	@Id @GeneratedValue
	private Long id;
	
	@Property("name")
	private String name;
	
	@Property("price")
	private int price;
}
