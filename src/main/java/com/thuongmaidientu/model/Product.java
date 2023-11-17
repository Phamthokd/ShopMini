package com.thuongmaidientu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
	private Integer quantity;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String status;
	private Integer numberOfViews;
	
	@ManyToOne
	@JoinColumn(name="categoryId",referencedColumnName = "id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "id")
	private User userProduct;
	
}
