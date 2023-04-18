package com.lina.HyTrendy.reponsitory;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.lina.HyTrendy.entity.CategoryEntity;

public interface CategoryReponsitory extends Neo4jRepository<CategoryEntity, Long> {
	@Query("MATCH (c:Category)-[h:HAS_TYPE]->(t:Type)"
			+ " RETURN c, collect(t) AS types, collect(h)")
		public List<CategoryEntity> getAllCategory();

}
