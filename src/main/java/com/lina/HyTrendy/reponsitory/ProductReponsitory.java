package com.lina.HyTrendy.reponsitory;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.lina.HyTrendy.entity.CategoryEntity;
import com.lina.HyTrendy.entity.ProductEntity;

public interface ProductReponsitory extends Neo4jRepository<ProductEntity, Long> {
	@Query("Match (p: Product) return p")
	public List<ProductEntity> getAll();
	
	@Query("match (c:Category)-[ht:HAS_TYPE] ->(t:Type)-[hp:HAS_PRODUCT]->(p:Product)"
			+ "Where c.code = $categoryCode and t.code = $typeCode "
			+ "return  p")
	public List<ProductEntity> getByCategoryAndType( @Param("categoryCode") String categoryCode,  @Param("typeCode") String typeCode);
	
	@Query ("match (p:Product) where ID(p) =$id Return p")
	public ProductEntity getById (@Param("id") long id);
	

}
