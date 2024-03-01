package br.com.stoom.store.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.stoom.store.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT p.id AS id, p.sku as sku, p.name AS name, p.data_inclusion AS dataInclusion, p.data_exclusion AS dataExclusion, p.data_change AS dataChange, p.price AS price, b.id AS brandId, b.id AS categoryId FROM product p INNER JOIN brand b ON b.id = p.brand_id WHERE b.name LIKE ?1 AND b.data_exclusion IS NULL", nativeQuery = true)
	public List<ProductDto>findProductsByNameBrand(String nameBrand);
	
	@Query(value = "SELECT p.id AS id, p.sku as sku, p.name AS name, p.data_inclusion AS dataInclusion, p.data_exclusion AS dataExclusion, p.data_change AS dataChange, p.price AS price, c.name AS category FROM product p INNER JOIN category c ON c.id = p.category_id WHERE c.name LIKE ?1 AND c.data_exclusion IS NULL", nativeQuery = true)
	public List<ProductCategoryDto>findProductsByNameCategory(String nameCategory);

	Optional<Product> findByNameAndDataExclusionIsNull(String name);
	
	Optional<Product> findById(Long id);
	
	public interface ProductDto {
	    String getName();
	    String getSku();
	    String name();
	    LocalDateTime getDataInclusion();
	    LocalDateTime getDataExclusion();
	    LocalDateTime getDataChange();
	    BigDecimal getPrice();
	}
	
	public interface ProductCategoryDto {
	    String getName();
	    String getSku();
	    String name();
	    LocalDateTime getDataInclusion();
	    LocalDateTime getDataExclusion();
	    LocalDateTime getDataChange();
	    BigDecimal getPrice();
	    String getCategory();
	}
}