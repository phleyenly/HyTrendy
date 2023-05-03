package com.lina.HyTrendy.reponsitory;

import java.util.List;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.lina.HyTrendy.entity.CategoryEntity;
import com.lina.HyTrendy.projection.CategoryAndTypeNameProjection;

public interface CategoryReponsitory extends Neo4jRepository<CategoryEntity, Long> {
	@Query("MATCH (c:Category)-[h:HAS_TYPE]->(t:Type)"
			+ " RETURN c, collect(t) AS types, collect(h)")
		public List<CategoryEntity> getAllCategory();
     
	@Query ("match (c:Category) where c.code = $categoryCode return c")
	public CategoryEntity getCategoryByCode( @Param("categoryCode") String categoryCode);
	
	@Query ("Match (p:Category)-[h1:HAS_TYPE] -(t:Type)-[h2:HAS_PRODUCT]->(pr:Product) "
			+ "Where ID(pr) = $id "
			+ "Return p.name as categoryName , t.name as typeName")
	public CategoryAndTypeNameProjection getCategoryAndTypeNameById (@Param("id") Long id);
}
