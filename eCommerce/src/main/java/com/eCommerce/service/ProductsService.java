package com.eCommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.eCommerce.entity.Products;

public interface ProductsService {
	List<Products> findAll();
	Page<Products> findAll(int pageSize,int pageNumber) throws Exception;
	Products findById(Long id);
	void updateQuantity(Integer newQuantity, Long productId);
	Integer countProducts();
}
