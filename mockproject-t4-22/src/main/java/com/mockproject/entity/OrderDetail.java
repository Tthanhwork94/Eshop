package com.mockproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@Entity
@Table(name="order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications","hibernateLazyInitializer"})
    private Products products;

    @Column(name = "quantity")
    private Integer quantity;

}
