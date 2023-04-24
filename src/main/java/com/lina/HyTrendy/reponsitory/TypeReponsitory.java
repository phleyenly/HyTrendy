package com.lina.HyTrendy.reponsitory;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.lina.HyTrendy.entity.TypeEntity;

public interface TypeReponsitory extends Neo4jRepository<TypeEntity, Long> {
//	@Query("match (t:Type) where t.code= $typeCode return t.name")
//	public String getTypeByCode (@Param ("typeCode") String typeCode);
	
	@Query("MATCH (c:Category)-[h:HAS_TYPE]->(t:Type) "
			+ "WHERE c.code = $categoryCode AND t.code = $typeCode "
			+ "RETURN t.name")
		public String getTypeByCode(@Param("categoryCode") String categoryCode, @Param("typeCode") String typeCode);

}
