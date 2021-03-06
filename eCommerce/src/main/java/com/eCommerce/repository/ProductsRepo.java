package com.eCommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eCommerce.entity.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
	
	List<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeleted,Integer quantity);
	Page<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeleted,Integer quantity, Pageable pageable);
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET quantity = ? WHERE id = ?",nativeQuery=true)
	void updateQuantity(Integer newQuantity, Long productId);
	
	@Query(value = "SELECT COUNT(id) FROM products;",nativeQuery=true)
	Integer countProducts();
}
