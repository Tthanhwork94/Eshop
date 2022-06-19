package com.mockproject.dto;

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
public class CartDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 2364286333547745109L;
	
	private Long userId;
	private String address;
	private String phone;
	private Double totalPrice=0D;
	private Integer totalQuantity=0;
	private HashMap<Long,CartDetailDto> details= new HashMap<>();
	
}
