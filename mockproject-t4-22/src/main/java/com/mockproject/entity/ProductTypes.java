package com.mockproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypes implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8630798460747044256L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

}
