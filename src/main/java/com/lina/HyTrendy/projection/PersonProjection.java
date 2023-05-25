package com.lina.HyTrendy.projection;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonProjection {
	private Long id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String role;
	
	private String address;
	
	private String phone;
}
