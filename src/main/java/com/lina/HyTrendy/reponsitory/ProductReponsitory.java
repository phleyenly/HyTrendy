package com.lina.HyTrendy.reponsitory;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.lina.HyTrendy.entity.ProductEntity;

public interface ProductReponsitory extends Neo4jRepository<ProductEntity, Long> {

}
