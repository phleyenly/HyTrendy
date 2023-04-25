package com.lina.HyTrendy.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

@RelationshipEntity
public class QuantityItemsEntity {
	
	@Id @GeneratedValue
    private Long id;

    @Property
    private int quantity;

    @StartNode
    private PersonEntity person;

    @EndNode
    private ProductEntity product;

}
