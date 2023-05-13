package com.lina.HyTrendy.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lina.HyTrendy.entity.PersonEntity;
import com.lina.HyTrendy.projection.CartItemProjection;

@Repository
public interface PersonReponsitory extends Neo4jRepository<PersonEntity, Long> {//rename thanh person trc di
	@Query("MATCH (p:Person)-[h:HAS_CART]->(i:Product) "
			+ "WHERE ID(p) = $id "
			+ "RETURN i.name as name, i.price as price , h.quantity as quantity")
	public List<CartItemProjection> getCartByPersonById ( @Param("id") Long id);
	
	@Query("MATCH (p:Person) "
			+ "RETURN distinct p.role")
	public List<String> getRole();
	
	@Query("MATCH (p:Person) Return p")
	public List<PersonEntity> getAll();

	public PersonEntity findByUsername(String username);
	
	

}
