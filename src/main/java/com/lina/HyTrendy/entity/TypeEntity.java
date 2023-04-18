package com.lina.HyTrendy.entity;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Data;

@Node("Type")
@Data
public class TypeEntity {
	@Id @GeneratedValue
	private Long id;
	
	@Property("name")
	private String name;
	
}
