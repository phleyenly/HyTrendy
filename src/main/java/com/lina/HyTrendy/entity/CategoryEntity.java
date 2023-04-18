package com.lina.HyTrendy.entity;

import java.util.List;


import org.springframework.data.neo4j.core.schema.GeneratedValue;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;


import lombok.Data;

@Node("Category")
@Data
public class CategoryEntity {
	@Id @GeneratedValue
	private Long id;
	
	@Property("name")
	private String name;
	
	@Relationship(type = "HAS_TYPE", direction = Relationship.Direction.OUTGOING)
	private List<TypeEntity> types;
}
