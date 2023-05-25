package com.lina.HyTrendy.entity;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node("Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
	@Id @GeneratedValue
	private Long id;
	
	@Property("username")
	private String username;
	
	@Property("password")
	private String password;
	
	@Property("name")
	private String name;
	
	@Property("role")
	private String role;
	
	@Property("address")
	private String address;
	
	@Property("phone")
	private String phone;
}
