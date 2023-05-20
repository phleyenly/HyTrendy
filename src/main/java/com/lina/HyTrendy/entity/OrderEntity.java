package com.lina.HyTrendy.entity;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Data;

@Node("Order")
@Data
public class OrderEntity {
	@Id @GeneratedValue
	private Long id;
	
	@Property("status")
	private String status;
	
	@Property("date")
	private LocalDate date;

}
