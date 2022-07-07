package com.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eCommerce.entity.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {
	
	@Query(value = "SELECT COUNT(id) FROM orders",nativeQuery=true)
	Integer countOrders();
}
