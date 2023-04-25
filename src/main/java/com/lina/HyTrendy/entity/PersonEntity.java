package com.lina.HyTrendy.entity;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Data;

@Node("Person")
@Data
public class PersonEntity {
	@Id @GeneratedValue
	private Long id;
	
	@Property("username")
	private String userName;
	
	@Property("password")
	private String passWord;
	
	@Property("name")
	private String name;
	
	@Property("role")
	private String role;
	
	@Property("address")
	private String address;
	
	@Property("phone")
	private String phone;
	
	@Relationship(type = "HAS_CART")
    private List<ProductEntity> product;

}
