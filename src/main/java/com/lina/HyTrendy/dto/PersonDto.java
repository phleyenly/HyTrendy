package com.lina.HyTrendy.dto;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Data;

@Data
public class PersonDto {
	private Long id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String role;
	
	private String address;
	
	private String phone;

}
