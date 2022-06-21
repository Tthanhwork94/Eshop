package com.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eCommerce.dto.CartDetailDto;
import com.eCommerce.entity.OrderDetails;

@Repository
public interface OrdersDetailRepo extends JpaRepository<OrderDetails, Long>{
	
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO order_details(orderId,productId,quantity,price)"
	+" VALUES(:#{#dto.orderId},:#{#dto.productId},:#{#dto.quantity},:#{#dto.price})", nativeQuery=true)
	void insert(@Param("dto") CartDetailDto cartDetailDto);
}
