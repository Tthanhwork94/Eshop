package com.mockproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "typeid")
    private Integer typeid;

    @Column(name = "price")
    private Double price;

    @Column(name = "imgUrl")
    private String imgUrl;
    
    
    @Column(name="quantity")
    private Integer quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "typeId",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
    private ProductTypes product_types;

    @ManyToOne
    @JoinColumn(name = "unitId",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
    private UnitTypes unitTypes;

}
