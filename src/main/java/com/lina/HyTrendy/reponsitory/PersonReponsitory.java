package com.lina.HyTrendy.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lina.HyTrendy.dto.PersonDto;
import com.lina.HyTrendy.entity.PersonEntity;
import com.lina.HyTrendy.projection.CartItemProjection;

@Repository
public interface PersonReponsitory extends Neo4jRepository<PersonEntity, Long> {//rename thanh person trc di
	@Query("MATCH (p:Person)-[h:HAS_CART]->(i:Product)"
			+ " WHERE ID(p) = $id"
			+ " RETURN i, h, i.name as name, i.price as price, ID(i) as idProduct, h.quantity as quantity, h.size as size, ID(h) as IdCart")
	public List<CartItemProjection> getCartByPersonById ( @Param("id") Long id);
	
	//tạo cart 
	
	@Query("MATCH (p:Person), (pr:Product)"
			+ " WHERE ID(pr) = $idProduct and p.username= $username"
			+ " CREATE (p)-[h:HAS_CART {quantity: $quantity, size: $size}]->(pr)"
			+ " RETURN ID(h)")
	public Long createCartByUsername(@Param("idProduct") Long idProduct, @Param("username") String usernam, @Param("quantity") int quanity, @Param("size") String size);
	
	//set quantity trong cart 
	@Query("MATCH (p:Person) -[h:HAS_CART]->(pr:Product)"
			+ " WHERE ID(h) = $idCart"
			+ " SET h.quantity= $quantity"
			+ " RETURN ID(h)")
	public Long setQuantityCart(@Param("idCart") Long idCart, @Param("quantity") int quantity);
	
	
	//xóa product trong cart
	@Query("MATCH (p:Person)-[h:HAS_CART]->(pr:Product)"
			+ " WHERE ID(p) = $idPerson AND ID(h) = $idCart"
			+ " DELETE h"
			+ " RETURN 'Xóa thành công!'")
	public String deleteCartByIdCart(@Param("idCart") Long idCart, @Param("idPerson") Long idPerson);
	
	
	@Query("MATCH (p:Person) "
			+ "RETURN distinct p.role")
	public List<String> getRole();
	
	@Query("MATCH (p:Person) Return p")
	public List<PersonEntity> getAll();
	
	@Query("MATCH (p:Person) WHERE p.role = $role"
			+ " RETURN p")
	public List<PersonEntity> getByRole(@Param("role") String role);
	
	@Query("MATCH (p:Person) WHERE ID(p) = $id"
			+ " RETURN p")
	public PersonEntity getPersonById(@Param("id") Long id);

	public PersonEntity findByUsername(String username);
	
	public boolean existsByUsername(String username);
	
	@Query("MATCH (p:Person) WHERE ID(p) = $id"
			+ " SET p.address= $address, p.name= $name, p.phone= $phone, p.role= $role"
			+ " RETURN ID(p)")
	public Long updateById (@Param("id") long id, @Param("address") String address, @Param("name")  String name, @Param("phone") String phone, 
			@Param("role") String role, @Param("username") String username, @Param("password") String password);

	@Query("MATCH (p:Person) WHERE p.username = $username"
			+ " SET p.password = $password"
			+ " RETURN 'OK'")
	public String setPasswordByUsername(@Param("username") String username, @Param("password") String password);
}
