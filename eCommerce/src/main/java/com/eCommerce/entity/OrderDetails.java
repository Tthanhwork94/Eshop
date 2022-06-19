package com.eCommerce.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4229959347090144322L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "orderId",referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name = "productId",referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	private Products products;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private Double price;
}
