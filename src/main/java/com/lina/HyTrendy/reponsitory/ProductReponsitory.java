package com.lina.HyTrendy.reponsitory;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.lina.HyTrendy.dto.ProductDto;
import com.lina.HyTrendy.entity.CategoryEntity;
import com.lina.HyTrendy.entity.ProductEntity;
import com.lina.HyTrendy.projection.ProductExtendProjection;

public interface ProductReponsitory extends Neo4jRepository<ProductEntity, Long> {
	@Query("Match (p: Product) return p")
	public List<ProductEntity> getAll();
	
	@Query("match (c:Category)-[ht:HAS_TYPE] ->(t:Type)-[hp:HAS_PRODUCT]->(p:Product)"
			+ "Where c.code = $categoryCode and t.code = $typeCode "
			+ "return  p")
	public List<ProductEntity> getByCategoryAndType( @Param("categoryCode") String categoryCode,  @Param("typeCode") String typeCode);
	
	@Query ("match (p:Product) where ID(p) =$id Return p")
	public ProductEntity getById (@Param("id") long id);
	
//	@Query("Match (p:Product) Where ID(p)= $id "
//			+ "SET p.description = $description, p.material = $material, p.name = $name, p.origin = $origin, p.price = $price, p.size = $size, p.stock = $stock, p.tags = $tags, p.image = $image "
//			+ "Return ID(p)")
//	public Long setByID (@Param("id") long id, @Param("description") String description, @Param("material") String material, @Param("name") String name,
//			@Param("origin") String origin, @Param("price")  int price, @Param("size") String[] size, @Param("stock") int stock, @Param("tags") String tags, @Param("image") String[] image);
	
	
	@Query("Match (p:Category)-[h1:HAS_TYPE] -(t:Type)-[h2:HAS_PRODUCT]->(pr:Product) "
			+ "Where ID(pr) = $id "
			+ "Return ID(p) as categoryId , ID(t) as typeId, pr.description as description, pr.image as image, pr.material as material, pr.name as name, pr.origin as origin, pr.price as price, pr.size as size, pr.stock as stock, pr.tags as tags")
	public ProductExtendProjection getProductExtendById (@Param("id") long id);

	@Query("Match (p:Category)-[h1:HAS_TYPE] -(t:Type)-[h2:HAS_PRODUCT]->(pr:Product) "
			+ "Where ID(pr) = $id "
			+ "Delete h2 "
			+ "SET pr.description = $description, pr.material = $material, pr.name = $name, pr.origin = $origin, pr.price = $price, pr.size = $size, pr.stock = $stock, pr.tags = $tags, pr.image = $image "
			+ " With  pr "
			+ " Match (t1:Type) "
			+ "where ID(t1) = $idType "
			+ "Create (t1)-[:HAS_PRODUCT]-> (pr) "
			+ "Return ID(pr)")
	public Long updateByID (@Param("id") long id, @Param("description") String description, @Param("material") String material, @Param("name") String name,
			@Param("origin") String origin, @Param("price")  int price, @Param("size") String[] size, @Param("stock") int stock, @Param("tags") String tags, @Param("image") String[] image, @Param("idType") long idType);
	
	
	
}
