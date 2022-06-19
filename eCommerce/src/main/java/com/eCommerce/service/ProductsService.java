package com.eCommerce.service;

import java.util.List;

import com.eCommerce.entity.Products;

public interface ProductsService {
	List<Products> findAll();
	Products findById(Long id);
}
