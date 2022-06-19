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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6947234000436681131L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="hashPassword")
	private String hashPassword;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="role",referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
	private Roles role;
	
	@Column(name="createdDate")
	@CreationTimestamp
	private Timestamp createdDate;
	
	@Column(name="isDeleted")
	private Boolean isDeleted;
	
}
