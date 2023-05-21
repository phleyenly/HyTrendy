package com.lina.HyTrendy.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

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
	
	@Relationship(type = "HAS_PRODUCT_ORDER", direction = Relationship.Direction.OUTGOING)
	private List<ProductEntity> products;

}
