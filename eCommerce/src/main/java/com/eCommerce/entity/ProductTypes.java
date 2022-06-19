package com.eCommerce.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5241795872072525768L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private	Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="slug")
	private String slug;
	
	@Column(name="isDeleted")
	private Boolean isDeleted;
}
