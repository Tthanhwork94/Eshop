package com.eCommerce.dto;

import java.io.Serializable;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4366200313102040071L;
	
	private Long orderId;
	private Long productId;
	private Double price;
	private Integer quantity;
	private String name;
	private String Slug;
	private String imgUrl;
	
}
