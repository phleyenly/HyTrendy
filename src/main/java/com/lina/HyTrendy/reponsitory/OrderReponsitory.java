package com.lina.HyTrendy.reponsitory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lina.HyTrendy.entity.OrderEntity;

@Repository
public interface OrderReponsitory extends Neo4jRepository<OrderEntity, Long>  {
	@Query("MATCH (p:Person)"
			+ " WHERE ID(p) = $idPerson"
			+ " CREATE (p)-[h2:HAS_ORDER]->(o:Order)"
			+ " WITH o, p, h2"
			+ " MATCH (p)-[h1:HAS_CART]->(pr:Product)"
			+ " CREATE (o)-[h3:HAS_PRODUCT_ORDER]->(pr)"
			+ " SET h3.quantity = h1.quantity, h3.size = h1.size, o.status = $status, o.date = $date"
			+ " DELETE h1"
			+ " RETURN ID(o) AS result"
			+ " LIMIT 1")
	public Long createOrder(@Param("idPerson") Long idPerson, @Param("status") String status, @Param("date") LocalDate date);
	
	@Query("MATCH (p:Person)-[h1:HAS_ORDER]-> (o:Order)-[h2:HAS_PRODUCT_ORDER]->(pr:Product)"
			+ " WHERE ID(p)=15"
			+ " Return o, collect(h2), collect(pr) AS products")
	public List<OrderEntity> getAllOrder(@Param("idPersom") Long idPerson);
	

}
