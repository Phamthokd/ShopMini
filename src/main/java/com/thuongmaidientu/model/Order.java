package com.thuongmaidientu.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name =  "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private List<OrderDetails> orderDetails;

	private double totalAmount;
	private double commission; //hoa hồng
	private String customerName;
	private String customerAddress;
	private String phoneNumber;
	private Date createdDate;
	private String note;
	private String status;
}
