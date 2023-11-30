package com.thuongmaidientu.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String name;
	private Integer price;
	private Integer discount;
	
	@Column(columnDefinition = "TEXT")
	private String shortDescription;
	
	@Column(name = "description",columnDefinition = "TEXT")
	private String description;
	@Min(0)
	private Integer quantity;
	
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String status;
	@Min(0)
	private Integer numberOfViews;
	@Min(0)
	private Integer numberOfOrder;
	
	@ManyToOne
	@JoinColumn(name="categoryId",referencedColumnName = "id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "id")
	private User userProduct;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
	private Set<Cart> carts;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
	private Set<Review> reviews ;
	
}
