package com.eCommerce.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eCommerce.entity.Products;
import com.eCommerce.repository.ProductsRepo;
import com.eCommerce.service.ProductsService;


@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepo repo;
	
	@Override
	public List<Products> findAll() {	
		return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0);
	}

	@Override
	public Products findById(Long id) {
		// TODO Auto-generated method stub
		Optional<Products> optional = repo.findById(id);
		return !optional.isEmpty() ? optional.get() : null;
	}

	@Override
	public void updateQuantity(Integer newQuantity, Long productId) {
		// TODO Auto-generated method stub
		repo.updateQuantity(newQuantity, productId);
	}

	@Override
	public Integer countProducts() {
		return repo.countProducts();
	}

	@Override
	public Page<Products> findAll(int pageSize, int pageNumber) throws Exception {
		// TODO Auto-generated method stub
		if(pageNumber>=1) {
			return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0, PageRequest.of(pageNumber-1,pageSize));
		}else {
			throw new Exception("page number must be greater than 1");
		}
		
	}
	
}
