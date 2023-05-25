package com.lina.HyTrendy.reponsitory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lina.HyTrendy.entity.OrderEntity;
import com.lina.HyTrendy.projection.OrderProjection;
import com.lina.HyTrendy.projection.ProductOrderProjection;

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
			+ " WHERE p.username = $username"
			+ " Return o, collect(h2), collect(pr) AS products")
	public List<OrderEntity> getOrderByUsername(@Param("username") String username);
	
	@Query("MATCH (p:Person)-[h:HAS_ORDER]-> (o:Order)"
			+ " RETURN p, h, o")
	public List<OrderEntity> getAllOrder();
	
	@Query("MATCH (o:Order)-[h:HAS_PRODUCT_ORDER]->(pr:Product)"
			+ " WHERE ID(o)=$idOrder"
			+ " RETURN"
			+ " ID(pr) as idProduct,"
			+ " pr.image as image,"
			+ " pr.name as name,"
			+ " pr.price as price,"
			+ " pr.description as description,"
			+ " h.quantity as quantity,"
			+ " h.size as sizeBuy")
	public List<ProductOrderProjection> getProductByIdOrder(@Param("idOrder") Long idOrder);
	

}
