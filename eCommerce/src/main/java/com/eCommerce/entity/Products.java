package com.eCommerce.entity;

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
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "unit",referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	private UnitTypes unitTypes;
	
	@ManyToOne
	@JoinColumn(name = "type",referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	private ProductTypes productTypes;
	
	@Column(name = "imgUrl")
	private String imgUrl;
	
	@Column(name="description")
	private String description;
	
	@Column(name="slug")
	private String slug;
	
	@Column(name="isDeleted")
	private Boolean isDeleted;
}
